import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

public class GUIvon_bis {

	public JFrame frame12;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public GUIvon_bis(JDBC_MariaDB jdbc, int row) {
		initialize(jdbc, row);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc, int row) {
		frame12 = new JFrame();
		frame12.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIvon_bis.class.getResource("/ressources/EQOS.jpg")));
		frame12.setBounds(100, 100, 526, 337);
		frame12.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblVon = new JLabel("von KW:");
		
		JLabel lblBis = new JLabel("bis KW:");
		
		
		
		
		
		JButton btnBesttigen = new JButton("krank");
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vonint=0;
				int bisint=0;
				int vJahr=0;
				int bJahr=0;
				int PersNr=//Wert aus Datenbank
				
				vonint=Integer.parseInt(textField.getText());
				bisint=Integer.parseInt(textField_1.getText());
				vJahr=Integer.parseInt(textField_2.getText());
				bJahr=Integer.parseInt(textField_3.getText());
						
				int startday=2;								//2 für Montag
				int endday=7;								//7 für Samstag
				
				Calendar startdate = Calendar.getInstance();
				startdate.setWeekDate(vJahr, vonint, startday);	//von Datum setzen
				Date vonDate=startdate.getTime();				//in Java Date speichern
				java.sql.Date von = new java.sql.Date(vonDate.getTime());	//in SQL Date umwandeln
						
						
				Calendar enddate = Calendar.getInstance();
				enddate.setWeekDate(bJahr, bisint, endday);	//bis Datum setzen
				enddate.getTime();
				Date bisDate=enddate.getTime();				//in Java Date speichern
				java.sql.Date bis = new java.sql.Date(bisDate.getTime()); 	 //in SQL Date umwandeln	
				
				
					try {
						
						jdbc.verfuegbarkeitSetzen(PersNr, von, bis);
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
			}
		});
		
		JButton btnNewButton = new JButton("wieder verf\u00FCgbar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int von=0;
				int bis=0;
				
					
					try {
						von=Integer.parseInt(textField.getText());
						bis=Integer.parseInt(textField_1.getText());
						
						//int lenght=jdbc.getMitarbeiter().size();
						
						
						/*for(int i=0; i<lenght; i++) {
						    if (jdbc.getMitarbeiter().get(i).getPersnr()==(PersoNr)) {
						        System.out.println("ok cool");*/
						        while(von<=bis) {
						        	
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setKrank(false);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setUrlaub(false);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setSchulung(false);
						        	von++;
						        	
						        }
						        
						        
						   // }
						    
						
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
				
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Urlaub");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int von=0;
				int bis=0;
				
					
					try {
						von=Integer.parseInt(textField.getText());
						bis=Integer.parseInt(textField_1.getText());
						
						//int lenght=jdbc.getMitarbeiter().size();
						
						
						/*for(int i=0; i<lenght; i++) {
						    if (jdbc.getMitarbeiter().get(i).getPersnr()==(PersoNr)) {
						        System.out.println("ok cool");*/
						        while(von<=bis) {
						        	
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setUrlaub(true);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setZugeteilt(null);
						        	von++;
						        	
						        }
						        
						   // }
						    
						
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Schulung");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int von=0;
				int bis=0;
				
					
					try {
						von=Integer.parseInt(textField.getText());
						bis=Integer.parseInt(textField_1.getText());
						
						//int lenght=jdbc.getMitarbeiter().size();
						
						
						/*for(int i=0; i<lenght; i++) {
						    if (jdbc.getMitarbeiter().get(i).getPersnr()==(PersoNr)) {
						        System.out.println("ok cool");*/
						        while(von<=bis) {
						        	
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setSchulung(true);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setZugeteilt(null);
						        	von++;
						        	
						        }
						        boolean schulung=jdbc.getMitarbeiter().get(1).getWochen().get(5).isSchulung();
						        System.out.println(schulung);
						   // }
						    
						
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblJahr = new JLabel("Jahr:");
			
		
		GroupLayout groupLayout = new GroupLayout(frame12.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnBesttigen)
									.addGap(117)
									.addComponent(btnNewButton_1)
									.addGap(18)
									.addComponent(btnNewButton_2))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(lblJahr)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVon)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBis))
							.addGap(95)))
					.addContainerGap(132, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVon)
						.addComponent(lblBis))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJahr)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBesttigen)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		frame12.getContentPane().setLayout(groupLayout);
	}
}
