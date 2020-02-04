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

//Wartecker Marcel
public class GUIstartmenue2 {

	public JFrame frame2;
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
		frame2.setTitle("Personal- und Projektmanager");
		frame2.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIstartmenue2.class.getResource("/ressources/EQOS.jpg")));
		frame2.setBounds(100, 100, 1118, 627);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(new CardLayout(0, 0));
		
		Startmenue = new JPanel();
		frame2.getContentPane().add(Startmenue, "name_1799136513500");
		Startmenue.setLayout(null);
		
		JButton btnProjekte = new JButton("Projektplanung");
		btnProjekte.setBounds(78, 148, 429, 100);
		Startmenue.add(btnProjekte);
		
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(767, 294, 146, 17);
		Startmenue.add(progressBar);
		
		progressBar.setValue(2);
		progressBar.setStringPainted(true);
		
		JButton btnMitarbeiter = new JButton("Mitarbeiterplanung");
		btnMitarbeiter.setBounds(767, 236, 125, 23);
		Startmenue.add(btnMitarbeiter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 278, 419, 198);
		Startmenue.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		
		JLabel lblAuslastungNchsteKw = new JLabel("Auslastung n\u00E4chste KW:");
		lblAuslastungNchsteKw.setBounds(630, 294, 118, 14);
		Startmenue.add(lblAuslastungNchsteKw);
		
		JLabel lblAuslastungbernchsteKw = new JLabel("Auslastung \u00FCbern\u00E4chste KW:");
		lblAuslastungbernchsteKw.setBounds(608, 331, 140, 14);
		Startmenue.add(lblAuslastungbernchsteKw);
		
		JProgressBar progressBar_1 = new JProgressBar(0, 100);
		progressBar_1.setBounds(767, 331, 146, 17);
		Startmenue.add(progressBar_1);
		//progressBar_1.setValue(jdbc.auslastung(abfrage1, ProjNr));
		progressBar_1.setStringPainted(true);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1102, 25);
		Startmenue.add(menuBar);
		
		JButton btnAbmelden = new JButton("Abmelden");
		btnAbmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame2= new JFrame("Abmelden");
				if (JOptionPane.showConfirmDialog(frame2, "Wirklich abmelden?", "Personal- und Projektmanager", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
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
				}
			}
		});
		
		menuBar.add(btnAbmelden);
		
		JButton btnPasswortndern = new JButton("Passwort \u00E4ndern");
		menuBar.add(btnPasswortndern);
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
				frame2.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIgruppeODEReinzeln window = new GUIgruppeODEReinzeln(jdbc);
							window.frame6.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnProjekte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIProjekte window = new GUIProjekte(jdbc);
							window.frame5.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
			}
		});
		
		Projektplanung = new JPanel();
		frame2.getContentPane().add(Projektplanung, "name_1804404374300");
		Projektplanung.setLayout(null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 1101, 25);
		Projektplanung.add(menuBar_1);
		
		JButton button = new JButton("zur\u00FCck");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Startmenue.setVisible(true);
				Projektplanung.setVisible(false);
			}
		});
		menuBar_1.add(button);
		
		JLabel label = new JLabel("Projekte");
		label.setBounds(200, 53, 48, 14);
		Projektplanung.add(label);
		
		JButton button_1 = new JButton("Hinzuf\u00FCgen");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIProjektHINZU window = new GUIProjektHINZU(jdbc);
							window.frame11.setVisible(true);
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_1.setBounds(92, 92, 89, 23);
		Projektplanung.add(button_1);
		
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
				
				table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
			}
		});
		button_2.setBounds(92, 126, 89, 23);
		Projektplanung.add(button_2);
		
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
		button_3.setBounds(649, 92, 219, 23);
		Projektplanung.add(button_3);
		
		JButton button_4 = new JButton("Update");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String content= null;
				int row=0;
				String ProjektNr=null;
				
				try {
				
				row=table_1.getSelectedRow();
				ProjektNr=table_1.getValueAt(row, 0).toString();
				
				List<String> tablecontent = new ArrayList<String>();
				tablecontent.clear();
				
				
				
				for( int column=1;column<=table_1.getColumnCount();column++) {
					content=null;
					
					
					
					if(table_1.getModel().getValueAt(row, column-1)==null) {
						
						tablecontent.add(column-1, "");
					}
					else {
						content=table_1.getModel().getValueAt(row, column-1).toString();
						tablecontent.add(column-1, content);
					
					}
					
					
				}
				
				//System.out.println(tablecontent);
				
				jdbc.updateProjekt(ProjektNr,tablecontent);
				//JOptionPane.showMessageDialog(null, "Update war erfolgreich ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
				
				}catch(Exception e1) {
				
					e1.printStackTrace();
				}
			}
		});
		button_4.setBounds(191, 92, 89, 23);
		Projektplanung.add(button_4);
		
		JButton button_5 = new JButton("Pers.Bedarf pro Projekt pro Woche planen");
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
		button_5.setBounds(301, 92, 312, 23);
		Projektplanung.add(button_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(92, 170, 816, 276);
		Projektplanung.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		
		Mitarbeiterplanung = new JPanel();
		frame2.getContentPane().add(Mitarbeiterplanung, "name_1809842464300");
		Mitarbeiterplanung.setLayout(null);
		
		JButton button_6 = new JButton("Best\u00E4tigen");
		button_6.setBounds(870, 241, 96, 23);
		Mitarbeiterplanung.add(button_6);
		
		JLabel label_1 = new JLabel("Projektnummer");
		label_1.setBounds(712, 134, 74, 14);
		Mitarbeiterplanung.add(label_1);
		
		JLabel label_2 = new JLabel("von:");
		label_2.setBounds(723, 193, 38, 14);
		Mitarbeiterplanung.add(label_2);
		
		JLabel label_3 = new JLabel("bis:");
		label_3.setBounds(834, 193, 38, 14);
		Mitarbeiterplanung.add(label_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(712, 154, 96, 20);
		Mitarbeiterplanung.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(658, 217, 96, 20);
		Mitarbeiterplanung.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(764, 217, 96, 20);
		Mitarbeiterplanung.add(textField_2);
		
		JButton button_7 = new JButton("entfernen");
		button_7.setBounds(130, 193, 103, 23);
		Mitarbeiterplanung.add(button_7);
		
		JButton button_8 = new JButton("Hinzuf\u00FCgen");
		button_8.setBounds(130, 173, 103, 23);
		Mitarbeiterplanung.add(button_8);
		
		JButton button_9 = new JButton("Update");
		button_9.setBounds(243, 173, 79, 23);
		Mitarbeiterplanung.add(button_9);
		
		JButton button_10 = new JButton("Mitarbeiter nicht verf\u00FCgbar");
		button_10.setBounds(332, 173, 191, 23);
		Mitarbeiterplanung.add(button_10);
		
		JButton button_11 = new JButton("Zuteilung Detailansicht");
		button_11.setBounds(130, 241, 145, 23);
		Mitarbeiterplanung.add(button_11);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(658, 244, 96, 20);
		Mitarbeiterplanung.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(764, 244, 96, 20);
		Mitarbeiterplanung.add(textField_4);
		
		JLabel label_4 = new JLabel("KW:");
		label_4.setBounds(628, 217, 48, 14);
		Mitarbeiterplanung.add(label_4);
		
		JLabel label_5 = new JLabel("Jahr:");
		label_5.setBounds(628, 244, 48, 14);
		Mitarbeiterplanung.add(label_5);
		
		JButton button_12 = new JButton("Personalbedarf gesamt");
		button_12.setBounds(615, 493, 213, 33);
		Mitarbeiterplanung.add(button_12);
		
		JButton button_13 = new JButton("Personalbedarf pro Projekt");
		button_13.setBounds(831, 493, 198, 23);
		Mitarbeiterplanung.add(button_13);
		
		JButton button_14 = new JButton("Zuteilung per Email senden (Kann Ziel)");
		button_14.setBounds(300, 241, 283, 23);
		Mitarbeiterplanung.add(button_14);
		
		JButton button_15 = new JButton("Partien verwalten");
		button_15.setBounds(140, 493, 168, 23);
		Mitarbeiterplanung.add(button_15);
		
		JRadioButton radioButton = new JRadioButton("einzeln");
		radioButton.setBounds(629, 94, 109, 23);
		Mitarbeiterplanung.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Partie");
		radioButton_1.setBounds(739, 94, 109, 23);
		Mitarbeiterplanung.add(radioButton_1);
		
		ButtonGroup gruppe = new ButtonGroup();
		 
        //JRadioButtons werden zur ButtonGroup hinzugefügt
        gruppe.add(radioButton);
        gruppe.add(radioButton_1);
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBounds(0, 0, 1102, 25);
		Mitarbeiterplanung.add(menuBar_2);
		
		JButton button_16 = new JButton("zur\u00FCck");
		menuBar_2.add(button_16);
		
		JButton button_17 = new JButton("Mitarbeiterdetails bearbeiten");
		menuBar_2.add(button_17);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(140, 297, 442, 176);
		Mitarbeiterplanung.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(628, 304, 361, 178);
		Mitarbeiterplanung.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		table_2.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
		table_3.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
	}
	}
