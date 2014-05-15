package com.whathappensingandalf.howdoiflythisthing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.vecmath.Point2f;
import networkpackets.*;
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
	private Keybindings keybindins;
	private long countdown = 0;
	private String modelStatus = "Not connected to a host; status not avaiable";
	private WorldBorder border;
	private PropertyChangeSupport pcs;
	
	private long timerStart;
	private long timerStop;
	private final long timerInterval;
	
	private int hull;
	private int shield;

	public ClientState(String ip, Keybindings keybindings) throws IOException {

		spaceshipPoint = new Point2f(0, 0);
		this.keybindins = keybindings;
		
		pcs = new PropertyChangeSupport(this);

		client = new Client();
		drawables = new HashSet();
		sounds = new HashSet();

		timerStart = System.nanoTime();
		timerStop = System.nanoTime();
		timerInterval = 20000000; //20ms in nanoseconds
		
		hull= 0;
		shield= 0;

		NetworkUtils.registerClasses(client.getKryo());


		client.addListener(new Listener() {
			
			@Override
			public void received(Connection connection, Object message) {
				
				//If it is a valid network packet...
				if(message instanceof NetworkPacket) {
					NetworkPacket tempObject = (NetworkPacket)message;
					switch(tempObject.getType()) {
					case COUNTDOWN:
						countdown = ((CountdownNetworkPacket)message).countdown;
						break;
					case DRAWABLE_DATA:
						drawables = ((DrawableDataNetworkPacket)message).drawables;
						break;
					case HOLD_KEYS:
						break;
					case HUD:
						hull= ((HudNetworkPacket)message).hull;
						shield= ((HudNetworkPacket)message).shield;
						break;
					case KEYBINDINGS:
						break;
					case MODEL_STATUS:
						modelStatus = ((ModelStatusNetworkPacket)message).status;
						break;
					case SOUND:
						sounds = ((SoundNetworkPacket)message).sounds;
						break;
					case SPARKLE:
						pcs.firePropertyChange(Gameworld.Message.SPARKLE.toString(),((SparkleNetworkPacket)message),false);
						break;
					case SPACESHIP_POINT:
						spaceshipPoint = ((SpaceshipPointNetworkPacket)message).point;
						break;
					case EXPLOSION:
						pcs.firePropertyChange(Gameworld.Message.EXPLOSION.toString(),((ExplosionNetworkPacket)message),false);
						break;
					case WORLD_BORDER:
						border = new WorldBorder(((WorldBorderNetworkPacket)message).height, ((WorldBorderNetworkPacket)message).width);
					}
				}
			}
			
			@Override
			public void disconnected(Connection connection) {
				System.out.println("lost connection to host!");
				pcs.firePropertyChange(ModelNetworkState.message.SHUTDOWN.toString(), false, true);
			}
		});

		client.start();
		client.connect(5000, ip, 5000);
		client.sendTCP(new KeybindingsNetworkPacket(keybindings));
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
		Set<String> soundsCopy= new HashSet<String>();
		for(String string: sounds){
			soundsCopy.add(string);
		}
		sounds.clear();
		return soundsCopy;
	}
	
	public int getHull(){
		return hull;
	}
	
	public int getShield(){
		return shield;
	}

	@Override
	public long getCountdown() {
		return countdown;
	}

	@Override
	public String getModelStatus() {
		return modelStatus;
	}
	
	@Override
	public WorldBorder getWorldBorder() {
		return border;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
}
