package controller;
import View.LauncherFrame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.lwjgl.input.Keyboard;
/**
 *
 * @author Martin Nilsson
 */
public class LauncherController implements PropertyChangeListener{
	
	private Controller controller;
	private LauncherFrame launcher;
	private int defaultFireKey;
	private int defaultLeftKey;
	private int defaultMainKey;
	private int defaultRightKey;
	private int fireKey;
	private int leftKey;
	private int mainKey;
	private int rightKey;
	
	public LauncherController(){
		launcher = new LauncherFrame();
		launcher.addPropertyChangeListener(this);
		launcher.setVisible(true);
		defaultFireKey	=	Keyboard.KEY_SPACE;
		defaultLeftKey	=	Keyboard.KEY_A;
		defaultMainKey	=	Keyboard.KEY_W;
		defaultRightKey	=	Keyboard.KEY_D;
		leftKey			=	Keyboard.KEY_A;
		mainKey			=	Keyboard.KEY_W;
		rightKey		=	Keyboard.KEY_D;
		fireKey			=	Keyboard.KEY_SPACE;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_HOST.toString())){
			launcher.dispose();
			controller = new Controller(leftKey, mainKey, rightKey, fireKey);
			controller.start();
			controller.cleanup();
			System.exit(0);
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_JOIN.toString())){
			launcher.dispose();
			controller = new Controller(launcher.getIP(), leftKey, mainKey, rightKey, fireKey);
			controller.start();
			controller.cleanup();
			System.exit(0);
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_OPTIONS.toString())){
			this.displaySettings();
			launcher.displayOptionsPanel();
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_OPTIONS_OK.toString())){
			this.saveSettings();
			launcher.displayStartPanel();
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_OPTIONS_CANCEL.toString())){
			this.displaySettings();
			launcher.displayStartPanel();
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDOFTT_OPTIONS_RESET.toString())){
			this.resetSettings();
		}
	}
	public void saveSettings(){
		fireKey = Keyboard.getKeyIndex(launcher.getFireKey().toUpperCase());
		leftKey = Keyboard.getKeyIndex(launcher.getLeftThrusterKey().toUpperCase());
		mainKey = Keyboard.getKeyIndex(launcher.getMainThrusterKey().toUpperCase());
		rightKey = Keyboard.getKeyIndex(launcher.getRightThrusterKey().toUpperCase());
		System.out.println(Keyboard.getKeyName(fireKey));
	}
	public void displaySettings(){
		launcher.setFireKey(Keyboard.getKeyName(fireKey));
		launcher.setLeftThrusterKey(Keyboard.getKeyName(leftKey));
		launcher.setMainThrusterKey(Keyboard.getKeyName(mainKey));
		launcher.setRightThrusterKey(Keyboard.getKeyName(rightKey));
	}
	public void resetSettings(){
		launcher.setFireKey(Keyboard.getKeyName(defaultFireKey));
		launcher.setLeftThrusterKey(Keyboard.getKeyName(defaultLeftKey));
		launcher.setMainThrusterKey(Keyboard.getKeyName(defaultMainKey));
		launcher.setRightThrusterKey(Keyboard.getKeyName(defaultRightKey));
	}
	public void launchControllerStop(){
			controller.cleanup();
			System.exit(0);
	}
	
}
