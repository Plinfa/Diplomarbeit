import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class GUIPartienverwaltung {

	public JFrame frame17;
	private JTable table;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUIPartienverwaltung(JDBC_MariaDB jdbc) {

		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frame17 = new JFrame();
		frame17.setTitle("Personal- und Projektmanager");
		frame17.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIPartienverwaltung.class.getResource("/ressources/EQOS.jpg")));
		frame17.setBounds(100, 100, 717, 410);
		frame17.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame17.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 100, 435, 189);
		frame17.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnHinzufgen = new JButton("Hinzuf\u00FCgen");
		btnHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHinzufgen.setBounds(53, 138, 127, 23);
		frame17.getContentPane().add(btnHinzufgen);
		
		JButton btnEntfernen = new JButton("entfernen");
		btnEntfernen.setBounds(53, 172, 127, 23);
		frame17.getContentPane().add(btnEntfernen);
		
		JButton btnPartieLschen = new JButton("Partie l\u00F6schen");
		btnPartieLschen.setBounds(53, 240, 127, 23);
		frame17.getContentPane().add(btnPartieLschen);
		
		JComboBox comboBox = new JComboBox();
		
		ResultSet res = null;
		ResultSet res2 = null;

		try {
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eqospersonalplanung", "root","5455809Otto");
			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT leitetpartie.PartieNr, mitarbeiter.Name, mitarbeiter.Nachname  FROM leitetpartie JOIN mitarbeiter ON leitetpartie.PersNr=mitarbeiter.PersNr";

			res = stmt.executeQuery(sql);
			while(res.next()) {
				String name=res.getString(2)+ " "+ res.getString(3);
				
				comboBox.addItem(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//partie des Leiters bekommen und dann in tabelle anzeigen
				int partienummer =comboBox.getSelectedIndex()+1;
				//Statement stmt2 = con.createStatement();
				//String sql2= "SELECT 
				//res2 = stmt2.executeQuery(sql2);
				//table.setModel(DbUtils.resultSetToTableModel(jdbc.));
				
			}
		});
		
		comboBox.setBounds(265, 36, 156, 22);
		frame17.getContentPane().add(comboBox);
		
		JLabel lblMitarbeiterZuPartie = new JLabel("Mitarbeiter zu Partie:");
		lblMitarbeiterZuPartie.setBounds(53, 101, 127, 14);
		frame17.getContentPane().add(lblMitarbeiterZuPartie);
		
		JLabel lblPartieleiterAuswhlenUm = new JLabel("Partieleiter ausw\u00E4hlen, um diese Partie zu bearbeiten:");
		lblPartieleiterAuswhlenUm.setBounds(159, 11, 321, 14);
		frame17.getContentPane().add(lblPartieleiterAuswhlenUm);
		
		JLabel lblOder = new JLabel("oder:");
		lblOder.setBounds(96, 215, 48, 14);
		frame17.getContentPane().add(lblOder);
		
		JMenuBar menuBar = new JMenuBar();
		frame17.setJMenuBar(menuBar);
		
		JButton btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame17.dispose();
			}
		});
		menuBar.add(btnZurck);
		
		JButton btnNeuePartie = new JButton("neue Partie anlegen");
		menuBar.add(btnNeuePartie);
	}
}
