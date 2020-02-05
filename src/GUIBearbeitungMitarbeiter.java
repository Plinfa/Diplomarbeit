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
		frame111.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIBearbeitungMitarbeiter.class.getResource("/ressources/EQOS.jpg")));
		frame111.setBounds(100, 100, 709, 470);
		frame111.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame111.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 92, 551, 303);
		frame111.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleMitarbeiter()));
		
		JButton btnnderungenSpeichern = new JButton("\u00C4nderungen speichern");
		btnnderungenSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//jdbc.updateMitarbeiter(PersoNr, tablecontent);
				//personalnummer von ausgewähltem mitarbeiter benötigt
			}
		});
		btnnderungenSpeichern.setBounds(91, 43, 189, 23);
		frame111.getContentPane().add(btnnderungenSpeichern);
		
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
