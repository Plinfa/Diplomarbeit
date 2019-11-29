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
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblPersnr = new JLabel("PersNr");
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblVorname = new JLabel("Vorname");
		
		JButton btnEingabe = new JButton("Eingabe");
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
					GUIgruppeODEReinzeln window = new GUIgruppeODEReinzeln(jdbc);
					window.frame6.setVisible(true);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame9.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPersnr, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVorname, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addComponent(btnEingabe, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(177))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(212)
							.addComponent(btnEingabe))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(lblPersnr)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblVorname)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
		);
		frame9.getContentPane().setLayout(groupLayout);
	}
}