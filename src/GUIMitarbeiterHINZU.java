import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTable;

//david 03.09
public class GUIMitarbeiterHINZU {

	public JFrame frame9;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	
		
	

	/**
	 * Create the application.
	 */
	public GUIMitarbeiterHINZU(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frame9 = new JFrame();
		frame9.setTitle("Mitarbeiter hinzuf\u00FCgen");
		frame9.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIMitarbeiterHINZU.class.getResource("/ressources/EQOS.jpg")));
		frame9.setBounds(100, 100, 450, 300);
		frame9.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField();
		textField.setBounds(26, 53, 96, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(26, 104, 96, 20);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(26, 155, 96, 20);
		textField_2.setColumns(10);
		
		JLabel lblPersnr = new JLabel("PersNr");
		lblPersnr.setBounds(26, 33, 46, 14);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(26, 84, 46, 14);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(26, 135, 53, 14);
		
		JButton btnEingabe = new JButton("Eingabe");
		btnEingabe.setBounds(168, 212, 89, 23);
		btnEingabe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Name=null;
				String Nachname=null;
				int PersoNr=0;
				
					
					try {
						PersoNr=Integer.parseInt(textField.getText());
						Name=textField_1.getText();
						Nachname=textField_2.getText();
						
						jdbc.insertEmployee(PersoNr, Name, Nachname);
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					frame9.dispose();
					
					
					
			}
		});
		frame9.getContentPane().setLayout(null);
		frame9.getContentPane().add(textField);
		frame9.getContentPane().add(lblPersnr);
		frame9.getContentPane().add(textField_1);
		frame9.getContentPane().add(lblVorname);
		frame9.getContentPane().add(textField_2);
		frame9.getContentPane().add(lblName);
		frame9.getContentPane().add(btnEingabe);
	}
}