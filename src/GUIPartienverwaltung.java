import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Toolkit;

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
		frame17.setBounds(100, 100, 717, 463);
		frame17.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame17.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(246, 63, 314, 309);
		frame17.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnHinzufgen = new JButton("Hinzuf\u00FCgen");
		btnHinzufgen.setBounds(51, 97, 89, 23);
		frame17.getContentPane().add(btnHinzufgen);
		
		JButton btnEntfernen = new JButton("entfernen");
		btnEntfernen.setBounds(51, 131, 89, 23);
		frame17.getContentPane().add(btnEntfernen);
		
		JButton btnNeuePartie = new JButton("neue Partie");
		btnNeuePartie.setBounds(177, 11, 89, 23);
		frame17.getContentPane().add(btnNeuePartie);
		
		JButton btnPartieLschen = new JButton("Partie l\u00F6schen");
		btnPartieLschen.setBounds(281, 11, 134, 23);
		frame17.getContentPane().add(btnPartieLschen);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(88, 11, 30, 22);
		frame17.getContentPane().add(comboBox);
		
		JMenuBar menuBar = new JMenuBar();
		frame17.setJMenuBar(menuBar);
		
		JButton btnZurck = new JButton("zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame17.dispose();
			}
		});
		menuBar.add(btnZurck);
	}
}
