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
import java.awt.Font;

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
		frameEinzelPerso.setTitle("Personal- und Projektmanager");
		frameEinzelPerso.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIeinzelPERS.class.getResource("/ressources/EQOS.jpg")));
		frameEinzelPerso.setBounds(100, 100, 716, 218);
		frameEinzelPerso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		JLabel lblKw = new JLabel("KW:");
		lblKw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblBentigt = new JLabel("ben\u00F6tigt:");
		lblBentigt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblZugeteilt = new JLabel("zugeteilt:");
		lblZugeteilt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDifferenz = new JLabel("\u00DCberhang:");
		lblDifferenz.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frameEinzelPerso.setJMenuBar(menuBar);
		
		JButton button = new JButton("zurück");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameEinzelPerso.dispose();
								
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
			
			String benotigt=benoetigt(jdbc, woche, year, ProjNr);//bei diesem Proj zugeteilt
			String zugeteilt=zugeteilt(jdbc, woche, year, ProjNr);//bei diesem Proj zugeteilt
			String diff=differenz(jdbc, woche, year, ProjNr);//bei diesem Proj zugeteilt
			// error da diff minus ist
				//String diff="0";
			data [0][spalte]=benotigt;
			data [1][spalte]=zugeteilt;
			data [2][spalte]=diff;
			
	
			
			
			
			spalte++;
			woche++;
			i++;
		}
		JScrollPane scrollPane_1 = new JScrollPane();
		
		
		scrollPane_1.setMinimumSize(new Dimension(1000, 1000));
		
		table_1 = new JTable(data, columns);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		table_1.setRowHeight(25);
		scrollPane_1.setViewportView(table_1);
		GroupLayout groupLayout = new GroupLayout(frameEinzelPerso.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblKw)
						.addComponent(lblBentigt)
						.addComponent(lblZugeteilt)
						.addComponent(lblDifferenz))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 542, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblKw)
							.addGap(9)
							.addComponent(lblBentigt)
							.addGap(9)
							.addComponent(lblZugeteilt)
							.addGap(8)
							.addComponent(lblDifferenz)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frameEinzelPerso.getContentPane().setLayout(groupLayout);
		
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
public String benoetigt(JDBC_MariaDB jdbc, int week, int year, int ProjNr) {
	
	Calendar calendar = Calendar.getInstance();
	calendar.setWeekDate(year, week, 2);
	
	
	
	Date abfrage=calendar.getTime();
	java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
	
	String benoetigt=jdbc.countbenötigt(abfrage1,ProjNr);
	
	//zählen wie viele nicht zugeteilt

	       
	
	return benoetigt;
}
public String differenz(JDBC_MariaDB jdbc, int week, int year, int ProjNr) {
	
	int differenzcount=0;
	
	Calendar calendar = Calendar.getInstance();
	calendar.setWeekDate(year, week, 2);
	
	Date abfrage=calendar.getTime();
	java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
	

	int benoetigtcount = Integer.parseInt(jdbc.countbenötigt(abfrage1, ProjNr));
	
		int zugeteiltcount = Integer.parseInt(jdbc.countzugeteilt(abfrage1,ProjNr));
		if(benoetigtcount==0) {
			differenzcount=0;
		}
		else {
			differenzcount=zugeteiltcount-benoetigtcount;
		}
		 
		String diff=Integer.toString(differenzcount);
	
	
	
	//zählen wie viele zugeteilt
	
	
	
	
	return diff;
}
}
