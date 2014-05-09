package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 */
public class KeybindingsNetworkPacket {
	public Keybindings keybindings;
	
	public KeybindingsNetworkPacket(){
		//Required by kryoNet.
	}
	public KeybindingsNetworkPacket(int leftKey, int mainKey, int rightKey, int fireKey){
		keybindings = new Keybindings(leftKey, mainKey, rightKey, fireKey);
	}
	public KeybindingsNetworkPacket(Keybindings keybindings){
		this.keybindings = new Keybindings(keybindings);
	}
}
