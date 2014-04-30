package com.whathappensingandalf.howdoiflythisthing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import org.lwjgl.input.Keyboard;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class HostState implements ModelNetworkState{

	private Round round;
	private HashMap<InetSocketAddress, User> users;
	private User myUser;
	private Server server;
	private Client client;
	private InetSocketAddress myIp;
	
	public HostState() {
		round = new Round();
		users = new HashMap();
		server = new Server();
		client = new Client();
		
		Kryo serverKryo = server.getKryo();
		serverKryo.register(HoldKeysNetworkPacket.class);
		serverKryo.register(IDrawableNetworkPacket.class);
		
		Kryo clientKryo = client.getKryo();
		clientKryo.register(HoldKeysNetworkPacket.class);
		clientKryo.register(IDrawableNetworkPacket.class);
		
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
				addUser(connection.getRemoteAddressTCP());
			}
			
			//Called whenever a client sends a packet
			public void recieved(Connection connection, Object object) {
				
				//If someone sends his input, execute it.
				if(object instanceof HoldKeysNetworkPacket) {
					users.get(connection.getRemoteAddressTCP()).executeInput(((HoldKeysNetworkPacket) object).listOfHoldKeys);
				}
			}
		});
		
		client.start();
		addUser(myIp);
		myUser = users.get(myIp);
		
		try {
			myIp = new InetSocketAddress(InetAddress.getLocalHost(), 5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		server.stop();
	}
	
	public void addUser(InetSocketAddress connection) {
		User user = new User();
		users.put(connection, user);
		round.addUser(user);
		if(users.size() == 2) {
			round.start();
		}
	}
	
	public void update(Set<Integer> listOfHoldKeys) {
		round.update();
		for(User user : users.values()) {
			user.executeInput(listOfHoldKeys);
		}
		
		//Send images to all clients
		server.sendToAllTCP(round.getIDrawables());
		
		//Send each spaceship point associated with each connection to the connected client
		for(Connection connection : server.getConnections()) {
			connection.sendTCP(users.get(connection.getRemoteAddressTCP()).getSpaceshipPoint());
		}
		
	}

	public Map<Object, IDrawable> getIDrawables() {
		return round.getIDrawables();
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
}
