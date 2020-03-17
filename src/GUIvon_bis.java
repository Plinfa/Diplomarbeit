import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.JMenuBar;

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
		frame12.setTitle("Personal- und Projektmanager");
		frame12.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIvon_bis.class.getResource("/ressources/EQOS.jpg")));
		frame12.setBounds(100, 100, 746, 431);
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
						
						//jdbc.unverfuegbarSetzen(PersNr, von, bis, Grund);
						jdbc.unverfuegbarsetzen(PersNr, von, bis, Grund);
						
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectAbwesenheiten(PersNr)));
					textField.setText(null);
					textField_1.setText(null);
					//frame12.dispose();
			}
		});
		
		JButton btnNewButton = new JButton("Eintrag l\u00F6schen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				String von = table.getValueAt(row, 0).toString();
				String bis = table.getValueAt(row, 1).toString();
				
				if (JOptionPane.showConfirmDialog(frame12, "Eintrag wirklich löschen?", "Personal- und Projektmanager", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) 
				{
					jdbc.deleteabwesenheit(von, bis, PersNr);
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectAbwesenheiten(PersNr)));
				}
				else {
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectAbwesenheiten(PersNr)));
				}
				
				
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
						
						jdbc.unverfuegbarsetzen(PersNr, von, bis, Grund);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectAbwesenheiten(PersNr)));
					//frame12.dispose();
				
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("Schulung");
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
						
						jdbc.unverfuegbarsetzen(PersNr, von, bis, Grund);
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectAbwesenheiten(PersNr)));
					//frame12.dispose();
			
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblJahr = new JLabel("Jahr:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectAbwesenheiten(PersNr)));
		GroupLayout groupLayout = new GroupLayout(frame12.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(lblJahr)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblVon, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
									.addGap(48)
									.addComponent(lblBis, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
									.addGap(46))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
									.addGap(10)
									.addComponent(textField_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
									.addGap(10)
									.addComponent(textField_3)))
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBesttigen, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
					.addGap(42)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addGap(56))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVon)
						.addComponent(lblBis))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblJahr))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnBesttigen)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(11)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(121))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
					.addGap(27))
		);
		frame12.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame12.setJMenuBar(menuBar);
		
		JButton btnNewButton_3 = new JButton("zur\u00FCck");
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame12.dispose();
				
			}
		});
		menuBar.add(btnNewButton_3);
		
	}
}
