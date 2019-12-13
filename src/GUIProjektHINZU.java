import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import javax.swing.SpringLayout;

//David 05.09
public class GUIProjektHINZU {

	public JFrame frame11;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUIProjektHINZU(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frame11 = new JFrame();
		frame11.setTitle("Projekt Hinzuf\u00FCgen");
		frame11.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIProjektHINZU.class.getResource("/ressources/EQOS.jpg")));
		frame11.setBounds(100, 100, 450, 300);
		frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		textField = new JTextField();
		textField.setBounds(10, 30, 96, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 73, 96, 20);
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 210, 96, 20);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(132, 30, 96, 20);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(132, 73, 96, 20);
		textField_6.setColumns(10);
		
		
		JDatePickerImpl datePicker=generateDatePicker();	
		datePicker.setBounds(238, 87, 120, 23);
	
		
		JDatePickerImpl datePicker1=generateDatePicker();	
		datePicker1.setBounds(132, 152, 120, 23);
		
		JButton btnEingabe = new JButton("Eingabe");
		btnEingabe.setBounds(335, 209, 89, 23);
		frame11.getContentPane().add(btnEingabe);
		btnEingabe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ProjektNr=0;
				String Projektname=null;
				int GruppenNr=0;
				int sonstigeMitarbeiter=0;
				String Ort=null;
				String Startdatum=null;
				String Enddatum=null;
				
				
					
					try {
						ProjektNr=Integer.parseInt(textField.getText());
						Projektname=textField_1.getText();
						
						Ort=textField_4.getText();
						Startdatum=textField_5.getText();
						Enddatum=textField_6.getText();
						
						jdbc.insertProject(ProjektNr, Projektname, Ort, Startdatum, Enddatum);
						
					
						
						
						JOptionPane.showMessageDialog(null, "Projekt erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					textField.setText(null);
					textField_1.setText(null);
				
					textField_4.setText(null);
					textField_5.setText(null);
					textField_6.setText(null);
					frame11.dispose();
					
					GUIProjekte window = new GUIProjekte(jdbc);
				
					window.frame5.setVisible(true);
			}
			
		});
		
		JLabel lblProjektnr = new JLabel("ProjektNr");
		lblProjektnr.setBounds(10, 10, 55, 14);
		
		JLabel lblProjektname = new JLabel("Projektname");
		lblProjektname.setBounds(10, 56, 75, 14);
		
		JLabel lblOrt = new JLabel("Ort");
		lblOrt.setBounds(10, 190, 31, 14);
		
		JLabel lblStartdatum = new JLabel("Startdatum");
		lblStartdatum.setBounds(132, 10, 69, 14);
		
		JLabel lblEnddatum = new JLabel("Enddatum");
		lblEnddatum.setBounds(132, 56, 96, 14);
		frame11.getContentPane().setLayout(null);
		frame11.getContentPane().add(datePicker);
		frame11.getContentPane().add(datePicker1);
		frame11.getContentPane().add(btnEingabe);
		frame11.getContentPane().add(textField_1);
		frame11.getContentPane().add(textField_6);
		frame11.getContentPane().add(textField_4);
		frame11.getContentPane().add(textField);
		frame11.getContentPane().add(lblStartdatum);
		frame11.getContentPane().add(textField_5);
		frame11.getContentPane().add(lblEnddatum);
		frame11.getContentPane().add(lblProjektnr);
		frame11.getContentPane().add(lblProjektname);
		frame11.getContentPane().add(lblOrt);
	}
	
		private JDatePickerImpl generateDatePicker(){

			Properties p = new Properties();
			p.put("text.today", "today");
			p.put("text.month", "month");
			p.put("text.year", "year");
		
			UtilDateModel model = new UtilDateModel();
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			JDatePickerImpl dp = new JDatePickerImpl(datePanel, new DateComponentFormatter());
			
			
			return dp;
		}
}
