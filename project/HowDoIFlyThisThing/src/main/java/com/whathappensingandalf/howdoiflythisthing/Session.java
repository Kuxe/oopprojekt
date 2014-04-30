package com.whathappensingandalf.howdoiflythisthing;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import org.lwjgl.input.Keyboard;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class Session {

	private Round round;
	private HashMap<InetSocketAddress, User> users;
	private Server server;
	
	
	public Session() {
		round = new Round();
		users = new HashMap();
		server = new Server();
		
		Kryo kryo = server.getKryo();
		kryo.register(HoldKeysNetworkPacket.class);
		kryo.register(IDrawableNetworkPacket.class);
	}
	
	public void start() {
		server.start();
		try {
			server.bind(5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			server.stop();
		} //5000 default TCP-port
		
		server.addListener(new Listener() {
			public void recieved(Connection connection, Object object) {
				if(object instanceof HoldKeysNetworkPacket) {
					users.get(connection.getRemoteAddressTCP()).executeInput(((HoldKeysNetworkPacket) object).listOfHoldKeys);
				}
			}
		});
		
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
	}

	public Map<Object, IDrawable> getIDrawables() {
		return round.getIDrawables();
	}
	
	public Point2f getSpaceshipPoint(InetSocketAddress ip) {
		return users.get(ip).getSpaceshipPoint();
	}
}
