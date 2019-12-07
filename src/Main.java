import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;



public class Main {
	
	

	public static void main(String[] args) {
		/*
		ExcelReader excelReader = new ExcelReader();
		try {
			excelReader.openFile("C:\\Users\\Marcel\\Desktop\\Test.xlsx");
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		JDBC_MariaDB jdbc = new JDBC_MariaDB();
		jdbc.Projektecount();
		
		*/
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIWillkommen2 window = new GUIWillkommen2("Personal- und Projektmanager", new JDBC_MariaDB());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
		//Employee karl= new Employee ("Hans");
		
		
		
		
		//Project erstes= new Project("Nices Projekt", 3232, "Sepp",3345 );
		//System.out.println(erstes.getKalendarwoche()+" "+ erstes.getYear());
		//JDBC_MariaDB jdbc = new JDBC_MariaDB();
		//Willkommenbildschirm
		
		//jdbc.selectPasswort();
		
		
	/*	
		
		
		
		
		
		
		System.out.println(erstes.getKunde().getName()+" "+erstes.getNameProj());
		Employee alf= new Employee("Alfred", "www.alf@gmail.at", "15.03.1973");
		System.out.println(alf.getName()+", "+alf.getEmail()+", "+alf.getDateOfBirth());
			    
		*/
		
		
		//jdbc.selectAll();
		
	}
	
	
	


}