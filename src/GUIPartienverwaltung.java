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
import java.awt.CardLayout;
import javax.swing.JPanel;

public class GUIPartienverwaltung {

	public JFrame frame17;
	private JTable table;
	private int PartieNr=0;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
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
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(193, 55, 281, 22);
		ResultSet res = null;

		
		frame17.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel Partienverwaltung = new JPanel();
		frame17.getContentPane().add(Partienverwaltung, "name_995414512100");
		
		JLabel label = new JLabel("Partieleiter ausw\u00E4hlen, um diese Partie zu bearbeiten:");
		label.setBounds(193, 30, 487, 14);
		
		
		

		
		

		try {
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eqospersonalplanung", "root","davmay81");
			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT leitetpartie.PartieNr, mitarbeiter.Name, mitarbeiter.Nachname, mitarbeiter.PersNr  FROM leitetpartie JOIN mitarbeiter ON leitetpartie.PersNr=mitarbeiter.PersNr";

			res = stmt.executeQuery(sql);
			while(res.next()) {
				 PartieNr= res.getInt(1);
				String name=res.getString(2)+ " "+ res.getString(3);
				
				
				comboBox_1.addItem(new ComboItem(name, PartieNr));
			}
			res.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//partie des Leiters bekommen und dann in tabelle anzeigen
				
				Object item=comboBox_1.getSelectedItem();
				 PartieNr = ((ComboItem)item).getPartieNr();
				
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectPartien(PartieNr)));
				
			}
		});
		
		JLabel label_1 = new JLabel("Mitarbeiter zu Partie:");
		label_1.setBounds(42, 120, 127, 14);
		
		JButton button = new JButton("Hinzuf\u00FCgen");
		
		button.setBounds(42, 157, 127, 23);
		
		JButton button_1 = new JButton("entfernen");
		button_1.setBounds(42, 191, 127, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table_1.getSelectedRow();
				int column = 0;
				int PersNr = (int) table_1.getValueAt(row, column);
				
				jdbc.MitarbeiterausPartieloeschen(PersNr);
				
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectPartien(PartieNr)));
			}
		});
		
		JLabel label_2 = new JLabel("oder:");
		label_2.setBounds(85, 234, 48, 14);
		
		JButton button_2 = new JButton("Partie l\u00F6schen");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				jdbc.deletePartie(PartieNr);
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectPartien(PartieNr)));
				frame17.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIPartienverwaltung window = new GUIPartienverwaltung(jdbc);
							window.frame17.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		button_2.setBounds(42, 259, 127, 23);
		Partienverwaltung.setLayout(null);
		Partienverwaltung.add(label);
		Partienverwaltung.add(comboBox_1);
		Partienverwaltung.add(label_1);
		Partienverwaltung.add(button);
		Partienverwaltung.add(button_1);
		Partienverwaltung.add(label_2);
		Partienverwaltung.add(button_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(193, 120, 480, 181);
		Partienverwaltung.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 701, 25);
		Partienverwaltung.add(menuBar);
		
		JButton btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame17.dispose();
			}
		});
		menuBar.add(btnZurck);
		
		JButton btnNeuePartie = new JButton("neue Partie anlegen");
		menuBar.add(btnNeuePartie);
		
		JPanel PartieAnlegen = new JPanel();
		frame17.getContentPane().add(PartieAnlegen, "name_1824475757400");
		
		JMenuBar menuBar_1 = new JMenuBar();
		
		JButton btnZurck_2 = new JButton("zur\u00FCck");
		btnZurck_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Partienverwaltung.setVisible(true);
				PartieAnlegen.setVisible(false);
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectPartien(PartieNr)));
				
			}
		});
		menuBar_1.add(btnZurck_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		table_3 = new JTable();
		scrollPane_2.setViewportView(table_3);
		
		JLabel lblPartieleiterAuswhlen = new JLabel("Partieleiter ausw\u00E4hlen:");
		
		JButton btnBesttigen_1 = new JButton("Best\u00E4tigen");
		btnBesttigen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = table_3.getSelectedRow();
				int column = 0;
				int PersNr =  (int) table_3.getValueAt(row, column);
				
				jdbc.Partiehinzufuegen(PersNr);
				PartieAnlegen.setVisible(false);
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectPartien(PartieNr)));
				
				frame17.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIPartienverwaltung window = new GUIPartienverwaltung(jdbc);
							window.frame17.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		GroupLayout gl_PartieAnlegen = new GroupLayout(PartieAnlegen);
		gl_PartieAnlegen.setHorizontalGroup(
			gl_PartieAnlegen.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar_1, GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_PartieAnlegen.createSequentialGroup()
					.addGap(69)
					.addComponent(lblPartieleiterAuswhlen, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_PartieAnlegen.createSequentialGroup()
					.addGap(70)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_PartieAnlegen.createSequentialGroup()
					.addGap(530)
					.addComponent(btnBesttigen_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
		);
		gl_PartieAnlegen.setVerticalGroup(
			gl_PartieAnlegen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PartieAnlegen.createSequentialGroup()
					.addComponent(menuBar_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblPartieleiterAuswhlen)
					.addGap(16)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnBesttigen_1))
		);
		PartieAnlegen.setLayout(gl_PartieAnlegen);
		
		JPanel Hinzufuegen = new JPanel();
		frame17.getContentPane().add(Hinzufuegen, "name_1845002358000");
		
		JMenuBar menuBar_2 = new JMenuBar();
		
		JButton btnZurck_1 = new JButton("zur\u00FCck");
		btnZurck_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Partienverwaltung.setVisible(true);
				Hinzufuegen.setVisible(false);
			}
		});
		menuBar_2.add(btnZurck_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		JButton btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				int row = table_2.getSelectedRow();
				
				String PersoNr = table_2.getValueAt(row, 0).toString();
				int PersNr= Integer.parseInt(PersoNr);
				
				jdbc.MitarbeiterzuPartie(PersNr, PartieNr);
				Hinzufuegen.setVisible(false);
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectPartien(PartieNr)));
				
				
			}
			
		});
		
		JLabel lblMitarbeiterAuswhlen = new JLabel("Mitarbeiter ausw\u00E4hlen:");
		GroupLayout gl_Hinzufuegen = new GroupLayout(Hinzufuegen);
		gl_Hinzufuegen.setHorizontalGroup(
			gl_Hinzufuegen.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar_2, GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_Hinzufuegen.createSequentialGroup()
					.addGap(87)
					.addComponent(lblMitarbeiterAuswhlen, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_Hinzufuegen.createSequentialGroup()
					.addGap(88)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 579, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_Hinzufuegen.createSequentialGroup()
					.addGap(554)
					.addComponent(btnBesttigen, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
		);
		gl_Hinzufuegen.setVerticalGroup(
			gl_Hinzufuegen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Hinzufuegen.createSequentialGroup()
					.addComponent(menuBar_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblMitarbeiterAuswhlen)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnBesttigen))
		);
		Hinzufuegen.setLayout(gl_Hinzufuegen);
		
		
		btnNeuePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PartieAnlegen.setVisible(true);
				Partienverwaltung.setVisible(false);
				table_3.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterPartieinfo()));
				
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Hinzufuegen.setVisible(true);
				Partienverwaltung.setVisible(false);
				table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterPartieinfo()));
			}
		});
	}
}
