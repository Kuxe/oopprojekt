package controller;
import View.LauncherFrame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import org.lwjgl.input.Keyboard;
/**
 *
 * @author Martin Nilsson
 */
public class LauncherController implements PropertyChangeListener{
	
	private Controller controller;
	private LauncherFrame launcher;
	private int fireKey;
	private int leftKey;
	private int mainKey;
	private int rightKey;
	
	public LauncherController(){
		launcher = new LauncherFrame();
		launcher.addPropertyChangeListener(this);
		launcher.setVisible(true);
		fireKey = Keyboard.KEY_SPACE;
		leftKey = Keyboard.KEY_A;
		mainKey = Keyboard.KEY_W;
		rightKey = Keyboard.KEY_D;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_HOST.toString())){
			launcher.dispose();
			controller = new Controller();
			controller.start();
			controller.cleanup();
			System.exit(0);
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_JOIN.toString())){
			launcher.dispose();
			controller = new Controller(launcher.getIP());
			controller.start();
			controller.cleanup();
			System.exit(0);
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_OPTIONS.toString())){
			launcher.displayOptionsPanel();
		}else if(evt.getPropertyName().equals(LauncherFrame.Message.HDIFTT_OPTIONS_OK.toString())){
			launcher.displayStartPanel();
			this.fireKey = Keyboard.getKeyIndex(launcher.getFireKey());
			this.leftKey = Keyboard.getKeyIndex(launcher.getLeftThrusterKey());
			this.mainKey = Keyboard.getKeyIndex(launcher.getMainThrusterKey());
			this.rightKey = Keyboard.getKeyIndex(launcher.getRightThrusterKey());
		}
	}
	public void launchControllerStop(){
			controller.cleanup();
			System.exit(0);
	}
	
}
