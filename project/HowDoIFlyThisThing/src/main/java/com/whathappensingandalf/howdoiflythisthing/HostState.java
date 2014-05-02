package com.whathappensingandalf.howdoiflythisthing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
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
	private HashMap<InetSocketAddress, User> users;
	private User myUser;
	private Server server;
	private InetSocketAddress myIp;
	private Set<Connection> connections;
	
	public HostState() {
		round = new Round();
		users = new HashMap();
		server = new Server();
		connections = new HashSet();
		
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
				
				//DO NOT TOUCH THIS ORDER
				//addUser is synchronized. It prevents nasty nullpointer errors related
				//to iterating through list and indexing hashmap with null key. DO NOT TOUCH!!!!!!
				addUser(connection.getRemoteAddressTCP());
				connections.add(connection);
				//END OF PROHIBITION
				
				System.out.println(" done!");
			}
			
			//Called whenever a client sends a packet
			public void recieved(Connection connection, Object object) {
				//If someone sends his input, execute it.
				if(object instanceof HoldKeysNetworkPacket) {
					//System.out.println("Recieved HoldKeysNetworkPacket from: " + connection.getRemoteAddressTCP());
					users.get(connection.getRemoteAddressTCP()).setListOfHoldKeys(((HoldKeysNetworkPacket) object).listOfHoldKeys);
				}
			}
			
			public void disconnected(Connection connection) {
				round.removeUser(users.get(connection.getRemoteAddressTCP()));
				users.remove(connection.getRemoteAddressTCP());
				connections.remove(connection);
			}
		});	
		
		try {
			myIp = new InetSocketAddress(InetAddress.getLocalHost(), 5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addUser(myIp);
		myUser = users.get(myIp);
	}
	
	public synchronized void addUser(InetSocketAddress ip) {
		User user = new User();
		users.put(ip, user);
		round.addUser(user);
		if(users.size() == 2) {
			round.start();
		}
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
	}
	
	public synchronized void sendPackets() {
		//Send images to all clients
		server.sendToAllTCP(round.getDrawableData());
		//Send each spaceship point associated with each connection to the connected client
		for(Connection connection : connections) {
			connection.sendTCP(users.get(connection.getRemoteAddressTCP()).getSpaceshipPoint());
		}
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
}
