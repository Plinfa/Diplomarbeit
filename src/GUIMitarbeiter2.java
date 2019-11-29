import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JComboBox;

//bearbeitet durch david Di 04.09
public class GUIMitarbeiter2 {

	public JFrame frame3;
	private JTable table;
	int[] rows;

	/**
	 * Launch the application.
	 */
	
		

	/**
	 * Create the application.
	 */
	public GUIMitarbeiter2(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frame3 = new JFrame();
		frame3.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIMitarbeiter2.class.getResource("/ressources/EQOS.jpg")));
		frame3.setTitle("Personal- und Projektmanager");
		frame3.setBounds(100, 100, 1186, 743);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Mitarbeiter Hinzufügen------------David
		JButton btnHinzufgen = new JButton("Hinzuf\u00FCgen");
		btnHinzufgen.addActionListener(new ActionListener() {
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
		
		//Mitarbeiter Entfernen------David
		JButton btnEntfernen = new JButton("entfernen");
		btnEntfernen.addActionListener(new ActionListener() {
			
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
						PersNr=table.getValueAt(row, 3).toString();
						
						
						jdbc.deleteEmployee(PersNr);
						System.out.println(PersNr);
						
					}catch(Exception e1) {
						
						e1.printStackTrace();
					}
							
					}
				});
				//frame3.dispose();
				
			}	
		});
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(1000, 1000));
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectTabelleMitarbeiter()));
		
		
		//Update Mitarbeiter----David 04.09
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content= null;
				int row=0;
				String PersoNr=null;
				
				try {
				
				row=table.getSelectedRow();
				PersoNr=table.getValueAt(row, 3).toString();
				
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
		
		JButton btnNewButton = new JButton("Mitarbeiter zuteilen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row =table.getSelectedRow();
				
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
		
		JButton btnKrankMelden = new JButton("Mitarbeiter nicht verf\u00FCgbar");
		btnKrankMelden.addActionListener(new ActionListener() {
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
		
		JButton btnZuteilungDetailansicht = new JButton("Zuteilung Detailansicht");
		btnZuteilungDetailansicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame3.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1059, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnUpdate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEntfernen, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnHinzufgen, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
							.addGap(443)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton)
									.addGap(75)
									.addComponent(btnZuteilungDetailansicht))
								.addComponent(btnKrankMelden))))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(btnEntfernen))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnHinzufgen)
								.addComponent(btnUpdate)
								.addComponent(btnNewButton)
								.addComponent(btnZuteilungDetailansicht))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnKrankMelden)))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE)
					.addGap(103))
		);
		frame3.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame3.setJMenuBar(menuBar);
		
		JButton btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame3.dispose();
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