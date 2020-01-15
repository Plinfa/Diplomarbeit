import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.*;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
//Wartecker Marcel
public class GUIPersonalbedarf {

	public JFrame frmPersonalUndProjektmanager;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	
		
	

	/**
	 * Create the application.
	 */
	public GUIPersonalbedarf(JDBC_MariaDB jdbc) {
		initialize(jdbc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc) {
		frmPersonalUndProjektmanager = new JFrame();
		frmPersonalUndProjektmanager.setTitle("Personal- und Projektmanager");
		frmPersonalUndProjektmanager.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIPersonalbedarf.class.getResource("/ressources/EQOS.jpg")));
		frmPersonalUndProjektmanager.setBounds(100, 100, 1057, 584);
		frmPersonalUndProjektmanager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JLabel lblMitarbeiter = new JLabel("Mitarbeiter");
		
		JLabel lblKrank = new JLabel("krank");
		
		JLabel lblUrlaub = new JLabel("Urlaub");
		
		JLabel lblSchulung = new JLabel("Schulung");
		
		JLabel label = new JLabel("");
		
		JLabel lblZugeteilt = new JLabel("zugeteilt");
		
		JLabel lblNichtZugeteiltbentigt = new JLabel("nicht zugeteilt/ben\u00F6tigt");
		
		JLabel lblKw = new JLabel("KW:");
	//	textField_6.setText(nichtzugeteilt);
		
		int i=0;
		
		String columns [] = new String[108];
		String [][] data= new String [6][108];
	
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
			
			String nichtzugeteilt=needed(jdbc, woche, year);
			String zugeteilt=generellzugeteilt(jdbc, woche, year);
			String krank=krank(jdbc, woche, year);
			String urlaub=urlaub(jdbc, woche, year);
			String schulung=schulung(jdbc, woche, year);
			String mitarbeiter=jdbc.countmitarbeiter();
				
			data [0][spalte]=mitarbeiter;
			data [1][spalte]=krank;
			data [2][spalte]=urlaub;
			data [3][spalte]=schulung;
			data [4][spalte]=zugeteilt;
			data [5][spalte]=nichtzugeteilt;
	
			
			
			
			spalte++;
			woche++;
			i++;
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(1000, 1000));
		
		table_1 = new JTable(data, columns);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		table_1.setRowHeight(25);
		scrollPane.setViewportView(table_1);
		GroupLayout groupLayout = new GroupLayout(frmPersonalUndProjektmanager.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(122)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMitarbeiter)
						.addComponent(lblKw)
						.addComponent(lblKrank)
						.addComponent(lblUrlaub)
						.addComponent(lblSchulung)
						.addComponent(lblZugeteilt)
						.addComponent(lblNichtZugeteiltbentigt))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(182, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(77)
							.addComponent(lblKw)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMitarbeiter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblKrank)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUrlaub)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSchulung)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblZugeteilt)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNichtZugeteiltbentigt))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(158, Short.MAX_VALUE))
		);
		frmPersonalUndProjektmanager.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmPersonalUndProjektmanager.setJMenuBar(menuBar);
		
		JButton btnZu = new JButton("zurück");
		btnZu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPersonalUndProjektmanager.dispose();
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
		menuBar.add(btnZu);
	}
	
	public String needed(JDBC_MariaDB jdbc, int week, int year) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(year, week, 2);
		
		Date abfrage=calendar.getTime();
		
		int nichtzug=0;
		
		
		//zählen wie viele nicht zugeteilt
	
		        
			
			
			
		
		String nichtzugeteilt = String.valueOf(nichtzug);
		
		return nichtzugeteilt;
	}
	
	public String generellzugeteilt(JDBC_MariaDB jdbc, int week, int year) {
		
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(year, week, 2);
		
		Date abfrage=calendar.getTime();
		java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
		
		
		String zugeteilt=jdbc.countzugeteilt(abfrage1);
		//zählen wie viele zugeteilt
		
		
		
		
		return zugeteilt;
	}
	
public String krank(JDBC_MariaDB jdbc, int week, int year) {
		
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(year, week, 2);
		
		
		Date abfrage=calendar.getTime();
		java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
		
		
		String krank=jdbc.countkrank(abfrage1);
		//zählen wie viele krank
		
		
		//String krank = String.valueOf(krk);
		
		return krank;
	}
public String urlaub(JDBC_MariaDB jdbc, int week, int year) {
	
	
	
	Calendar calendar = Calendar.getInstance();
	calendar.setWeekDate(year, week, 2);
	
	Date abfrage=calendar.getTime();
	java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
	
	
	String urlaub=jdbc.counturlaub(abfrage1);
	//zählen wie viele urlaub
	
	//String urlaub = String.valueOf(url);
	
	return urlaub;
}

	public String schulung(JDBC_MariaDB jdbc, int week, int year) {
	
	
	
	Calendar calendar = Calendar.getInstance();
	calendar.setWeekDate(year, week, 2);
	
	Date abfrage=calendar.getTime();
	java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
	
	
	String schulung=jdbc.countschulung(abfrage1);
	//zählen wie viele schulung
	
	//String schulung = String.valueOf(sch);
	
	return schulung;
	}
	
	
}
