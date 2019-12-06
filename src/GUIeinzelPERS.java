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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(1000, 1000));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblProjektname = new JLabel("Projektname:");
		GroupLayout groupLayout = new GroupLayout(frameEinzelPerso.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(46, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(lblProjektname)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(429, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProjektname))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
		);
		frameEinzelPerso.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frameEinzelPerso.setJMenuBar(menuBar);
		
		JButton button = new JButton("Mitarbeiter zuteilen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(button);
		
		JButton button_1 = new JButton("Projekte");
		menuBar.add(button_1);
	}
}
