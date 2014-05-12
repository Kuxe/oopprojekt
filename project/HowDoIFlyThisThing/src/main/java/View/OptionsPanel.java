package View;

import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Martin Nilsson
 */
public class OptionsPanel extends javax.swing.JPanel{

	
	/**
	 * Creates new form OptionsPanek
	 */
	Set <Integer> all;
	
	public OptionsPanel() {
		initComponents();
		all = new HashSet();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        soundLabel = new javax.swing.JLabel();
        fullscreenLabel = new javax.swing.JLabel();
        fireKeyLabel = new javax.swing.JLabel();
        leftThrusterKeyLabel = new javax.swing.JLabel();
        mainThrusterKeyLabel = new javax.swing.JLabel();
        rightThrusterKeyLabel = new javax.swing.JLabel();
        soundSlider = new javax.swing.JSlider();
        fullscreenCheckBox = new javax.swing.JCheckBox();
        fireTextField = new javax.swing.JTextField();
        leftTextField = new javax.swing.JTextField();
        mainTextField = new javax.swing.JTextField();
        rightTextField = new javax.swing.JTextField();
        resetButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        soundLabel.setText("Sound volume");
        soundLabel.setMaximumSize(new java.awt.Dimension(130, 14));
        soundLabel.setMinimumSize(new java.awt.Dimension(130, 14));
        soundLabel.setPreferredSize(new java.awt.Dimension(130, 14));

        fullscreenLabel.setText("Toggle fullscreen");
        fullscreenLabel.setMaximumSize(new java.awt.Dimension(130, 14));
        fullscreenLabel.setMinimumSize(new java.awt.Dimension(130, 14));
        fullscreenLabel.setPreferredSize(new java.awt.Dimension(130, 14));

        fireKeyLabel.setText("Fire key");
        fireKeyLabel.setMaximumSize(new java.awt.Dimension(130, 14));
        fireKeyLabel.setMinimumSize(new java.awt.Dimension(130, 14));
        fireKeyLabel.setPreferredSize(new java.awt.Dimension(130, 14));

        leftThrusterKeyLabel.setText("Left thruster key");
        leftThrusterKeyLabel.setMaximumSize(new java.awt.Dimension(70, 14));
        leftThrusterKeyLabel.setMinimumSize(new java.awt.Dimension(70, 14));
        leftThrusterKeyLabel.setPreferredSize(new java.awt.Dimension(70, 14));

        mainThrusterKeyLabel.setText("Main thruster key");
        mainThrusterKeyLabel.setMaximumSize(new java.awt.Dimension(130, 14));
        mainThrusterKeyLabel.setMinimumSize(new java.awt.Dimension(130, 14));
        mainThrusterKeyLabel.setPreferredSize(new java.awt.Dimension(130, 14));

        rightThrusterKeyLabel.setText("Right thruster key");
        rightThrusterKeyLabel.setMaximumSize(new java.awt.Dimension(130, 14));
        rightThrusterKeyLabel.setMinimumSize(new java.awt.Dimension(130, 14));
        rightThrusterKeyLabel.setPreferredSize(new java.awt.Dimension(130, 14));

        fullscreenCheckBox.setText("Fullscreen");

        fireTextField.setText("jTextField1");
        fireTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fireTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fireTextFieldFocusLost(evt);
            }
        });
        fireTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fireTextFieldKeyTyped(evt);
            }
        });

        leftTextField.setText("jTextField2");
        leftTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                leftTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                leftTextFieldFocusLost(evt);
            }
        });
        leftTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                leftTextFieldKeyTyped(evt);
            }
        });

        mainTextField.setText("jTextField3");
        mainTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mainTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mainTextFieldFocusLost(evt);
            }
        });
        mainTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mainTextFieldKeyTyped(evt);
            }
        });

        rightTextField.setText("jTextField4");
        rightTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rightTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                rightTextFieldFocusLost(evt);
            }
        });
        rightTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rightTextFieldKeyTyped(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(soundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(fireKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rightThrusterKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(leftThrusterKeyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainThrusterKeyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(fullscreenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(soundSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fullscreenCheckBox)
                            .addComponent(fireTextField)
                            .addComponent(leftTextField)
                            .addComponent(mainTextField)
                            .addComponent(rightTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(soundSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fullscreenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullscreenCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fireKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftThrusterKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainThrusterKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rightThrusterKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.firePropertyChange(LauncherFrame.Message.HDIFTT_OPTIONS_OK.toString(), 0, 1);
		this.getLeftThrusterKey();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.firePropertyChange(LauncherFrame.Message.HDIFTT_OPTIONS_CANCEL.toString(), 0, 1);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        this.firePropertyChange(LauncherFrame.Message.HDOFTT_OPTIONS_RESET.toString(), 0, 1);
    }//GEN-LAST:event_resetButtonActionPerformed

    private void fireTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fireTextFieldKeyTyped
		this.okButton.grabFocus();
		this.fireTextField.setText(this.fireTextField.getText().toUpperCase());
    }//GEN-LAST:event_fireTextFieldKeyTyped

    private void fireTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fireTextFieldFocusGained
        this.fireTextField.setText("");
    }//GEN-LAST:event_fireTextFieldFocusGained

    private void leftTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_leftTextFieldKeyTyped
		this.okButton.grabFocus();
		this.leftTextField.setText(this.leftTextField.getText().toUpperCase());
    }//GEN-LAST:event_leftTextFieldKeyTyped

    private void leftTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_leftTextFieldFocusGained
        this.leftTextField.setText("");
    }//GEN-LAST:event_leftTextFieldFocusGained

    private void mainTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mainTextFieldKeyTyped
        this.okButton.grabFocus();
		this.mainTextField.setText(this.mainTextField.getText().toUpperCase());
    }//GEN-LAST:event_mainTextFieldKeyTyped

    private void mainTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mainTextFieldFocusGained
        this.mainTextField.setText("");
    }//GEN-LAST:event_mainTextFieldFocusGained

    private void rightTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rightTextFieldFocusGained
        this.rightTextField.setText("");
    }//GEN-LAST:event_rightTextFieldFocusGained

    private void rightTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rightTextFieldKeyTyped
        this.okButton.grabFocus();
    }//GEN-LAST:event_rightTextFieldKeyTyped

    private void rightTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rightTextFieldFocusLost
        this.rightTextField.setText(this.rightTextField.getText().toUpperCase());
    }//GEN-LAST:event_rightTextFieldFocusLost

    private void mainTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mainTextFieldFocusLost
        this.mainTextField.setText(this.mainTextField.getText().toUpperCase());
    }//GEN-LAST:event_mainTextFieldFocusLost

    private void leftTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_leftTextFieldFocusLost
        this.leftTextField.setText(this.leftTextField.getText().toUpperCase());
    }//GEN-LAST:event_leftTextFieldFocusLost

    private void fireTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fireTextFieldFocusLost
        this.fireTextField.setText(this.fireTextField.getText().toUpperCase());
    }//GEN-LAST:event_fireTextFieldFocusLost

	public int getVolume(){
		return this.soundSlider.getValue();
	}
	public boolean getFullscreen(){
		return this.fullscreenCheckBox.isSelected();
	}
	public String getFireKey(){
		return this.fireTextField.getText();
	}
	public String getLeftThrusterKey(){
		return this.leftTextField.getText();
	}
	public String getMainThrusterKey(){
		return this.mainTextField.getText();
	}
	public String getRightThrusterKey(){
		return this.rightTextField.getText();
	}
	public void setFullscreen(boolean selected){
		this.fullscreenCheckBox.setSelected(selected);
	}
	public void setFireKey(String key){
		this.fireTextField.setText(key);
	}
	public void setLeftThrusterKey(String key){
		this.leftTextField.setText(key);
	}
	public void setMainThrusterKey(String Key){
		this.mainTextField.setText(Key);
	}
	public void setRightThrusterKey(String key){
		this.rightTextField.setText(key);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel fireKeyLabel;
    private javax.swing.JTextField fireTextField;
    private javax.swing.JCheckBox fullscreenCheckBox;
    private javax.swing.JLabel fullscreenLabel;
    private javax.swing.JTextField leftTextField;
    private javax.swing.JLabel leftThrusterKeyLabel;
    private javax.swing.JTextField mainTextField;
    private javax.swing.JLabel mainThrusterKeyLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField rightTextField;
    private javax.swing.JLabel rightThrusterKeyLabel;
    private javax.swing.JLabel soundLabel;
    private javax.swing.JSlider soundSlider;
    // End of variables declaration//GEN-END:variables
}
