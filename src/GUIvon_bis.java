import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUIvon_bis {

	public JFrame frame12;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public GUIvon_bis(JDBC_MariaDB jdbc, int row) {
		initialize(jdbc, row);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc, int row) {
		frame12 = new JFrame();
		frame12.setBounds(100, 100, 526, 337);
		frame12.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblVon = new JLabel("von KW:");
		
		JLabel lblBis = new JLabel("bis KW:");
		
		JButton btnBesttigen = new JButton("krank");
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int von=0;
				int bis=0;
				
					
					try {
						von=Integer.parseInt(textField.getText());
						bis=Integer.parseInt(textField_1.getText());
						
						//int lenght=jdbc.getMitarbeiter().size();
						
						
						/*for(int i=0; i<lenght; i++) {
						    if (jdbc.getMitarbeiter().get(i).getPersnr()==(PersoNr)) {
						        System.out.println("ok cool");*/
						        while(von<=bis) {
						        	
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setKrank(true);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setZugeteilt(null);
						        	von++;
						        	
						        }
						        boolean ill=jdbc.getMitarbeiter().get(1).getWochen().get(5).isKrank();
						        System.out.println(ill);
						   // }
						    
						
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
			}
		});
		
		JButton btnNewButton = new JButton("wieder verf\u00FCgbar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int von=0;
				int bis=0;
				
					
					try {
						von=Integer.parseInt(textField.getText());
						bis=Integer.parseInt(textField_1.getText());
						
						//int lenght=jdbc.getMitarbeiter().size();
						
						
						/*for(int i=0; i<lenght; i++) {
						    if (jdbc.getMitarbeiter().get(i).getPersnr()==(PersoNr)) {
						        System.out.println("ok cool");*/
						        while(von<=bis) {
						        	
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setKrank(false);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setUrlaub(false);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setSchulung(false);
						        	von++;
						        	
						        }
						        
						        
						   // }
						    
						
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
				
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Urlaub");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int von=0;
				int bis=0;
				
					
					try {
						von=Integer.parseInt(textField.getText());
						bis=Integer.parseInt(textField_1.getText());
						
						//int lenght=jdbc.getMitarbeiter().size();
						
						
						/*for(int i=0; i<lenght; i++) {
						    if (jdbc.getMitarbeiter().get(i).getPersnr()==(PersoNr)) {
						        System.out.println("ok cool");*/
						        while(von<=bis) {
						        	
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setUrlaub(true);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setZugeteilt(null);
						        	von++;
						        	
						        }
						        
						   // }
						    
						
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Schulung");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int von=0;
				int bis=0;
				
					
					try {
						von=Integer.parseInt(textField.getText());
						bis=Integer.parseInt(textField_1.getText());
						
						//int lenght=jdbc.getMitarbeiter().size();
						
						
						/*for(int i=0; i<lenght; i++) {
						    if (jdbc.getMitarbeiter().get(i).getPersnr()==(PersoNr)) {
						        System.out.println("ok cool");*/
						        while(von<=bis) {
						        	
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setSchulung(true);
						        	jdbc.getMitarbeiter().get(row).getWochen().get(von).setZugeteilt(null);
						        	von++;
						        	
						        }
						        boolean schulung=jdbc.getMitarbeiter().get(1).getWochen().get(5).isSchulung();
						        System.out.println(schulung);
						   // }
						    
						
						
						
						//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
					frame12.dispose();
			}
		});
			
		
		GroupLayout groupLayout = new GroupLayout(frame12.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVon))
							.addGap(105)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBis)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnBesttigen)
									.addGap(117)
									.addComponent(btnNewButton_1)
									.addGap(18)
									.addComponent(btnNewButton_2)))))
					.addContainerGap(96, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVon)
						.addComponent(lblBis))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBesttigen)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		frame12.getContentPane().setLayout(groupLayout);
	}
}
