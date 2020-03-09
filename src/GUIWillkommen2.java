import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.SwingConstants;
//Wartecker Marcel
public class GUIWillkommen2 {

	/**
	 * 
	 */
	
	public JFrame frame;
	private JPasswordField passwordField;
	
	

	/**
	 * Create the application.
	 */
	public GUIWillkommen2(String title,JDBC_MariaDB jdbc) {
		
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIWillkommen2.class.getResource("/ressources/EQOS.jpg")));
		frame.setBounds(100, 100, 659, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Personal- und Projektmanager");
		
		JLabel label = new JLabel("");
		
		JLabel label_1 = new JLabel("");
		
		passwordField = new JPasswordField();
		
		JButton btnAnmelden = new JButton("Anmelden");
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//jdbc.selectAllMitarbeiter();
 
				
				if(passwordField.getText().equals(jdbc.selectPasswort())) {/*Passwort von Datenbank importieren*/
					passwordField.setText(null);
					frame.dispose();
					
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									GUIstartmenue2 window = new GUIstartmenue2(jdbc);
									window.frame2.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					
					//was passiert nach anmelden
				}
				else {
					JOptionPane.showMessageDialog(null, "Falsches Passwort", "Error", JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
					
				
				}
			}
				
		});
		
		JLabel lblPasswort = new JLabel("Passwort:");
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPersonalprojektmanager = new JLabel("Personal- und Projektmanager");
		lblPersonalprojektmanager.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPersonalprojektmanager.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		lblPersonalprojektmanager.setHorizontalAlignment(JLabel.CENTER);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText(null);
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame= new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Wirklich beenden?", "Personal- und Projektmanager", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(220)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_1)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(270)
							.addComponent(lblLogin, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(178)))
					.addGap(114))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(178)
					.addComponent(lblPasswort, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(btnReset, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(btnAnmelden, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(178))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(189)
					.addComponent(lblPersonalprojektmanager, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
					.addGap(196))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(lblPersonalprojektmanager, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPasswort, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
							.addGap(137))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAnmelden)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExit)
							.addContainerGap())))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}