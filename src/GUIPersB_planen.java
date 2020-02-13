import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		frame27.setTitle("Personal- und Projektmanager");
		frame27.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIPersB_planen.class.getResource("/ressources/EQOS.jpg")));
		frame27.setBounds(100, 100, 676, 437);
		frame27.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel label = new JLabel("Jahr:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("von KW:");
		
		JLabel label_2 = new JLabel("bis KW:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
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
		
		JButton btnEintragLschen = new JButton("Eintrag l\u00F6schen");
		btnEintragLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				String von = table.getValueAt(row, 0).toString();
				String bis = table.getValueAt(row, 1).toString();
				String Anzahl = table.getValueAt(row, 2).toString();
				
				if (JOptionPane.showConfirmDialog(frame27, "Eintrag wirklich löschen?", "Personal- und Projektmanager", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) 
				{
					jdbc.deletebenoetigt(von, bis, Anzahl, ProjNr);
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectPersbedarfplanungfürProj(ProjNr)));
				}
				else {
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectPersbedarfplanungfürProj(ProjNr)));
				}
				
			}
		});
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblBentigt = new JLabel("Ben\u00F6tigt: (Anzahl)");
		GroupLayout groupLayout = new GroupLayout(frame27.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addComponent(label)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBentigt, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(btnBesttigen, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(btnEintragLschen, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)))
					.addGap(38)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
					.addGap(31))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(155)
					.addComponent(label))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addComponent(lblBentigt)
					.addGap(4)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label_2))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(btnBesttigen)
					.addGap(11)
					.addComponent(btnEintragLschen))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
		);
		frame27.getContentPane().setLayout(groupLayout);
		
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
