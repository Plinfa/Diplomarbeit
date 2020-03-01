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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnHinzufgen = new JButton("Hinzuf\u00FCgen");
		btnHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnEntfernen = new JButton("entfernen");
		
		JButton btnPartieLschen = new JButton("Partie l\u00F6schen");
		
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
		
		JLabel lblMitarbeiterZuPartie = new JLabel("Mitarbeiter zu Partie:");
		
		JLabel lblPartieleiterAuswhlenUm = new JLabel("Partieleiter ausw\u00E4hlen, um diese Partie zu bearbeiten:");
		
		JLabel lblOder = new JLabel("oder:");
		GroupLayout groupLayout = new GroupLayout(frame17.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblMitarbeiterZuPartie, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnHinzufgen, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEntfernen, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addComponent(lblOder, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnPartieLschen, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
					.addGap(62))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(204)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPartieleiterAuswhlenUm, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(216, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPartieleiterAuswhlenUm)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblMitarbeiterZuPartie)
							.addGap(23)
							.addComponent(btnHinzufgen)
							.addGap(11)
							.addComponent(btnEntfernen)
							.addGap(20)
							.addComponent(lblOder)
							.addGap(11)
							.addComponent(btnPartieLschen))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)))
		);
		frame17.getContentPane().setLayout(groupLayout);
		
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
