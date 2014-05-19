package controller;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Keyboard;

import view.LauncherFrame;
/**
 *
 * @author Martin Nilsson
 */
public class LauncherController implements PropertyChangeListener{
	
	private Controller controller;
	private final LauncherFrame launcher;
	private final int defaultFireKey;
	private final int defaultLeftKey;
	private final int defaultMainKey;
	private final int defaultRightKey;
	private boolean defaultFullscreen;
	private int fireKey;
	private int leftKey;
	private int mainKey;
	private int rightKey;
	private boolean fullscreen;
	
	public LauncherController(){
		launcher = new LauncherFrame();
		launcher.addPropertyChangeListener(this);
		launcher.hideErrorMessages();
		launcher.setVisible(true);
		defaultFireKey		=	Keyboard.KEY_SPACE;
		defaultLeftKey		=	Keyboard.KEY_A;
		defaultMainKey		=	Keyboard.KEY_W;
		defaultRightKey		=	Keyboard.KEY_D;
		leftKey				=	Keyboard.KEY_A;
		mainKey				=	Keyboard.KEY_W;
		rightKey			=	Keyboard.KEY_D;
		fireKey				=	Keyboard.KEY_SPACE;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_HOST.toString())){
			hostGame();
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_JOIN.toString())){
			joinGame();
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
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_EXIT.toString())){
			System.exit(0);
		}
		
	}
	private void hostGame(){
		try{
			controller = new Controller(leftKey, mainKey, rightKey, fireKey);
			launcher.setVisible(false);
			launcher.hideErrorMessages();
			controller.createTheView(fullscreen);
			controller.start();
			controller.cleanup();
			launcher.setVisible(true);
		}catch (IOException ex) {
			//All ready hosting.
			launcher.displayHostErrorMessage();
		}
	}
	private void joinGame(){
		try{
			controller = new Controller(launcher.getIP(), leftKey, mainKey, rightKey, fireKey);
			launcher.setVisible(false);
			launcher.hideErrorMessages();
			controller.createTheView(fullscreen);
			controller.start();
			controller.cleanup();
			launcher.setVisible(true);
			}catch(java.net.UnknownHostException h){
				//Can't be interpeted as a host.
				launcher.displayConnectionErrorMessage();
			}
	}
	private void saveSettings(){
		fireKey = Keyboard.getKeyIndex(launcher.getFireKey().toUpperCase());
		leftKey = Keyboard.getKeyIndex(launcher.getLeftThrusterKey().toUpperCase());
		mainKey = Keyboard.getKeyIndex(launcher.getMainThrusterKey().toUpperCase());
		rightKey = Keyboard.getKeyIndex(launcher.getRightThrusterKey().toUpperCase());
		fullscreen = launcher.getFullscreen();
	}
	private void displaySettings(){
		launcher.setFireKey(Keyboard.getKeyName(fireKey));
		launcher.setLeftThrusterKey(Keyboard.getKeyName(leftKey));
		launcher.setMainThrusterKey(Keyboard.getKeyName(mainKey));
		launcher.setRightThrusterKey(Keyboard.getKeyName(rightKey));
		launcher.setFullscreen(fullscreen);
	}
	private void resetSettings(){
		launcher.setFireKey(Keyboard.getKeyName(defaultFireKey));
		launcher.setLeftThrusterKey(Keyboard.getKeyName(defaultLeftKey));
		launcher.setMainThrusterKey(Keyboard.getKeyName(defaultMainKey));
		launcher.setRightThrusterKey(Keyboard.getKeyName(defaultRightKey));
		launcher.setFullscreen(defaultFullscreen);
	}
	
}
