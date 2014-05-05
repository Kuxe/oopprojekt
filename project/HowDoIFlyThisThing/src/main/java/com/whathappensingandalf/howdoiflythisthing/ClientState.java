package com.whathappensingandalf.howdoiflythisthing;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Point2f;

import utils.NetworkUtils;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 * 
 * State used by model when it's acting as a client
 */
public class ClientState implements ModelNetworkState {

	private Client client;
	private Set<DrawableData> drawables;
	private Set<String> sounds;
	private Point2f spaceshipPoint;
	
	private long timerStart;
	private long timerStop;
	private final long timerInterval;
	
	public ClientState(String ip) {
		
		spaceshipPoint = new Point2f(0, 0);
		
		client = new Client();
		drawables = new HashSet();
		sounds = new HashSet();
		
		timerStart = System.nanoTime();
		timerStop = System.nanoTime();
		timerInterval = 20000000; //20ms in nanoseconds
		
		NetworkUtils.registerClasses(client.getKryo());
		
		
		client.addListener(new Listener() {
			public void received(Connection connection, Object message) {
				//System.out.println("Recieved packet:" + message.toString());
				if(message instanceof DrawableDataNetworkPacket) {
					drawables = ((DrawableDataNetworkPacket)message).drawables;
				}
				else if(message instanceof Point2f) {
					spaceshipPoint = (Point2f)message;
				}
			}
		});
	
	    client.start();
	    try {
			client.connect(5000, ip, 5000);
		} catch (IOException e) {
			client.stop();
			e.printStackTrace();
		}
	}
	
	public state getState() {
		return ModelNetworkState.state.CLIENT;
	}

	public void addUser(int id) {
		//Illegal
	}

	public void update(Set<Integer> listOfHoldKeys) {
		
		
		if(timerStop - timerStart > timerInterval) {
			//Send listOfHoldKeys to server
			client.sendTCP(new HoldKeysNetworkPacket(listOfHoldKeys));
			timerStart = System.nanoTime();
		}
		timerStop = System.nanoTime();
	}

	public Set<DrawableData> getDrawableData() {
		return drawables;
	}

	public Point2f getSpaceshipPoint() {
		return spaceshipPoint;
	}

	public void cleanup() {
		System.out.println("Terminating client...");
		client.stop();
		System.out.println(" done!");
	}

	public Set<String> getListOfSounds() {
		return sounds;
	}
}
