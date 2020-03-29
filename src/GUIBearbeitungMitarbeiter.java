import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class GUIBearbeitungMitarbeiter {

	public JFrame frame111;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUIBearbeitungMitarbeiter(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frame111 = new JFrame();
		frame111.setTitle("Personal- und Projektmanager");
		frame111.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIBearbeitungMitarbeiter.class.getResource("/ressources/EQOS.jpg")));
		frame111.setBounds(100, 100, 709, 470);
		frame111.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleMitarbeiter()));
		
		
		
		JButton btnnderungenSpeichern = new JButton("\u00C4nderungen speichern");
		btnnderungenSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row=0;
				
				String PersoNr=null;
				String Name =null;
				String Nachname=null;
				String GebDat=null;
				String Taetigkeit=null;
				String EMail=null;
				//String Fuehrerschein="NULL";
				
				try {
					 row = table.getSelectedRow();
					 PersoNr = table.getValueAt(row, 0).toString();
					 Name = table.getValueAt(row, 1).toString();
					 Nachname= table.getValueAt(row, 2).toString();
					 GebDat = table.getValueAt(row, 3).toString();
					 Taetigkeit = table.getValueAt(row, 4).toString();
					 EMail = table.getValueAt(row, 5).toString();
					 //Fuehrerschein = table.getValueAt(row, 6).toString();
					 
					
					jdbc.updateMitarbeiter(PersoNr, Name, Nachname, GebDat, Taetigkeit, EMail); 
					 
					
					//personalnummer vom ausgewählten mitarbeiter benötigt
					
				}catch(Exception e1) {
					
					e1.printStackTrace();
				}
				 
			}
		});
		
		JLabel lblnderungenMitEnter = new JLabel("\u00C4nderungen mit Enter best\u00E4tigen und dann:");
		GroupLayout groupLayout = new GroupLayout(frame111.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(74)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblnderungenMitEnter, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnnderungenSpeichern, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
					.addGap(68))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblnderungenMitEnter)
						.addComponent(btnnderungenSpeichern))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
					.addGap(36))
		);
		frame111.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame111.setJMenuBar(menuBar);
		
		JButton btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame111.dispose();
				
				
			}
		});
		menuBar.add(btnZurck);
	}
}
