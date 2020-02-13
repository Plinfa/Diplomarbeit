import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
//Wartecker Marcel
public class GUIchangePasswort {

	public JFrame frame7;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	

	/**
	 * Launch the application.
	 */
	
		
	

	/**
	 * Create the application.
	 */
	public GUIchangePasswort(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frame7 = new JFrame();
		frame7.setTitle("Personal- und Projektmanager");
		frame7.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIchangePasswort.class.getResource("/ressources/EQOS.jpg")));
		frame7.setBounds(100, 100, 565, 388);
		frame7.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		passwordField = new JPasswordField();
		
		passwordField_1 = new JPasswordField();
		
		passwordField_2 = new JPasswordField();
		
		JLabel lblAltesPasswort = new JLabel("altes Passwort");
		
		JLabel lblNeuesPasswort = new JLabel("neues Passwort");
		
		JLabel lblNeuesPasswortWiederholen = new JLabel("neues Passwort wiederholen");
		
		JButton btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(passwordField.getText().equals(jdbc.selectPasswort()) && passwordField_1.getText().equals(passwordField_2.getText())) {
					String neuesPasswort=passwordField_1.getText();
					jdbc.updatePasswort(neuesPasswort);
					JOptionPane.showMessageDialog(null, "Erfolgreich abgeschlossen", "Passwort geändert", JOptionPane.INFORMATION_MESSAGE);
					frame7.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Erneut versuchen", "Passwort ändern fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
					passwordField_1.setText(null);
					passwordField_2.setText(null);
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame7.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAltesPasswort)
						.addComponent(lblNeuesPasswort)
						.addComponent(lblNeuesPasswortWiederholen))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField_2, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
					.addGap(166))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(353, Short.MAX_VALUE)
					.addComponent(btnBesttigen)
					.addGap(138))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAltesPasswort))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNeuesPasswort))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNeuesPasswortWiederholen))
					.addGap(52)
					.addComponent(btnBesttigen)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		frame7.getContentPane().setLayout(groupLayout);
	}
}
