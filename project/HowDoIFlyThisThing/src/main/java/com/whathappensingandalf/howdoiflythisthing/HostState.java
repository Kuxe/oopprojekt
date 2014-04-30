package com.whathappensingandalf.howdoiflythisthing;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2f;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

/**
 * 
 * @author Joakim "Kuxe" Thorén
 *
 * State used by model when acting as a host
 */
public class HostState implements ModelNetworkState {
	
	private Session session;
	private Client client;
	private InetSocketAddress myIp;
	
	public HostState() {
		try {
			myIp = new InetSocketAddress(InetAddress.getLocalHost(), 5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ModelNetworkState.state getState() {
		return ModelNetworkState.state.HOST;
	}
	
	public void addUser(InetSocketAddress connection) {
		session.addUser(connection);
	}
	
	public void host() {
		session = new Session();
		client = new Client(); 
		client.start();
		addUser(myIp);
	}
	
	public void update(Set<Integer> listOfHoldKeys) {
		session.update(listOfHoldKeys);
	}
	
	public Map<Object, IDrawable> getIDrawables() {
		return session.getIDrawables();
	}
	
	/**
	 *  This method returns point of spaceship beloning to ip.
	 *  If ip is currently a spectator, returns position where
	 *  spaceship exploded. If ip never had a spaceship returns
	 *  null.
	 * @return Point of spaceship beloning to ip
	 */
	public Point2f getSpaceshipPoint() {
		return session.getSpaceshipPoint(myIp); 
	}
}
