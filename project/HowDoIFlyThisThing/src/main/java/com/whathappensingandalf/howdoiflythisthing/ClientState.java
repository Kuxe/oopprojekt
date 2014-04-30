package com.whathappensingandalf.howdoiflythisthing;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 * 
 * State used by model when it's acting as a client
 */
public class ClientState implements ModelNetworkState {

	private Client client;
	
	public ClientState(String ip) {
		Client client = new Client();
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

	public void addUser(InetSocketAddress connection) {
		//Illegal
	}

	public void update(Set<Integer> listOfHoldKeys) {
		//Send listOfHoldKeys to server
		client.sendTCP(listOfHoldKeys);
	}

	public Map<Object, IDrawable> getIDrawables() {
		
		return null;
	}

	public Point2f getSpaceshipPoint() {
		//Request spaceshippoint from server
		return null;
	}
}
