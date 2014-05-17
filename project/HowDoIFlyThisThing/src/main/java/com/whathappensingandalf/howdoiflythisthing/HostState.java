package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;

import networkpackets.CountdownNetworkPacket;
import networkpackets.DrawableDataNetworkPacket;
import networkpackets.HoldKeysNetworkPacket;
import networkpackets.HudNetworkPacket;
import networkpackets.KeybindingsNetworkPacket;
import networkpackets.ModelStatusNetworkPacket;
import networkpackets.NetworkPacket;
import networkpackets.SoundNetworkPacket;
import networkpackets.SpaceshipPointNetworkPacket;
import networkpackets.WorldBorderNetworkPacket;
import networkpackets.NetworkPacket.Type;
import utils.NetworkUtils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class HostState implements ModelNetworkState, PropertyChangeListener{

	private Round round;
	private HashSet<Connection> lazyAddUsers;
	private HashSet<Connection> lazyRemoveUsers;
	private HashMap<Integer, User> users;
	private User myUser;
	private Server server;
	private Set<Connection> connections;
	private Keybindings keybindings;
	private PropertyChangeSupport pcs;

	private long timerStart;
	private long timerStop;
	private final long timerInterval;

	public HostState(Keybindings keybindings) {
		this.keybindings = keybindings;
		round = new Round();
		round.addPropertyChangeListener(this);
		
		pcs = new PropertyChangeSupport(this);
		
		users = new HashMap<Integer, User>();
		server = new Server(32768, 4096);
		connections = new HashSet<Connection>();
		
		lazyAddUsers = new HashSet<Connection>();
		lazyRemoveUsers = new HashSet<Connection>();

		timerStart = System.nanoTime();
		timerStop = System.nanoTime();
		timerInterval = 50000000; //20ms in nanoseconds

		NetworkUtils.registerClasses(server.getKryo());

		start();
	}

	public void start() {
		server.start();
		try {
			server.bind(5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //5000 default TCP-port

		server.addListener(new Listener() {

			public void connected(Connection connection) {

				//Grant new connections an user
				System.out.print(connection.getRemoteAddressTCP() + " is connecting...");
				lazyAddUsers.add(connection);
				System.out.println(" done!");
				
				WorldBorder border = round.getWorld().getBorder();
				int width = border.getWorldWidth();
				int height = border.getWorldHeight();
				connection.sendTCP(new WorldBorderNetworkPacket(width, height));
			}

			//Called whenever a client sends a packet
			public void received(Connection connection, Object object) {
				//If it is a valid network packet...
				if(object instanceof NetworkPacket) {
					NetworkPacket tempObject = (NetworkPacket)object;
					switch(tempObject.getType()) {
					case COUNTDOWN:
						break;
					case DRAWABLE_DATA:
						break;
					case HOLD_KEYS:
						users.get(connection.getID()).setListOfHoldKeys(((HoldKeysNetworkPacket) object).listOfHoldKeys);
						break;
					case HUD:
						break;
					case KEYBINDINGS:
						users.get(connection.getID()).setKeybindings(((KeybindingsNetworkPacket)object).keybindings);
						break;
					case MODEL_STATUS:
						break;
					case SOUND:
						break;
					case SPARKLE:
						break;
					}
				}
			}

			public void disconnected(Connection connection) {
				System.out.println(connection.getID() + " disconnected");
				lazyRemoveUsers.add(connection);
			}
		});	
		addUser(0); //Host ID is 0. Connection IDs start from 1 and go upward.
		myUser = users.get(0);
		myUser.setKeybindings(keybindings);
		myUser.setListOfHoldKeys(new HashSet<Integer>());
	}
	
	/**
	 * Thread-safe addUser()
	 * @param id
	 */
	public synchronized void lazyAddUser(Connection connection) {
		lazyAddUsers.add(connection);
	}

	/**
	 * Thread-safe removeUser()
	 * @param id
	 */
	public synchronized void lazyRemoveUser(Connection connection) {
		lazyRemoveUsers.add(connection);
	}
	
	/**
	 * Not thread safe
	 */
	public void addUser(int id) {
		User user = new User();
		users.put(id, user);
		round.addUser(user);
		//To prevent executng input before recieved from client causing nullptr
		users.get(id).setListOfHoldKeys(new HashSet<Integer>());
	}

	public void update(Set<Integer> listOfHoldKeys) {
		round.update();
		myUser.setListOfHoldKeys(listOfHoldKeys);
		for(User user : users.values()) {
			//TODO: FIX THIS STUPID SHIT
			user.executeInput(user.getListOfHoldKeys());
		}		
		//Send data packets to clients
		sendPackets();
		
		processLazyAddUser();
		processLazyRemoveUser();
	}
	
	/**
	 * Must be called on main-thread. Will add users from lazyAddUsers-set to
	 * actual set of users
	 */
	private synchronized void processLazyAddUser() {
		//Add newly connected cliens via main-thread
		for(Connection connection : lazyAddUsers) {
			//DO NOT TOUCH THIS ORDER
			//addUser is synchronized. It prevents nasty nullpointer errors related
			//to iterating through list and indexing hashmap with null key. DO NOT TOUCH!!!!!!
			addUser(connection.getID());
			connections.add(connection);
		}
		lazyAddUsers.clear();
	}
	
	/**
	 * Must be called on main-thread. Will remove users defined in
	 * lazyRemoveUsers from actual set of users
	 */
	private synchronized void processLazyRemoveUser() {
		//Add newly connected cliens via main-thread
		for(Connection connection : lazyRemoveUsers) {
			round.removeUser(users.get(connection.getID()));
			users.remove(connection.getID());
			connections.remove(connection);
		}
		lazyRemoveUsers.clear();
	}

	public void sendPackets() {
		
		if(timerStop - timerStart > timerInterval) {		
			//Send each spaceship point associated with each connection to the connected client
			for(Connection connection : connections) {
				
				//Send drawables to client
				connection.sendTCP(new DrawableDataNetworkPacket(round.getDrawableData()));
				
				//Send spaceship point to client
				connection.sendTCP(new SpaceshipPointNetworkPacket(users.get(connection.getID()).getSpaceshipPoint()));
				
				//Send round countdown to client
				connection.sendTCP(new CountdownNetworkPacket(round.getCountdown()));
				
				//Send modelstatus to client
				connection.sendTCP(new ModelStatusNetworkPacket(round.getModelStatus()));
				
				//Send sounds to client
				connection.sendTCP(new SoundNetworkPacket(getListOfSounds()));
				
				//Send HUD to client
				connection.sendTCP(new HudNetworkPacket(users.get(connection.getID()).getHull(), users.get(connection.getID()).getShield()));
			}
			getListOfSounds().clear();
			timerStart = System.nanoTime();
		}
		timerStop = System.nanoTime();
	}

	public Set<DrawableData> getDrawableData() {
		return round.getDrawableData();
	}

	public Point2f getSpaceshipPoint(Connection connection) {
		return users.get(connection.getID()).getSpaceshipPoint();
	}

	public State getState() {
		return ModelNetworkState.State.HOST;
	}
	/**
	 *  This method returns point of spaceship beloning to ip.
	 *  If ip is currently a spectator, returns position where
	 *  spaceship exploded. If ip never had a spaceship returns
	 *  null.
	 * @return Point of spaceship beloning to ip
	 */
	public Point2f getSpaceshipPoint() {
		return myUser.getSpaceshipPoint();
	}

	public void cleanup() {
		System.out.print("Terminating server...");
		server.stop();
		System.out.println(" done!");
	}

	public Set<String> getListOfSounds(){
		return round.getListOfSounds();
	}

	@Override
	public int getHull() {
		return myUser.getHull();
	}

	@Override
	public int getShield() {
		return myUser.getShield();
	}
	public long getCountdown() {
		return round.getCountdown();
	}

	@Override
	public String getModelStatus() {
		return round.getModelStatus();
	}
	
	@Override
	public WorldBorder getWorldBorder() {
		return round.getWorld().getBorder();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Gameworld.Message.EXPLOSION.toString())){
			server.sendToAllTCP(evt.getOldValue());
			pcs.firePropertyChange(evt);
		}else if(evt.getPropertyName().equals(Gameworld.Message.SPARKLE.toString())){
			server.sendToAllTCP(evt.getOldValue());
			pcs.firePropertyChange(evt);
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
}
