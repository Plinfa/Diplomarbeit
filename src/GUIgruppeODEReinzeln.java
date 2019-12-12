import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

//Wartecker Marcel
public class GUIgruppeODEReinzeln {

	public JFrame frame6;
	private JMenuBar menuBar;
	private JButton btnZurck;
	private JButton btnBesttigen;
	private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnPersonalbedarfProProjekt;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GUIgruppeODEReinzeln(JDBC_MariaDB jdbc) {
		initialize(jdbc);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frame6 = new JFrame();
		frame6.setIconImage(
				Toolkit.getDefaultToolkit().getImage(GUIgruppeODEReinzeln.class.getResource("/ressources/EQOS.jpg")));
		frame6.setBounds(100, 100, 964, 620);
		frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.setBounds(853, 197, 85, 23);
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();
				int column = 0;
				String content = table.getValueAt(row, column).toString();
				int PersNr = Integer.parseInt(content);

				// int von=0;
				// int bis=0;
				int projnr = 0;
				projnr = Integer.parseInt(textField_2.getText());
				// von=Integer.parseInt(textField_3.getText());
				// bis=Integer.parseInt(textField_4.getText());
				String vonEingabe = null;
				String bisEingabe = null;
				String vonJahr = null;
				String bisJahr = null;
				vonEingabe = textField_3.getText();
				bisEingabe = textField_4.getText();
				vonJahr = textField.getText();
				bisJahr = textField_1.getText(); 				// Eingabe
				int vonint = Integer.parseInt(vonEingabe);
				int bisint = Integer.parseInt(bisEingabe);
				int vJahr = Integer.parseInt(vonJahr);
				int bJahr = Integer.parseInt(bisJahr); 			// Eingabe in Integer umwandeln
				int startday = 2; 								// 2 für Montag
				int endday = 7;

				Calendar startdate = Calendar.getInstance();
				startdate.setWeekDate(vJahr, vonint, startday); 			// von Datum setzen
				Date vonDate = startdate.getTime(); 						// in Java Date speichern
				java.sql.Date von = new java.sql.Date(vonDate.getTime()); 	// in SQL Date umwandeln

				Calendar enddate = Calendar.getInstance();
				enddate.setWeekDate(bJahr, bisint, endday); 				// bis Datum setzen
				enddate.getTime();
				Date bisDate = enddate.getTime(); 							// in Java Date speichern
				java.sql.Date bis = new java.sql.Date(bisDate.getTime()); 	// in SQL Date umwandeln

				try {

					jdbc.Mitarbeiterzuteilen(PersNr, von, bis, projnr);

					// JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ",
					// "Bestätigen", JOptionPane.OK_CANCEL_OPTION);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				textField_3.setText(null);
				textField_4.setText(null);

			}

		});
		frame6.getContentPane().setLayout(null);
		frame6.getContentPane().add(btnBesttigen);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 242, 393, 196);
		frame6.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(583, 242, 285, 196);
		frame6.getContentPane().add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
		table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));

		label = new JLabel("Projektnummer");
		label.setBounds(641, 90, 74, 14);
		frame6.getContentPane().add(label);

		label_1 = new JLabel("von:");
		label_1.setBounds(652, 149, 21, 14);
		frame6.getContentPane().add(label_1);

		label_2 = new JLabel("bis:");
		label_2.setBounds(763, 149, 18, 14);
		frame6.getContentPane().add(label_2);

		textField_2 = new JTextField();
		textField_2.setBounds(641, 110, 96, 20);
		frame6.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(641, 171, 96, 20);
		frame6.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(747, 171, 96, 20);
		frame6.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		JButton button = new JButton("entfernen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						/*
						 * try { GUIMitarbeiterENTF window = new GUIMitarbeiterENTF(new JDBC_MariaDB());
						 * window.frame8.setVisible(true); } catch (Exception e) { e.printStackTrace();
						 * }
						 */
						int row = 0;
						String PersNr = null;

						try {

							row = table.getSelectedRow();
							PersNr = table.getValueAt(row, 0).toString();

							jdbc.deleteEmployee(PersNr);
							System.out.println(PersNr);

						} catch (Exception e1) {

							e1.printStackTrace();
						}
						table.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
					}

				});
				// frame3.dispose();

			}

		});
		button.setBounds(59, 149, 89, 23);
		frame6.getContentPane().add(button);

		JButton button_1 = new JButton("Hinzuf\u00FCgen");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame6.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIMitarbeiterHINZU window = new GUIMitarbeiterHINZU(jdbc);
							window.frame9.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});
				// frame3.dispose();

			}
		});

		button_1.setBounds(59, 129, 89, 23);
		frame6.getContentPane().add(button_1);

		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content = null;
				int row = 0;
				String PersoNr = null;

				try {

					row = table.getSelectedRow();
					PersoNr = table.getValueAt(row, 0).toString();

					List<String> tablecontent = new ArrayList<String>();
					tablecontent.clear();

					for (int column = 1; column <= table.getColumnCount(); column++) {
						content = null;

						if (table.getModel().getValueAt(row, column - 1) == null) {

							tablecontent.add(column - 1, "");
						} else {
							content = table.getModel().getValueAt(row, column - 1).toString();
							tablecontent.add(column - 1, content);

						}

					}

					// System.out.println(tablecontent);

					jdbc.updateMitarbeiter(PersoNr, tablecontent);
					JOptionPane.showMessageDialog(null, "Update war erfolgreich ", "Bestätigen",
							JOptionPane.OK_CANCEL_OPTION);

				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}
		});
		button_2.setBounds(158, 129, 67, 23);
		frame6.getContentPane().add(button_2);

		JButton button_3 = new JButton("Mitarbeiter nicht verf\u00FCgbar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();
				int column = 0;
				String content = table.getValueAt(row, column).toString();
				int PersNr = Integer.parseInt(content);

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIvon_bis window = new GUIvon_bis(jdbc, PersNr);
							window.frame12.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_3.setBounds(245, 129, 163, 23);
		frame6.getContentPane().add(button_3);

		JButton button_5 = new JButton("Zuteilung Detailansicht");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		button_5.setBounds(59, 197, 145, 23);
		frame6.getContentPane().add(button_5);

		textField = new JTextField();
		textField.setBounds(641, 198, 96, 20);
		frame6.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(747, 198, 96, 20);
		frame6.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblKw = new JLabel("KW:");
		lblKw.setBounds(583, 174, 48, 14);
		frame6.getContentPane().add(lblKw);

		JLabel lblJahr = new JLabel("Jahr:");
		lblJahr.setBounds(583, 201, 48, 14);
		frame6.getContentPane().add(lblJahr);

		JButton btnNewButton = new JButton("Personalbedarf gesamt");
		btnNewButton.setBounds(337, 11, 213, 57);
		frame6.getContentPane().add(btnNewButton);

		btnPersonalbedarfProProjekt = new JButton("Personalbedarf pro Projekt");
		btnPersonalbedarfProProjekt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIeinzelPERS window = new GUIeinzelPERS(jdbc);
							window.frameEinzelPerso.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnPersonalbedarfProProjekt.setBounds(583, 449, 198, 23);
		frame6.getContentPane().add(btnPersonalbedarfProProjekt);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame6.dispose();

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIPersonalbedarf window = new GUIPersonalbedarf(jdbc);
							window.frmPersonalUndProjektmanager.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		menuBar = new JMenuBar();
		frame6.setJMenuBar(menuBar);

		btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame6.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIstartmenue2 window = new GUIstartmenue2(jdbc);
							window.frame2.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		menuBar.add(btnZurck);

		JButton btnMitarbeiterdetailsBearbeiten = new JButton("Mitarbeiterdetails bearbeiten");
		menuBar.add(btnMitarbeiterdetailsBearbeiten);
	}
}