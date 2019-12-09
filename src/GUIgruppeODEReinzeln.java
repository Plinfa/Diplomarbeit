import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		frame6.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIgruppeODEReinzeln.class.getResource("/ressources/EQOS.jpg")));
		frame6.setBounds(100, 100, 964, 620);
		frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.setBounds(491, 405, 85, 23);
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row =table.getSelectedRow();
				int column=0;
				String content =table.getValueAt(row,column).toString();
				int PersNr=Integer.parseInt(content);
				
				//int von=0;
				//int bis=0;
				int projnr=0;
				projnr=Integer.parseInt(textField_2.getText());
				//von=Integer.parseInt(textField_3.getText());
				//bis=Integer.parseInt(textField_4.getText());
				String von=null;
				String bis=null;
				von=textField_3.getText();
				bis=textField_4.getText();
					
					try {
						
						
						int lenght=jdbc.Projektecount();
						System.out.println(lenght);
						
						jdbc.Mitarbeiterzuteilen(PersNr, von, bis, projnr);
						/*
						for(int i=0; i<lenght; i++) {
						    if (jdbc.getProjekte().get(i).getKostenstelle()==(projnr)) {
						        while(von<=bis) {
						        	
						        	if (jdbc.getMitarbeiter().get(row).getWochen().get(von).isKrank()==true | jdbc.getMitarbeiter().get(row).getWochen().get(von).isSchulung()==true | jdbc.getMitarbeiter().get(row).getWochen().get(von).isUrlaub()==true) {
						        		
						        		jdbc.getMitarbeiter().get(row).getWochen().get(von).setZugeteilt(null);
						        	}
						        	
						        	else {
						        		
						        		jdbc.getMitarbeiter().get(row).getWochen().get(von).setZugeteilt(jdbc.getProjekte().get(i));
						        		
						        	}
						        	von++;
						        }
						        
						      /*  Project projektname =jdbc.getMitarbeiter().get(1).getWochen().get(2).getZugeteilt();
						        String projname=projektname.getNameProj();
						        System.out.println(projname);
						        break;
						    }
						    
						   
						}
						 */
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField_2.setText(null);
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
		scrollPane_1.setBounds(600, 244, 285, 196);
		frame6.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));
		table_1.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleProjects()));
		
		label = new JLabel("Projektnummer");
		label.setBounds(665, 90, 74, 14);
		frame6.getContentPane().add(label);
		
		label_1 = new JLabel("von:");
		label_1.setBounds(676, 149, 21, 14);
		frame6.getContentPane().add(label_1);
		
		label_2 = new JLabel("bis:");
		label_2.setBounds(787, 149, 18, 14);
		frame6.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(665, 110, 96, 20);
		frame6.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(665, 171, 96, 20);
		frame6.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(771, 169, 96, 20);
		frame6.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton button = new JButton("entfernen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						/*try {
							GUIMitarbeiterENTF window = new GUIMitarbeiterENTF(new JDBC_MariaDB());
							window.frame8.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}*/
						int row=0;
						String PersNr=null;
						
						try {
						
						row=table.getSelectedRow();
						PersNr=table.getValueAt(row, 0).toString();
						
						
						jdbc.deleteEmployee(PersNr);
						System.out.println(PersNr);
						 
						
					}catch(Exception e1) {
						
						e1.printStackTrace();
					}
						table.setModel(DbUtils.resultSetToTableModel(jdbc.selectMitarbeiterinfo()));	
					}
					
				});
				//frame3.dispose();
				
				
			}
			
		});
		button.setBounds(132, 69, 89, 23);
		frame6.getContentPane().add(button);
		
		JButton button_1 = new JButton("Hinzuf\u00FCgen");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				//frame3.dispose();
				
				
			}
		});
		
		
		
		button_1.setBounds(132, 48, 89, 23);
		frame6.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content= null;
				int row=0;
				String PersoNr=null;
				
				try {
				
				row=table.getSelectedRow();
				PersoNr=table.getValueAt(row, 0).toString();
				
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
				
				jdbc.updateMitarbeiter(PersoNr, tablecontent);
				JOptionPane.showMessageDialog(null, "Update war erfolgreich ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
				
			}catch(Exception e1) {
				
				e1.printStackTrace();
			}
			
			}
		});
		button_2.setBounds(59, 48, 67, 23);
		frame6.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Mitarbeiter nicht verf\u00FCgbar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row;
				int PersoNr;
				String PersonalNr;
				row=table.getSelectedRow();
				
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GUIvon_bis window = new GUIvon_bis(jdbc, row);
							window.frame12.setVisible(true);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_3.setBounds(265, 48, 163, 23);
		frame6.getContentPane().add(button_3);
		
		JButton button_5 = new JButton("Zuteilung Detailansicht");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		button_5.setBounds(463, 48, 145, 23);
		frame6.getContentPane().add(button_5);
		
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
		
		JButton btnNewButton = new JButton("Personalbedarf");
		menuBar.add(btnNewButton);
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
	}
}