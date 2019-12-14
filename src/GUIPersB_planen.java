import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GUIPersB_planen {

	public JFrame frame27;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUIPersB_planen(JDBC_MariaDB jdbc, int ProjNr) {
		initialize(jdbc, ProjNr);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc, int ProjNr) {
		frame27 = new JFrame();
		frame27.setBounds(100, 100, 676, 437);
		frame27.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame27.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Jahr:");
		label.setBounds(61, 155, 25, 14);
		frame27.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(92, 152, 96, 20);
		frame27.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(92, 126, 96, 20);
		frame27.getContentPane().add(textField_1);
		
		JLabel label_1 = new JLabel("von KW:");
		label_1.setBounds(92, 106, 58, 14);
		frame27.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("bis KW:");
		label_2.setBounds(198, 106, 50, 14);
		frame27.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(198, 126, 96, 20);
		frame27.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(198, 152, 96, 20);
		frame27.getContentPane().add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 81, 277, 232);
		frame27.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectPersbedarfplanungfürProj(ProjNr)));
		
		JButton btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vonint=0;
				int bisint=0;
				int vJahr=0;
				int bJahr=0;
				int anzahl=0;
				//int PersNr=//Wert aus Datenbank
						
				
				vonint=Integer.parseInt(textField_1.getText());
				bisint=Integer.parseInt(textField_2.getText());
				vJahr=Integer.parseInt(textField.getText());
				bJahr=Integer.parseInt(textField_3.getText());
				anzahl=Integer.parseInt(textField_4.getText());
						
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
						
						jdbc.persbedarfplanen(ProjNr, von, bis, anzahl);
						
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectPersbedarfplanungfürProj(ProjNr)));
					textField_2.setText(null);
					textField_1.setText(null);
					textField_4.setText(null);
					//frame12.dispose();
			}
		});
				
			
		btnBesttigen.setBounds(99, 202, 89, 23);
		frame27.getContentPane().add(btnBesttigen);
		
		JButton btnEintragLschen = new JButton("Eintrag l\u00F6schen");
		btnEintragLschen.setBounds(99, 236, 163, 23);
		frame27.getContentPane().add(btnEintragLschen);
		
		textField_4 = new JTextField();
		textField_4.setBounds(93, 75, 95, 20);
		frame27.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblBentigt = new JLabel("Ben\u00F6tigt: (Anzahl)");
		lblBentigt.setBounds(92, 57, 146, 14);
		frame27.getContentPane().add(lblBentigt);
		
		JMenuBar menuBar = new JMenuBar();
		frame27.setJMenuBar(menuBar);
		
		JButton btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame27.dispose();
				
			}
		});
		menuBar.add(btnZurck);
	}
}
