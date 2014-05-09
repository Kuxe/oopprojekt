package View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Martin Nilsson
 */
public class LauncherFrame extends javax.swing.JFrame implements PropertyChangeListener{

	private PropertyChangeSupport pcs;
	private StartPanel startPanel;
	private OptionsPanel optionsPanel;
	private CardLayout cardLayout;

	
	public static enum Message{
		HDIFTT_HOST,
		HDIFTT_JOIN,
		HDIFTT_OPTIONS,
		HDIFTT_OPTIONS_OK,
		HDIFTT_OPTIONS_CANCEL,
		HDOFTT_OPTIONS_RESET
	}
	
	/**
	 * Creates new form LauncherFrame
	 */
	public LauncherFrame() {
		initComponents();
		cardLayout = (CardLayout)backgroundPanel.getLayout();
		pcs = new PropertyChangeSupport(this);
		startPanel = new StartPanel();
		startPanel.addPropertyChangeListener(this);
		optionsPanel = new OptionsPanel();
		optionsPanel.addPropertyChangeListener(this);
		
		this.setSize(380, 500);
		startPanel.setSize(360, 500);
		optionsPanel.setSize(360, 500);
		backgroundPanel.add(startPanel,"Start");
		backgroundPanel.add(optionsPanel,"Options");
		
		cardLayout.show(backgroundPanel, "Start");
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
	public void displayStartPanel(){
		cardLayout.show(backgroundPanel, "Start");
	}
	public void displayOptionsPanel(){
		cardLayout.show(backgroundPanel, "Options");
	}
	/**
	 * Adds a listener to this object.
	 * @param pcl
	 */
	@Override
    public void addPropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.addPropertyChangeListener(pcl);
	}
	/**
	 * Adds a listener to this object.
	 * @param pcl
	 */
	@Override
    public void removePropertyChangeListener(PropertyChangeListener pcl){
		this.pcs.removePropertyChangeListener(pcl);
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		pcs.firePropertyChange(evt);
	}
	public String getIP(){
		return this.startPanel.getIP();
	}
	public int getVolume(){
		return this.optionsPanel.getVolume();
	}
	public boolean getFullscreen(){
		return this.optionsPanel.getFullscreen();
	}
	public String getFireKey(){
		return this.optionsPanel.getFireKey();
	}
	public String getLeftThrusterKey(){
		return this.optionsPanel.getLeftThrusterKey();
	}
	public String getMainThrusterKey(){
		return this.optionsPanel.getMainThrusterKey();
	}
	public String getRightThrusterKey(){
		return this.optionsPanel.getRightThrusterKey();
	}
	public void setFireKey(String key){
		this.optionsPanel.setFireKey(key);
	}
	public void setLeftThrusterKey(String key){
		this.optionsPanel.setLeftThrusterKey(key);
	}
	public void setMainThrusterKey(String key){
		this.optionsPanel.setMainThrusterKey(key);
	}
	public void setRightThrusterKey(String key){
		this.optionsPanel.setRightThrusterKey(key);
	}
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("How do I fly this thing?!");

        backgroundPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LauncherFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    // End of variables declaration//GEN-END:variables
}
