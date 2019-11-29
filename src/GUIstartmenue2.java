import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//Wartecker Marcel
public class GUIstartmenue2 {

	public JFrame frame2;

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
		
		JButton btnPasswortndern = new JButton("Passwort \u00E4ndern");
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
		
		JButton btnPersonalbedarf = new JButton("Personalbedarf");
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
		GroupLayout groupLayout = new GroupLayout(frame2.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(228)
							.addComponent(btnPasswortndern, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(btnProjekte, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
							.addGap(72)
							.addComponent(btnMitarbeiter, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addComponent(btnPersonalbedarf)))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addComponent(btnPasswortndern, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(55)
							.addComponent(btnProjekte, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(btnMitarbeiter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(42)
					.addComponent(btnPersonalbedarf)
					.addGap(94))
		);
		frame2.getContentPane().setLayout(groupLayout);
	}
}