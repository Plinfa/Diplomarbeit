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
import java.awt.Font;
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
		frmPersonalUndProjektmanager.setBounds(100, 100, 869, 398);
		frmPersonalUndProjektmanager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel lblMitarbeiter = new JLabel("Mitarbeiter");
		lblMitarbeiter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblKrank = new JLabel("krank");
		lblKrank.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblUrlaub = new JLabel("Urlaub");
		lblUrlaub.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblSchulung = new JLabel("Schulung");
		lblSchulung.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel label = new JLabel("");
		
		JLabel lblZugeteilt = new JLabel("zugeteilt");
		lblZugeteilt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNichtZugeteiltbentigt = new JLabel("nicht zugeteilt");
		lblNichtZugeteiltbentigt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblKw = new JLabel("KW:");
		lblKw.setFont(new Font("Tahoma", Font.PLAIN, 13));
	//	textField_6.setText(nichtzugeteilt);
		
		int i=0;
		
		String columns [] = new String[108];
		String [][] data= new String [7][108];
	
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
			
			String benoetigt=benoetigt(jdbc, woche, year,0);
			String zugeteilt=generellzugeteilt(jdbc, woche, year, 0);
			String krank=krank(jdbc, woche, year);
			String urlaub=urlaub(jdbc, woche, year);
			String schulung=schulung(jdbc, woche, year);
			String mitarbeiter=jdbc.countmitarbeiter();
			String nichtzugeteilt=nichtzugeteilt(jdbc, woche, year);
				
			data [0][spalte]=mitarbeiter;
			data [1][spalte]=krank;
			data [2][spalte]=urlaub;
			data [3][spalte]=schulung;
			data [4][spalte]=zugeteilt;
			data [5][spalte]=nichtzugeteilt;
			data [6][spalte]=benoetigt;
	
			
			
			
			
			
			spalte++;
			woche++;
			i++;
		}
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(1000, 1000));
		
		table_1 = new JTable(data, columns);
		
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		//TableColorCellRenderer renderer =new TableColorCellRenderer();
		//table_1.setDefaultRenderer(Object.class, renderer);
		table_1.setRowHeight(36);
		scrollPane.setViewportView(table_1);
		
		JLabel lblBentigt = new JLabel("ben\u00F6tigt");
		lblBentigt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout groupLayout = new GroupLayout(frmPersonalUndProjektmanager.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblKw, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNichtZugeteiltbentigt)
						.addComponent(lblSchulung)
						.addComponent(lblZugeteilt)
						.addComponent(lblUrlaub)
						.addComponent(lblBentigt)
						.addComponent(lblKrank)
						.addComponent(lblMitarbeiter))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 735, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblKw)
							.addGap(13)
							.addComponent(lblMitarbeiter)
							.addGap(22)
							.addComponent(lblKrank)
							.addGap(18)
							.addComponent(lblUrlaub)
							.addGap(21)
							.addComponent(lblSchulung)
							.addGap(21)
							.addComponent(lblZugeteilt)
							.addGap(19)
							.addComponent(lblNichtZugeteiltbentigt)
							.addGap(20)
							.addComponent(lblBentigt)))
					.addContainerGap())
		);
		frmPersonalUndProjektmanager.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmPersonalUndProjektmanager.setJMenuBar(menuBar);
		
		JButton btnZu = new JButton("zurück");
		btnZu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				frmPersonalUndProjektmanager.dispose();
				
			}
		});
		menuBar.add(btnZu);
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
	
	public String generellzugeteilt(JDBC_MariaDB jdbc, int week, int year, int ProjNr) {
		
	
		
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(year, week, 2);
		
		Date abfrage=calendar.getTime();
		java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
		
		
		String zugeteilt=jdbc.countzugeteilt(abfrage1, ProjNr);
		//zählen wie viele zugeteilt
		
		
		
		
		return zugeteilt;
	}
	
public String nichtzugeteilt(JDBC_MariaDB jdbc, int week, int year) {
		
	
		
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(year, week, 2);
		
		Date abfrage=calendar.getTime();
		java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
		
		
		//String nichtzugeteilt=jdbc.countnichtzugeteilt(abfrage1);
		String mitarbeiter=jdbc.countmitarbeiter();
		int mitarbeitercount = Integer.parseInt(mitarbeiter);
		String zugeteilt=jdbc.countzugeteilt(abfrage1,0);
		int zugeteiltcount = Integer.parseInt(zugeteilt);
		
		int nichtzugeteiltcount=mitarbeitercount-zugeteiltcount;
		String nichtzugeteilt=Integer.toString(nichtzugeteiltcount);
		//zählen wie viele zugeteilt
		
		
		
		
		return nichtzugeteilt;
	}
	
public String krank(JDBC_MariaDB jdbc, int week, int year) {
		
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(year, week, 2);
		
		
		Date abfrage=calendar.getTime();
		java.sql.Date abfrage1 = new java.sql.Date(abfrage.getTime());
		
		
		String krank=jdbc.countkrank(abfrage1);
		//zählen wie viele krank
		
		
		
		
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
