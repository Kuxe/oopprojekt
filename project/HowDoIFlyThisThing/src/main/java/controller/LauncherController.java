package controller;
import View.LauncherFrame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Martin Nilsson
 */
public class LauncherController implements PropertyChangeListener{
	
	private Controller controller;
	private LauncherFrame launcher;
	
	public LauncherController(){
		launcher = new LauncherFrame();
		launcher.addPropertyChangeListener(this);
		launcher.setVisible(true);
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
		}
	}
	public void launchControllerStop(){
			controller.cleanup();
			System.exit(0);
	}
	
}
