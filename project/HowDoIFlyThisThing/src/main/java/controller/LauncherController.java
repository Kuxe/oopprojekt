package controller;
import View.HDIFTT_Launcher;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Martin Nilsson
 */
public class LauncherController implements PropertyChangeListener{
	
	private Controller controller;
	private HDIFTT_Launcher launcher;
	
	public LauncherController(){
		//launcher = new HDIFTT_Launcher();
		//launcher.addPropertyChangeListener(this);
		//launcher.setVisible(true);
		controller = new Controller();
		controller.start();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		
		this.launcher.dispose();
		
		if(evt.getPropertyName().toString().equals(HDIFTT_Launcher.Message.HDIFTT_HOST.toString())){
			System.out.println("Host");
			controller = new Controller();
		}else if(evt.getPropertyName().toString().equals(HDIFTT_Launcher.Message.HDIFTT_JOIN.toString())){
			System.out.println("Join");
			controller = new Controller(launcher.getIP());
		}
		controller.start();
		
	}
}
