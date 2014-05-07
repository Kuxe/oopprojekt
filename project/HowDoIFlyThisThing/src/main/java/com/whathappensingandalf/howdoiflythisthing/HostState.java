package com.whathappensingandalf.howdoiflythisthing;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;

import utils.NetworkUtils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class HostState implements ModelNetworkState{

	private Round round;
	private HashSet<Connection> lazyAddUsers;
	private HashSet<Connection> lazyRemoveUsers;
	private HashMap<Integer, User> users;
	private User myUser;
	private Server server;
	private InetSocketAddress myIp;
	private Set<Connection> connections;

	private long timerStart;
	private long timerStop;
	private final long timerInterval;

	public HostState() {
		round = new Round();
		users = new HashMap();
		server = new Server();
		connections = new HashSet();
		
		lazyAddUsers = new HashSet();
		lazyRemoveUsers = new HashSet();

		timerStart = System.nanoTime();
		timerStop = System.nanoTime();
		timerInterval = 20000000; //20ms in nanoseconds

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
			}

			//Called whenever a client sends a packet
			public void received(Connection connection, Object object) {
				//System.out.println("Recieved packet from " + connection.getRemoteAddressTCP() + ": " + object.toString());
				//If someone sends his input, execute it.
				if(object instanceof HoldKeysNetworkPacket) {
					//System.out.println("Recieved HoldKeysNetworkPacket from: " + connection.getRemoteAddressTCP());
					users.get(connection.getID()).setListOfHoldKeys(((HoldKeysNetworkPacket) object).listOfHoldKeys);
				}
			}

			public void disconnected(Connection connection) {
				System.out.println(connection.getID() + " disconnected");
				lazyRemoveUsers.add(connection);
			}
		});	
		addUser(0); //Host ID is 0. Connection IDs start from 1 and go upward.
		myUser = users.get(0);
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
		
//		if(getListOfSounds().size() != 0){
//			System.out.println("listOfSounds " + getListOfSounds());
//		}
		
		if(timerStop - timerStart > timerInterval) {			
			//Send images to all clients
			server.sendToAllTCP(new DrawableDataNetworkPacket(round.getDrawableData()));
			
//			TODO- round.getListOfSounds() always == 0
			if(getListOfSounds().size() != 0){
				System.out.println("in if " + getListOfSounds());
			}
			//Send sounds to all clients
			server.sendToAllTCP(new SoundNetworkPacket(round.getListOfSounds()));
			//Send each spaceship point associated with each connection to the connected client
			for(Connection connection : connections) {
				connection.sendTCP(users.get(connection.getID()).getSpaceshipPoint());
			}			
			timerStart = System.nanoTime();
		}
//		if(getListOfSounds().size() != 0){
//			System.out.println("outside " + getListOfSounds());
//		}
		timerStop = System.nanoTime();
	}

	public Set<DrawableData> getDrawableData() {
		return round.getDrawableData();
	}

	public Point2f getSpaceshipPoint(InetSocketAddress ip) {
		return users.get(ip).getSpaceshipPoint();
	}

	public state getState() {
		return ModelNetworkState.state.HOST;
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
}
