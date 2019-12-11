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
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUIeinzelPERS(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frameEinzelPerso = new JFrame();
		frameEinzelPerso.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIeinzelPERS.class.getResource("/ressources/EQOS.jpg")));
		frameEinzelPerso.setBounds(100, 100, 690, 406);
		frameEinzelPerso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField();
		textField.setBounds(157, 18, 96, 20);
		textField.setColumns(10);
		frameEinzelPerso.getContentPane().setLayout(null);
		
		JLabel lblProjektname = new JLabel("Projektname:");
		lblProjektname.setBounds(74, 21, 65, 14);
		frameEinzelPerso.getContentPane().add(lblProjektname);
		frameEinzelPerso.getContentPane().add(textField);
		
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
