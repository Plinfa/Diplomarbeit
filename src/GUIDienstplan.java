import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIDienstplan {

	public JFrame frameDienstplan;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GUIDienstplan(JDBC_MariaDB jdbc, int PersNr) {
		initialize(jdbc, PersNr);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc, int PersNr) {
		frameDienstplan = new JFrame();
		frameDienstplan.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIDienstplan.class.getResource("/ressources/EQOS.jpg")));
		frameDienstplan.setTitle("Personal- und Projektmanager");
		frameDienstplan.setBounds(100, 100, 478, 301);
		frameDienstplan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameDienstplan.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 442, 206);
		frameDienstplan.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(jdbc.selectZuteilungen(PersNr)));
		
		JButton btnEntfernen = new JButton("Zuteilung löschen");
		btnEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row==-1) {
					JOptionPane.showMessageDialog(null, "Bitte Zuteilung auswählen", "Fehler", JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					String von = table.getValueAt(row, 0).toString();
					String bis = table.getValueAt(row, 1).toString();
				
					if (JOptionPane.showConfirmDialog(frameDienstplan, "Eintrag wirklich löschen?", "Personal- und Projektmanager", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) 
					{
						jdbc.deleteZuteilung(von, bis, PersNr);
						table.setModel(DbUtils.resultSetToTableModel(jdbc.selectZuteilungen(PersNr)));
					}
					else {
						table.setModel(DbUtils.resultSetToTableModel(jdbc.selectZuteilungen(PersNr)));
					}
				}
				
				
				
			}
		});
		btnEntfernen.setBounds(161, 228, 140, 23);
		frameDienstplan.getContentPane().add(btnEntfernen);
	}
}
