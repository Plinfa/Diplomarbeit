import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
//Wartecker Marcel
public class GUIstartmenue2 {

	public JFrame frame2;
	private JTable table;

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
		
		JButton btnMitarbeiter = new JButton("Mitarbeiter");
		btnMitarbeiter.setBounds(561, 154, 531, 60);
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
		
		JButton btnProjekte = new JButton("Projekte");
		btnProjekte.setBounds(42, 154, 397, 60);
		btnProjekte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
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
		
		JButton btnPersonalbedarf = new JButton("Personalbedarf");
		btnPersonalbedarf.setBounds(56, 446, 107, 23);
		btnPersonalbedarf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
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
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 272, 2, 2);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(561, 272, 531, 60);
		progressBar.setValue(34);
		progressBar.setStringPainted(true);
		frame2.getContentPane().setLayout(null);
		frame2.getContentPane().add(btnProjekte);
		frame2.getContentPane().add(scrollPane);
		frame2.getContentPane().add(progressBar);
		frame2.getContentPane().add(btnMitarbeiter);
		frame2.getContentPane().add(btnPersonalbedarf);
		
		JMenuBar menuBar = new JMenuBar();
		frame2.setJMenuBar(menuBar);
		
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
	}
}