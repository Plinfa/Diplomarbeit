import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
//Wartecker Marcel
public class GUIProjekte {

	public JFrame frame5;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
		
	

	/**
	 * Create the application.
	 */
	public GUIProjekte(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frame5 = new JFrame();
		frame5.setTitle("Personal- und Projektmanager");
		frame5.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIProjekte.class.getResource("/ressources/EQOS.jpg")));
		frame5.setBounds(100, 100, 1117, 623);
		frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//David 05.09
		
				JButton btnHinzufgen = new JButton("Hinzuf\u00FCgen");
				btnHinzufgen.addActionListener(new ActionListener() {
				
					
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
				btnHinzufgen.setBounds(99, 67, 89, 23);
				
				
				//David 05.09
				JButton btnLschen = new JButton("l\u00F6schen");
				btnLschen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						int row=0;
						String ProjektNr=null;
						
						try {
						
						row=table.getSelectedRow();
						ProjektNr=table.getValueAt(row, 0).toString();
						
						
						jdbc.deleteProject(ProjektNr);
						System.out.println(ProjektNr);
						
					}catch(Exception e1) {
						
						e1.printStackTrace();
					}
						
						table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));	
					}
				});
				btnLschen.setBounds(99, 101, 89, 23);
		
		JLabel lblProjekte = new JLabel("Projekte");
		lblProjekte.setBounds(207, 28, 48, 14);
		
		JButton btnProjektplanungDiagramm = new JButton("Projektplanung Diagramm");
		btnProjektplanungDiagramm.setBounds(890, 108, 172, 23);
		btnProjektplanungDiagramm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUI_Gantt_ChartDesigner window = new GUI_Gantt_ChartDesigner(jdbc);
							window.frame4.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JButton btnNewButton = new JButton("Personalbedarf gesamt");
		btnNewButton.setBounds(519, 45, 172, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame5.dispose();
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
		frame5.getContentPane().setLayout(null);
		frame5.getContentPane().add(lblProjekte);
		frame5.getContentPane().add(btnHinzufgen);
		frame5.getContentPane().add(btnLschen);
		frame5.getContentPane().add(btnNewButton);
		frame5.getContentPane().add(btnProjektplanungDiagramm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 137, 943, 393);
		frame5.getContentPane().add(scrollPane); 
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		
		//David 05.09
				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String content= null;
						int row=0;
						String ProjektNr=null;
						
						try {
						
						row=table.getSelectedRow();
						ProjektNr=table.getValueAt(row, 0).toString();
						
						List<String> tablecontent = new ArrayList<String>();
						tablecontent.clear();
						
						
						
						for( int column=1;column<=table.getColumnCount();column++) {
							content=null;
							
							
							
							if(table.getModel().getValueAt(row, column-1)==null) {
								
								tablecontent.add(column-1, "");
							}
							else {
								content=table.getModel().getValueAt(row, column-1).toString();
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
				btnUpdate.setBounds(198, 67, 89, 23);
				frame5.getContentPane().add(btnUpdate);
				
				JButton btnEinzelprojektPersonalbedarf = new JButton("Einzelprojekt Personalbedarf");
				btnEinzelprojektPersonalbedarf.addActionListener(new ActionListener() {
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
				btnEinzelprojektPersonalbedarf.setBounds(504, 89, 187, 23);
				frame5.getContentPane().add(btnEinzelprojektPersonalbedarf);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame5.setJMenuBar(menuBar);
		
		JButton btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame5.dispose();
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
	}
}