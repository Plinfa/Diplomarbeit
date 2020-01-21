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
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTable;

public class GUIeinzelPERS {

	public JFrame frameEinzelPerso;
	private JTable table_1;

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
		frameEinzelPerso.setBounds(100, 100, 690, 485);
		frameEinzelPerso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameEinzelPerso.getContentPane().setLayout(null);
		
		
		
		JLabel lblKw = new JLabel("KW:");
		lblKw.setBounds(45, 68, 48, 14);
		frameEinzelPerso.getContentPane().add(lblKw);
		
		JLabel lblBentigt = new JLabel("ben\u00F6tigt:");
		lblBentigt.setBounds(27, 93, 48, 14);
		frameEinzelPerso.getContentPane().add(lblBentigt);
		
		JLabel lblZugeteilt = new JLabel("zugeteilt:");
		lblZugeteilt.setBounds(27, 118, 48, 14);
		frameEinzelPerso.getContentPane().add(lblZugeteilt);
		
		JLabel lblDifferenz = new JLabel("Differenz:");
		lblDifferenz.setBounds(27, 143, 66, 14);
		frameEinzelPerso.getContentPane().add(lblDifferenz);
		
		
		
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
		
		int i=0;
		
		String columns [] = new String[108];
		String [][] data= new String [3][108];
	
		Calendar c1 = Calendar.getInstance();
		
		int woche = c1.get(Calendar.WEEK_OF_YEAR);
		int spalte=0;
		int year= c1.get(Calendar.YEAR);
		
		
		
		while(i<=107) {
			if(year==2020 || year==2026|| year==2032 || year==2037 || year==2043 || year==2048 || year==2054) {
				if(woche>=54) {
					woche=1;
					year++;
				}
			}
			else {
				if(woche>=53) {
					woche=1;
					year++;
				}
			}
		
			String week=String.valueOf(woche);
			String jahr=String.valueOf(year);
			columns[i]=week+ ", "+jahr;
			
			String benotigt=null;//bei diesem Proj zugeteilt
			String zugeteilt=zugeteilt(jdbc, woche, year, ProjNr);//bei diesem Proj zugeteilt
			String diff=null;//bei diesem Proj zugeteilt
				
			data [0][spalte]=benotigt;
			data [1][spalte]=zugeteilt;
			data [2][spalte]=diff;
			
	
			
			
			
			spalte++;
			woche++;
			i++;
		}
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(119, 68, 571, 325);
		frameEinzelPerso.getContentPane().add(scrollPane_1);
		
		
		scrollPane_1.setMinimumSize(new Dimension(1000, 1000));
		
		table_1 = new JTable(data, columns);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		table_1.setRowHeight(25);
		scrollPane_1.setViewportView(table_1);
		
	}
	
public String zugeteilt(JDBC_MariaDB jdbc, int week, int year, int ProjNr) {
		
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(year, week, 2);
		
		Date abfrage=calendar.getTime();
		java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
		
		
		String zugeteilt=jdbc.countzugeteilt(abfrage1, ProjNr);
		//zählen wie viele zugeteilt
		
		
		
		
		return zugeteilt;
	}
}
