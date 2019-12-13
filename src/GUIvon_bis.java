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

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GUIvon_bis {

	public JFrame frame12;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public GUIvon_bis(JDBC_MariaDB jdbc, int PersNr) {
		initialize(jdbc, PersNr);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc, int PersNr) {
		frame12 = new JFrame();
		frame12.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIvon_bis.class.getResource("/ressources/EQOS.jpg")));
		frame12.setBounds(100, 100, 746, 431);
		frame12.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField();
		textField.setBounds(51, 105, 96, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(157, 105, 96, 20);
		textField_1.setColumns(10);
		
		JLabel lblVon = new JLabel("von KW:");
		lblVon.setBounds(51, 85, 58, 14);
		
		JLabel lblBis = new JLabel("bis KW:");
		lblBis.setBounds(157, 85, 50, 14);
		
		
		
		
		
		JButton btnBesttigen = new JButton("krank");
		btnBesttigen.setBounds(42, 198, 67, 23);
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vonint=0;
				int bisint=0;
				int vJahr=0;
				int bJahr=0;
				String Grund= "krank";
				//int PersNr=//Wert aus Datenbank
						
				
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
						
						jdbc.unverfuegbarSetzen(PersNr, von, bis, Grund);
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
			}
		});
		
		JButton btnNewButton = new JButton("wieder verf\u00FCgbar");
		btnNewButton.setBounds(42, 227, 115, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				/*
				
				int von=0;
				int bis=0;
				
					
					try {
						von=Integer.parseInt(textField.getText());
						bis=Integer.parseInt(textField_1.getText());
						
						//int lenght=jdbc.getMitarbeiter().size();
						
						
						/*for(int i=0; i<lenght; i++) {
						    if (jdbc.getMitarbeiter().get(i).getPersnr()==(PersoNr)) {
						        System.out.println("ok cool");
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
				*/
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Urlaub");
		btnNewButton_1.setBounds(119, 198, 75, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int vonint=0;
				int bisint=0;
				int vJahr=0;
				int bJahr=0;
				String Grund= "urlaub";
				//int PersNr=//Wert aus Datenbank
				
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
						
						jdbc.unverfuegbarSetzen(PersNr, von, bis, Grund);
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
				
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("Schulung");
		btnNewButton_2.setBounds(204, 198, 87, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int vonint=0;
				int bisint=0;
				int vJahr=0;
				int bJahr=0;
				String Grund= "schulung";
				//int PersNr=//Wert aus Datenbank
				
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
						
						jdbc.unverfuegbarSetzen(PersNr, von, bis, Grund);
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
			
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setBounds(51, 131, 96, 20);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(157, 131, 96, 20);
		textField_3.setColumns(10);
		
		JLabel lblJahr = new JLabel("Jahr:");
		lblJahr.setBounds(20, 134, 25, 14);
		frame12.getContentPane().setLayout(null);
		frame12.getContentPane().add(lblJahr);
		frame12.getContentPane().add(lblVon);
		frame12.getContentPane().add(textField);
		frame12.getContentPane().add(textField_2);
		frame12.getContentPane().add(textField_1);
		frame12.getContentPane().add(textField_3);
		frame12.getContentPane().add(lblBis);
		frame12.getContentPane().add(btnNewButton);
		frame12.getContentPane().add(btnBesttigen);
		frame12.getContentPane().add(btnNewButton_1);
		frame12.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(362, 98, 232, 186);
		frame12.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectAbwesenheiten(PersNr)));
	}
}
