import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class GUIeinzelPERS {

	public JFrame frameEinzelPerso;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUIeinzelPERS(JDBC_MariaDB jdbc, int ProjNr) {
		initialize(jdbc, ProjNr);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc, int ProjNr) {
		frameEinzelPerso = new JFrame();
		frameEinzelPerso.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIeinzelPERS.class.getResource("/ressources/EQOS.jpg")));
		frameEinzelPerso.setBounds(100, 100, 690, 406);
		frameEinzelPerso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameEinzelPerso.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 49, 618, 241);
		frameEinzelPerso.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		scrollPane.setMinimumSize(new Dimension(1000, 1000));
		
		JMenuBar menuBar = new JMenuBar();
		frameEinzelPerso.setJMenuBar(menuBar);
		
		JButton button = new JButton("zurück");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameEinzelPerso.dispose();
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
		menuBar.add(button);
	}
}
