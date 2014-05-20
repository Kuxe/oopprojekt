package network;

import com.whathappensingandalf.howdoiflythisthing.Keybindings;

/**
 *
 * @author Martin Nilsson
 */
public class KeybindingsNetworkPacket implements NetworkPacket {
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
	@Override
	public Type getType() {
		return NetworkPacket.Type.KEYBINDINGS;
	}
}
