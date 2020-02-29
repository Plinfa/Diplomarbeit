import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Font;

//Wartecker Marcel
public class GUIstartmenue2 {

	public JFrame frame2;
	private JFrame frame25;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table_2;
	private JTable table_3;
	private JPanel Startmenue;
	private JPanel Projektplanung;
	private JPanel Mitarbeiterplanung;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTable table_4;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUIstartmenue2(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		

		
		frame2 = new JFrame();
		frame2.getContentPane().setBackground(Color.RED);
		frame2.getContentPane().setBounds(new Rectangle(2147483647, 2147483647, 2147483647, 2147483647));
		frame2.setBackground(Color.DARK_GRAY);
		frame2.getContentPane().setSize(new Dimension(2147483647, 2147483647));
		frame2.setTitle("Personal- und Projektmanager");
		frame2.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIstartmenue2.class.getResource("/ressources/EQOS.jpg")));
		frame2.setBounds(100, 100, 1118, 627);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(new CardLayout(0, 0));
		
		Startmenue = new JPanel();
		Startmenue.setBackground(SystemColor.menu);
		Startmenue.setSize(2147483647, 2147483647);
		frame2.getContentPane().add(Startmenue, "name_1799136513500");
		
		JButton btnProjekte = new JButton("Projektplanung");
		btnProjekte.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JProgressBar progressBar = new JProgressBar(0, 100);
		Calendar c1 = Calendar.getInstance();
		
		int week = c1.get(Calendar.WEEK_OF_YEAR)+1;
		int year= c1.get(Calendar.YEAR);
		c1.setWeekDate(year, week, 2);
		
		
		
		Date abfrage=c1.getTime();
		java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
		
		progressBar.setValue(jdbc.auslastung(abfrage1, 0));
		progressBar.setStringPainted(true);
		
		JButton btnMitarbeiter = new JButton("Mitarbeiterplanung");
		btnMitarbeiter.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		
		JLabel lblAuslastungNchsteKw = new JLabel("Auslastung n\u00E4chste KW:");
		lblAuslastungNchsteKw.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblAuslastungbernchsteKw = new JLabel("Auslastung \u00FCbern\u00E4chste KW:");
		lblAuslastungbernchsteKw.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JProgressBar progressBar_1 = new JProgressBar(0, 100);
		
		progressBar_1.setValue(30);
		progressBar_1.setStringPainted(true);
		
		Calendar calendar = Calendar.getInstance();
		
		 int week1 = calendar.get(Calendar.WEEK_OF_YEAR)+2;
		 int year1= calendar.get(Calendar.YEAR);
		calendar.setWeekDate(year1, week1, 2);
		
		
		
		Date abfrage2=calendar.getTime();
		java.sql.Date abfrage3 = new java.sql.Date(abfrage2.getTime());
		
		progressBar_1.setValue(jdbc.auslastung(abfrage3, 0));
		progressBar_1.setStringPainted(true);
		
		JMenuBar menuBar = new JMenuBar();
		
		JButton btnAbmelden = new JButton("Abmelden");
		btnAbmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame25= new JFrame("Abmelden");
				if (JOptionPane.showConfirmDialog(frame25, "Wirklich abmelden?", "Personal- und Projektmanager", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								GUIWillkommen2 window = new GUIWillkommen2("Personal- und Projektmanager", new JDBC_MariaDB());
								window.frame.setVisible(true);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					frame2.dispose();
				}
			}
		});
		
		menuBar.add(btnAbmelden);
		
		JButton btnPasswortndern = new JButton("Passwort \u00E4ndern");
		menuBar.add(btnPasswortndern);
		GroupLayout gl_Startmenue = new GroupLayout(Startmenue);
		gl_Startmenue.setHorizontalGroup(
			gl_Startmenue.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
				.addGroup(gl_Startmenue.createSequentialGroup()
					.addGap(88)
					.addComponent(btnProjekte, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addGap(79)
					.addComponent(btnMitarbeiter, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addGap(97))
				.addGroup(gl_Startmenue.createSequentialGroup()
					.addGap(88)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addGap(21)
					.addGroup(gl_Startmenue.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAuslastungNchsteKw)
						.addComponent(lblAuslastungbernchsteKw))
					.addGap(18)
					.addGroup(gl_Startmenue.createParallelGroup(Alignment.TRAILING)
						.addComponent(progressBar_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
					.addGap(53))
		);
		gl_Startmenue.setVerticalGroup(
			gl_Startmenue.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Startmenue.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(102)
					.addGroup(gl_Startmenue.createParallelGroup(Alignment.LEADING)
						.addComponent(btnProjekte, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMitarbeiter, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_Startmenue.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
						.addGroup(gl_Startmenue.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_Startmenue.createParallelGroup(Alignment.BASELINE)
								.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAuslastungNchsteKw, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(38)
							.addGroup(gl_Startmenue.createParallelGroup(Alignment.BASELINE)
								.addComponent(progressBar_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAuslastungbernchsteKw, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
					.addGap(24))
		);
		Startmenue.setLayout(gl_Startmenue);
		btnPasswortndern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIchangePasswort window = new GUIchangePasswort(jdbc);
							window.frame7.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnMitarbeiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Startmenue.setVisible(false);
				Mitarbeiterplanung.setVisible(true);
				/*frame2.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIgruppeODEReinzeln window = new GUIgruppeODEReinzeln(jdbc);
							window.frame6.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});*/
			}
		});
		btnProjekte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Startmenue.setVisible(false);
				Projektplanung.setVisible(true);
				
				/*EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIProjekte window = new GUIProjekte(jdbc);
							window.frame5.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});*/
				
				
			}
		});
		
		Projektplanung = new JPanel();
		frame2.getContentPane().add(Projektplanung, "name_1804404374300");
		
		JMenuBar menuBar_1 = new JMenuBar();
		
		JButton button = new JButton("zur\u00FCck");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Startmenue.setVisible(true);
				Projektplanung.setVisible(false);
			}
		});
		menuBar_1.add(button);
		
		JButton button_4 = new JButton("Projekte bearbeiten");
		menuBar_1.add(button_4);
		
		
		JButton button_1 = new JButton("Hinzuf\u00FCgen");
		
		JButton button_2 = new JButton("l\u00F6schen");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=0;
				String ProjNr=null;
				
				try {
				
				row=table_1.getSelectedRow();
				ProjNr=table_1.getValueAt(row, 0).toString();
				
				int ProjektNr = Integer.parseInt(ProjNr);
				if (JOptionPane.showConfirmDialog(frame2, "Projekt und alle Einträge wirklich löschen?", "Personal- und Projektmanager", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) 
				{
					jdbc.deleteProject(ProjektNr);
				}
				else {
					
				}
				
				
				//System.out.println(ProjektNr);
				
				}catch(Exception e1) {
				
					e1.printStackTrace();
				}
				table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
				table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
				table_3.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
			}
		});
		
		JButton button_3 = new JButton("Projektplanung Diagramm");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUI_Gantt_ChartDesigner window = new GUI_Gantt_ChartDesigner(jdbc,jdbc.zeitplanung());
							window.frame4.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JButton button_5 = new JButton("Personalbedarf pro Projekt pro Woche planen");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				int column = 0;
				String content = table_1.getValueAt(row, column).toString();
				int ProjNr = Integer.parseInt(content);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIPersB_planen window = new GUIPersB_planen(jdbc, ProjNr);
							window.frame27.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setRowHeight(25);
		table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		GroupLayout gl_Projektplanung = new GroupLayout(Projektplanung);
		gl_Projektplanung.setHorizontalGroup(
			gl_Projektplanung.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar_1, GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
				.addGroup(gl_Projektplanung.createSequentialGroup()
					.addGap(61)
					.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(159)
					.addComponent(button_5, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(button_3, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addGap(60))
				.addGroup(gl_Projektplanung.createSequentialGroup()
					.addGap(61)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
					.addGap(60))
		);
		gl_Projektplanung.setVerticalGroup(
			gl_Projektplanung.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Projektplanung.createSequentialGroup()
					.addComponent(menuBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_Projektplanung.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1)
						.addComponent(button_2)
						.addComponent(button_5)
						.addComponent(button_3))
					.addGap(11)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
					.addGap(32))
		);
		Projektplanung.setLayout(gl_Projektplanung);
		
		Mitarbeiterplanung = new JPanel();
		frame2.getContentPane().add(Mitarbeiterplanung, "name_1809842464300");
		
		JButton button_6 = new JButton("Best\u00E4tigen");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table_2.getSelectedRow();
				int column = 0;
				String content = table_2.getValueAt(row, column).toString();
				int PersNr = Integer.parseInt(content);

				// int von=0;
				// int bis=0;
				int projnr = 0;
				projnr = Integer.parseInt(textField.getText());
				// von=Integer.parseInt(textField_3.getText());
				// bis=Integer.parseInt(textField_4.getText());
				String vonEingabe = null;
				String bisEingabe = null;
				String vonJahr = null;
				String bisJahr = null;
				vonEingabe = textField_1.getText();
				bisEingabe = textField_2.getText();
				vonJahr = textField_3.getText();
				bisJahr = textField_4.getText(); 				// Eingabe
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

				textField_1.setText(null);
				textField_2.setText(null);
			}
		});
		
		JLabel label_1 = new JLabel("Projektnummer");
		
		JLabel label_2 = new JLabel("von:");
		
		JLabel label_3 = new JLabel("bis:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JButton button_7 = new JButton("entfernen");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						int row = 0;
						String PersNr = null;

						try {

							row = table_2.getSelectedRow();
							PersNr = table_2.getValueAt(row, 0).toString();
							if (JOptionPane.showConfirmDialog(frame2, "Mitarbeiter und alle Einträge wirklich löschen?", "Personal- und Projektmanager", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) 
							{
								jdbc.deleteEmployee(PersNr);
							}
							else {
								
							}
							
							//System.out.println(PersNr);

						} catch (Exception e1) {

							e1.printStackTrace();
						}
						table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
						table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
						table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
						table_3.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
					}

				});
			}
		});
		
		JButton button_8 = new JButton("Hinzuf\u00FCgen");
		
		JButton button_10 = new JButton("Mitarbeiter nicht verf\u00FCgbar");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int row = table_2.getSelectedRow();
				if (row==-1) {
					JOptionPane.showMessageDialog(null, "Bitte Mitarbeiter auswählen", "Fehler", JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					int column = 0;
					String content = table_2.getValueAt(row, column).toString();
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

			}
			
		});
		
		JButton button_11 = new JButton("Dienstplan");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table_2.getSelectedRow();
				int column = 0;
				
				
				if (row==-1) {
					JOptionPane.showMessageDialog(null, "Bitte Mitarbeiter auswählen", "Fehler", JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					String content = table_2.getValueAt(row, column).toString();
					int PersNr = Integer.parseInt(content);
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								
								
								jdbc.zuteilungdetailansicht(PersNr);
								GUI_Gantt_ChartDesigner window = new GUI_Gantt_ChartDesigner(jdbc, jdbc.zuteilungdetailansicht(PersNr));
								window.frame4.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								GUIDienstplan window = new GUIDienstplan(jdbc, PersNr);
								window.frameDienstplan.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				
				
			}
		});
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel label_4 = new JLabel("KW:");
		
		JLabel label_5 = new JLabel("Jahr:");
		
		JButton button_12 = new JButton("Personalbedarf gesamt");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		
		JButton button_13 = new JButton("Personalbedarf pro Projekt");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_3.getSelectedRow();
				int column = 0;
				
				if (row==-1) {
					JOptionPane.showMessageDialog(null, "Bitte Projekt auswählen", "Fehler", JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					String content = table_3.getValueAt(row, column).toString();
					int ProjNr = Integer.parseInt(content);
				
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								GUIeinzelPERS window = new GUIeinzelPERS(jdbc, ProjNr);
								window.frameEinzelPerso.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
					
				}
				
				
			}
		});
		
		JButton button_15 = new JButton("Partien verwalten");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		
		JRadioButton radioButton = new JRadioButton("einzeln");
		
		JRadioButton radioButton_1 = new JRadioButton("Partie");
		
		ButtonGroup gruppe = new ButtonGroup();
		
		radioButton.setSelected(true);
		 
        //JRadioButtons werden zur ButtonGroup hinzugefügt
        gruppe.add(radioButton);
        gruppe.add(radioButton_1);
        
        radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectPartieLeiter()));
			}
		});
        
        radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
			}
		});
		
		JMenuBar menuBar_2 = new JMenuBar();
		
		JButton button_16 = new JButton("zur\u00FCck");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mitarbeiterplanung.setVisible(false);
				Startmenue.setVisible(true);
				progressBar.setValue(jdbc.auslastung(abfrage1, 0));
				progressBar_1.setValue(jdbc.auslastung(abfrage3, 0));
				
			}
		});
		menuBar_2.add(button_16);
		
		JButton button_17 = new JButton("Mitarbeiterdetails bearbeiten");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIBearbeitungMitarbeiter window = new GUIBearbeitungMitarbeiter(jdbc);
							window.frame111.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		menuBar_2.add(button_17);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
		table_3.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		
		JLabel lblMitarbeiter = new JLabel("Mitarbeiter");
		
		JLabel lblZuteilenZuProjekt = new JLabel("Zuteilen zu Projekt");
		
		JLabel lblOder = new JLabel("oder");
		
		JLabel lblZuteilen = new JLabel("zuteilen?");
		
		JLabel lblZuerstMitarbeiterOder = new JLabel("zuerst Mitarbeiter oder Partie aus Tabelle links ausw\u00E4hlen");
		
		JLabel lblUntersttzendeDatenFr = new JLabel("unterst\u00FCtzende Daten f\u00FCr die Zuteilung:");
		GroupLayout gl_Mitarbeiterplanung = new GroupLayout(Mitarbeiterplanung);
		gl_Mitarbeiterplanung.setHorizontalGroup(
			gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar_2, GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
				.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
					.addGap(99)
					.addComponent(lblMitarbeiter, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(426)
					.addComponent(lblZuteilenZuProjekt, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
					.addGap(316))
				.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
					.addGap(51)
					.addComponent(button_8, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addGap(17)
					.addComponent(button_7, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(button_10, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
					.addGap(135)
					.addComponent(radioButton, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addGap(23)
					.addComponent(lblOder, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addGap(3)
					.addComponent(radioButton_1, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblZuteilen, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addGap(168))
				.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
					.addGap(51)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
					.addGap(86)
					.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(133)
							.addComponent(label_1))
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(49)
							.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
									.addGap(30)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(49)
							.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
									.addGap(30)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(button_6, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
							.addGap(136))
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
							.addGap(32))
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(79)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(68)
							.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(133)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(49)
							.addComponent(lblZuerstMitarbeiterOder, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(49)
							.addComponent(lblUntersttzendeDatenFr, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
					.addGap(51)
					.addComponent(button_11, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
					.addGap(16)
					.addComponent(button_15, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
					.addGap(131)
					.addComponent(button_12, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(button_13, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addGap(44))
		);
		gl_Mitarbeiterplanung.setVerticalGroup(
			gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
					.addComponent(menuBar_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMitarbeiter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblZuteilenZuProjekt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(20)
					.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
						.addComponent(button_8)
						.addComponent(button_7)
						.addComponent(button_10)
						.addComponent(radioButton)
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(4)
							.addComponent(lblOder))
						.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.BASELINE)
							.addComponent(radioButton_1)
							.addComponent(lblZuteilen)))
					.addGap(11)
					.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
							.addGap(8)
							.addComponent(lblZuerstMitarbeiterOder)
							.addGap(32)
							.addComponent(label_1)
							.addGap(3)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
								.addComponent(label_3)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(4)
							.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
									.addGap(3)
									.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
										.addComponent(label_5)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_Mitarbeiterplanung.createSequentialGroup()
									.addGap(3)
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(button_6))
							.addGap(11)
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(lblUntersttzendeDatenFr)))
					.addGap(11)
					.addGroup(gl_Mitarbeiterplanung.createParallelGroup(Alignment.LEADING)
						.addComponent(button_11)
						.addComponent(button_15)
						.addComponent(button_12)
						.addComponent(button_13))
					.addGap(32))
		);
		Mitarbeiterplanung.setLayout(gl_Mitarbeiterplanung);
		
		JPanel MitarbeiterHINZU = new JPanel();
		frame2.getContentPane().add(MitarbeiterHINZU, "name_5368979385300");
		MitarbeiterHINZU.setLayout(null);
		
		JLabel label_6 = new JLabel("PersNr");
		label_6.setBounds(124, 69, 46, 14);
		MitarbeiterHINZU.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(124, 89, 96, 20);
		MitarbeiterHINZU.add(textField_5);
		
		JLabel label_7 = new JLabel("Name");
		label_7.setBounds(124, 120, 46, 14);
		MitarbeiterHINZU.add(label_7);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(124, 140, 96, 20);
		MitarbeiterHINZU.add(textField_6);
		
		JLabel label_8 = new JLabel("Vorname");
		label_8.setBounds(124, 171, 53, 14);
		MitarbeiterHINZU.add(label_8);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(124, 191, 96, 20);
		MitarbeiterHINZU.add(textField_7);
		
		JButton button_9 = new JButton("Eingabe");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Name=null;
				String Nachname=null;
				int PersoNr=0;
				
					
					try {
						PersoNr=Integer.parseInt(textField_5.getText());
						Name=textField_6.getText();
						Nachname=textField_7.getText();
						
						jdbc.insertEmployee(PersoNr, Name, Nachname);
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField_5.setText(null);
					textField_6.setText(null);
					textField_7.setText(null);
					
					Mitarbeiterplanung.setVisible(true);
					MitarbeiterHINZU.setVisible(false);
					
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
					table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
					table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
					table_3.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
					
				
			}
		});
		button_9.setBounds(266, 248, 89, 23);
		MitarbeiterHINZU.add(button_9);
		
		JMenuBar menuBar_3 = new JMenuBar();
		menuBar_3.setBounds(0, 0, 1102, 25);
		MitarbeiterHINZU.add(menuBar_3);
		
		JButton btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MitarbeiterHINZU.setVisible(false);
				Mitarbeiterplanung.setVisible(true);
			}
		});
		menuBar_3.add(btnZurck);
		
		JPanel ProjekteHINZU = new JPanel();
		frame2.getContentPane().add(ProjekteHINZU, "name_995074335200");
		
		JLabel label = new JLabel("ProjektNr");
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JLabel label_9 = new JLabel("Projektname");
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		JLabel label_10 = new JLabel("Ort");
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		
		JButton button_18 = new JButton("Eingabe");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ProjektNr=0;
				String Projektname=null;
				String Ort=null;
				
				ProjektNr=Integer.parseInt(textField_8.getText());
				Projektname=textField_9.getText();
						
				Ort=textField_10.getText();
				String vonEingabe = null;
				String bisEingabe = null;
				String vonJahr = null;
				String bisJahr = null;
				vonEingabe = textField_13.getText();
				bisEingabe = textField_16.getText();
				vonJahr = textField_14.getText();
				bisJahr = textField_15.getText(); 				// Eingabe
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
				java.sql.Date bis = new java.sql.Date(bisDate.getTime());
					
					try {
						
						

				        
						
						jdbc.insertProject(ProjektNr, Projektname, Ort, von, bis);
						
					
						
						
						JOptionPane.showMessageDialog(null, "Projekt erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					textField_8.setText(null);
					textField_9.setText(null);
				
					textField_10.setText(null);
					textField_13.setText(null);
					textField_16.setText(null);
					
					table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
					table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
					table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
					table_3.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
					
					Projektplanung.setVisible(true);
					ProjekteHINZU.setVisible(false);
				
			}
		});
		
		JLabel label_13 = new JLabel("Jahr:");
		
		JLabel label_14 = new JLabel("KW:");
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		
		JLabel label_15 = new JLabel("bis:");
		
		JLabel label_16 = new JLabel("von:");
		
		JMenuBar menuBar_4 = new JMenuBar();
		
		JButton btnZurck_1 = new JButton("zur\u00FCck");
		btnZurck_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjekteHINZU.setVisible(false);
				Projektplanung.setVisible(true);
			}
		});
		menuBar_4.add(btnZurck_1);
		GroupLayout gl_ProjekteHINZU = new GroupLayout(ProjekteHINZU);
		gl_ProjekteHINZU.setHorizontalGroup(
			gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar_4, GroupLayout.PREFERRED_SIZE, 1102, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_ProjekteHINZU.createSequentialGroup()
					.addGap(65)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_ProjekteHINZU.createSequentialGroup()
					.addGap(65)
					.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_ProjekteHINZU.createSequentialGroup()
					.addGap(65)
					.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_ProjekteHINZU.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.TRAILING)
						.addComponent(button_18, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_ProjekteHINZU.createSequentialGroup()
								.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(140)
								.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGap(86)
								.addComponent(label_15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, gl_ProjekteHINZU.createSequentialGroup()
								.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
									.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(34)
								.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_ProjekteHINZU.createSequentialGroup()
										.addGap(5)
										.addComponent(label_14))
									.addComponent(label_13))
								.addGap(10)
								.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
		);
		gl_ProjekteHINZU.setVerticalGroup(
			gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ProjekteHINZU.createSequentialGroup()
					.addComponent(menuBar_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ProjekteHINZU.createSequentialGroup()
							.addGap(14)
							.addComponent(label)
							.addGap(9)
							.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_ProjekteHINZU.createSequentialGroup()
									.addGap(19)
									.addComponent(label_16))))
						.addGroup(gl_ProjekteHINZU.createSequentialGroup()
							.addGap(56)
							.addComponent(label_15)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ProjekteHINZU.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ProjekteHINZU.createSequentialGroup()
							.addComponent(label_9)
							.addGap(8)
							.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ProjekteHINZU.createSequentialGroup()
							.addGap(8)
							.addComponent(label_14)
							.addGap(13)
							.addComponent(label_13))
						.addGroup(gl_ProjekteHINZU.createSequentialGroup()
							.addGap(5)
							.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ProjekteHINZU.createSequentialGroup()
							.addGap(5)
							.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(5)
					.addComponent(label_10)
					.addGap(11)
					.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button_18)
					.addGap(344))
		);
		ProjekteHINZU.setLayout(gl_ProjekteHINZU);
		
		JPanel Bearbeitung_Projekte = new JPanel();
		frame2.getContentPane().add(Bearbeitung_Projekte, "name_4173580015200");
		
		JButton btnNewButton = new JButton("Änderungen speichern");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row=0;
				
				String ProjektNr=null;
				String Name=null;
				String Ort=null;
				String von=null;
				String bis=null;
				
				
				try {
					 row = table_4.getSelectedRow();
					 ProjektNr = table_4.getValueAt(row, 0).toString();
					 Name= table_4.getValueAt(row, 1).toString();
					 Ort = table_4.getValueAt(row, 2).toString();
					 von = table_4.getValueAt(row, 3).toString();
					 bis = table_4.getValueAt(row, 4).toString();
					 
					 jdbc.updateProjekt( ProjektNr,  Name,  Ort,  von,  bis );
				
				}catch(Exception e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		table_4 = new JTable();
		table_4.setRowHeight(30);
		table_4.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		scrollPane_4.setViewportView(table_4);
		
		JMenuBar menuBar_5 = new JMenuBar();
		
		JButton btnZurck_2 = new JButton("zur\u00FCck");
		btnZurck_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bearbeitung_Projekte.setVisible(false);
				Projektplanung.setVisible(true);
				table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
				table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
				table_3.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
			}
		});
		menuBar_5.add(btnZurck_2);
		
		JLabel label_11 = new JLabel("\u00C4nderungen mit Enter best\u00E4tigen und dann:");
		GroupLayout gl_Bearbeitung_Projekte = new GroupLayout(Bearbeitung_Projekte);
		gl_Bearbeitung_Projekte.setHorizontalGroup(
			gl_Bearbeitung_Projekte.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar_5, GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
				.addGroup(gl_Bearbeitung_Projekte.createSequentialGroup()
					.addGap(160)
					.addComponent(label_11, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
					.addGap(136))
				.addGroup(gl_Bearbeitung_Projekte.createSequentialGroup()
					.addGap(123)
					.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
					.addGap(136))
		);
		gl_Bearbeitung_Projekte.setVerticalGroup(
			gl_Bearbeitung_Projekte.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Bearbeitung_Projekte.createSequentialGroup()
					.addComponent(menuBar_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_Bearbeitung_Projekte.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Bearbeitung_Projekte.createSequentialGroup()
							.addGap(4)
							.addComponent(label_11))
						.addComponent(btnNewButton))
					.addGap(34)
					.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
					.addGap(39))
		);
		Bearbeitung_Projekte.setLayout(gl_Bearbeitung_Projekte);
		
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Mitarbeiterplanung.setVisible(false);
				MitarbeiterHINZU.setVisible(true);
								
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Bearbeitung_Projekte.setVisible(true);
				Projektplanung.setVisible(false);
				
				
				
				
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Projektplanung.setVisible(false);
				ProjekteHINZU.setVisible(true);
			}
		});
	}
	}
