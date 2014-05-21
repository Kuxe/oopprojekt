package network;

public interface NetworkPacket {
	public static enum Type {
		COUNTDOWN,
		DRAWABLE_DATA,
		HOLD_KEYS,
		HUD,
		KEYBINDINGS,
		MODEL_STATUS,
		SOUND,
		SPARKLE,
		SPACESHIP_POINT,
		EXPLOSION,
		WORLD_BORDER,
		ASTEROID_EXP
	}
	public Type getType();
}
