import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;



public class JDBC_MariaDB
{
	private ArrayList<Employee> mitarbeiter= new ArrayList<Employee>();
	private ArrayList<Project> projekte= new ArrayList<Project>();
	Connection con=null;
	
	//Konstruktor
	public JDBC_MariaDB()
	{
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			}catch(ClassNotFoundException e)
		
				{
					return;
				}
		

		try {
			
			
			con= DriverManager.getConnection("jdbc:mariadb://localhost:3306/eqospersonalplanung","root","davmay81");
		
			}catch(SQLException e)
				{
					e.printStackTrace();
				}
		
		
	}

	
	//Methoden
	// david update 25,09,2019
	public ResultSet selectAllMitarbeiter() 
	{
		
		
		
		ResultSet res=null;
		ResultSet resu=null;
		try {
		
			Statement stmt =con.createStatement();
		
			//SQL Befehl
			
			//String sql = "SELECT ANGA, GruppenNr, AAktiv, PersNr, StichtagUrlaub, Eintritt, SVNr, Name, Vorname";//, NameuVorname, PlzOrt, Strasse, GebDat, Stand, Geschl, Nation, Tel, Handy, Email, gelernterBeruf, Aufgabenbereich, Sicherheitsvertrauensperson, Firmenhandy, Kennzeichen, Fahrzeug, FIN, Handwerkerbefreiung, Führerscheine, FührerscheinNr, Behörde, Ausstelldat, gültigbis, Führerscheinüberprüfung, InfoschrPrivatnutzung, EzB, FahrbewilligungNr, Kranführerausbildung, C95LKW, HubstaplerÖ, HubstaplerDE, Notfallperson, NotfallsTel,TShirt, Jacke, Schuhe, GültigkeitA1, SicherheitspassNr, Bürounterweisung, VBFnAPG, Bayernwerkgültigbis, TenneT, TowerLatchSystem, Steigschulung, GUntersuchungenfälligam, GUntersuchungenfällig, PSAÜberprüfung, EHKursDauer, EHKursam, ErsthelferbisDE, nötigeDauer, APGSchulung, RichtigesPrüfenARCUS, LumpiSeilschulung, Compliance, Teleskopstapler, Hubarbeitsbühnen, HubarbeitsbKartenNr FROM mitarbeiter";
			
		String sql = "SELECT * FROM mitarbeiter";
		
			res= stmt.executeQuery(sql);	
			resu= stmt.executeQuery(sql);
				
			while(! resu.isLast()) // as long as valid data is in the result set
			{
				
				
			resu.next(); // go to next line in the customer table
			
			
			int persnr = resu.getInt(1);
			
			
			
			
			mitarbeiterliste_füllen(persnr); 
		
			}
		
			}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		
		
		return res;
	}
	
	public ResultSet selectTabelleMitarbeiter() 
	{
		
		
		
		ResultSet res=null;
		
		try {
		
			Statement stmt =con.createStatement();
		
			//SQL Befehl
			
			//String sql = "SELECT ANGA, GruppenNr, AAktiv, PersNr, StichtagUrlaub, Eintritt, SVNr, Name, Vorname";//, NameuVorname, PlzOrt, Strasse, GebDat, Stand, Geschl, Nation, Tel, Handy, Email, gelernterBeruf, Aufgabenbereich, Sicherheitsvertrauensperson, Firmenhandy, Kennzeichen, Fahrzeug, FIN, Handwerkerbefreiung, Führerscheine, FührerscheinNr, Behörde, Ausstelldat, gültigbis, Führerscheinüberprüfung, InfoschrPrivatnutzung, EzB, FahrbewilligungNr, Kranführerausbildung, C95LKW, HubstaplerÖ, HubstaplerDE, Notfallperson, NotfallsTel,TShirt, Jacke, Schuhe, GültigkeitA1, SicherheitspassNr, Bürounterweisung, VBFnAPG, Bayernwerkgültigbis, TenneT, TowerLatchSystem, Steigschulung, GUntersuchungenfälligam, GUntersuchungenfällig, PSAÜberprüfung, EHKursDauer, EHKursam, ErsthelferbisDE, nötigeDauer, APGSchulung, RichtigesPrüfenARCUS, LumpiSeilschulung, Compliance, Teleskopstapler, Hubarbeitsbühnen, HubarbeitsbKartenNr FROM mitarbeiter";
			
		String sql = "SELECT * FROM mitarbeiter";
		
			res= stmt.executeQuery(sql);	
			
				
			
		
			}
			catch(SQLException e)
			{
			e.printStackTrace(); 
			}
		
		
		return res;
	}
	
	public ResultSet selectMitarbeiterinfo() 
	{
		
		
		
		ResultSet res=null;
		ResultSet resu=null;
		try {
		
			Statement stmt =con.createStatement();
		
			//SQL Befehl
			
			String sql = "SELECT PersNr, Name, Nachname FROM mitarbeiter";
		
		
			res= stmt.executeQuery(sql);	
			resu= stmt.executeQuery(sql);
				
			while(! resu.isLast()) // as long as valid data is in the result set
			{
				
				
			resu.next(); // go to next line in the customer table
			
			
			int persnr = resu.getInt(1);//persnr= 1 spalte
			
			
		
			}
		
			}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		
		
		return res;
	}
	
	public ResultSet selectAllProjects() 
	{
		ResultSet res=null;
		ResultSet resu=null;
		try {
		
			Statement stmt =con.createStatement();
		
			//SQL Befehl
			
			String sql = "SELECT * FROM projekt";
		
		
			res= stmt.executeQuery(sql);	
			resu= stmt.executeQuery(sql);
			
			while(! resu.isLast()) // as long as valid data is in the result set
			{
				
				
			resu.next(); // go to next line in the customer table
			
			int projektnummer =resu.getInt(1);
			String projektname = resu.getString(2);
			Date startdatum = resu.getDate(4);
			Date enddatum = resu.getDate(5);
			
			projektliste_füllen(projektnummer, projektname,startdatum, enddatum);
		
			}
		}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		
		
		return res;
	}
	
	public ResultSet selectTabelleProjects() 
	{
		ResultSet res=null;
		try {
		
			Statement stmt =con.createStatement();
		
			//SQL Befehl
			
			String sql = "SELECT ProjektNr, Name, Ort, von, bis FROM projekt";
		
		
			res= stmt.executeQuery(sql);	
			
			
			
		}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		
		
		return res;
	}
	
	public IntervalCategoryDataset zeitplanung() 
	{
		
		ResultSet res=null;
		TaskSeriesCollection dataset = new TaskSeriesCollection();
		TaskSeries series1 = new TaskSeries("erwartet");
		
		try {
		
			Statement stmt =con.createStatement();
			
		
			//SQL Befehl
			
			String sql = "SELECT * FROM projekt";
			
			res= stmt.executeQuery(sql);
			
			
			while(! res.isLast()) // as long as valid data is in the result set
			{
				
				
			res.next(); // go to next line in the customer table
			
			 
			java.sql.Date heute = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						
			String projektname = res.getString(2);
			Date startdatum = res.getDate(4);
			Date enddatum = res.getDate(5);
			
			if(startdatum.compareTo(heute)<0) {
				
				startdatum=heute;
				
				
			}
			
			/*	
			if(enddatum.compareTo(heute)<0) {
				
				deleteProject(res.getString(1));
				
			}
			*/	
			 
			series1.add(new Task(projektname, startdatum, enddatum)); 	        
			
			
			}
		}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		
		dataset.add(series1);
		
		return dataset;
		
	}

	public void Mitarbeiterzuteilen(int PersNr,  String von, String bis, int projnr){
		
		
		int lenght=Projektecount();
		
		System.out.println(lenght);
		
		ResultSet res=null;
		String verfueg=Verfuegbarkeit(PersNr);
		
		try {
		
			Statement stmt =con.createStatement();
			
		
			//SQL Befehl
			
			
			//String sql = "SELECT * FROM projekt";
			
			
				if(verfueg!=null) {
					System.out.println("mitarbeiter ist nicht verfuegbar");
					
				}
				else {
					String sql = "INSERT INTO arbeitet VALUES('"+von+"','"+bis+"','"+projnr+"','"+PersNr+"')";
					res= stmt.executeQuery(sql);
				}
			
			
			res.close();
			stmt.close();
			
			/*
			while(! res.isLast()) // as long as valid data is in the result set
			{
				
				int projektnummer = res.getInt(1);
			
				res.next(); // go to next line in the customer table
			
						
			
			
			
		
			if(projektnummer==projnr) {
				
				while(von<=bis) {
		        	
		        	if (verfueg!=null) {
		        		
		        		//zuteilung löschen
		        		
		        	}
		        	
		        	else {
		        		
		        		//zuteilen 
		        		String zuteilen = "INSERT INTO arbeitet VALUES('"+von+"','"+bis+"','"+projnr+"','"+PersNr+"')";
		        	}
		        	von++;
		        }
				
				
			}        
		
			
			}
			*/
		}
		
		
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		    
		    
		
		
		
		//JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ", "Bestätigen", JOptionPane.OK_CANCEL_OPTION);
	
	}
	
	

	public void insertEmployee(int PersoNr, String Name, String Nachname ){
		
		ResultSet res=null;
		int PersNr=PersoNr;
		//String Name=Name;
		
			try {
			
				Statement stmt =con.createStatement();
				
				
				 //SQL Befehl
				
			String sql = "INSERT INTO mitarbeiter (PersNr, Name, Nachname) VALUES ('"+PersNr+"','"+Name+"','"+Nachname+"')";
				 res= stmt.executeQuery(sql);	
				
					res.close();		
					stmt.close();
				}
					catch(SQLException e)
					{
					e.printStackTrace(); 
					}
		}
		
		//David angepasst für marcel
	public void insertallfromExcel() { //bearbeiten reihenanzahl von excelreader fehlt nicht sicher ob benötigt
		
		
		try {
			
			Statement stmt =con.createStatement();
			
			 //SQL Befehl
			for(int r=0;r<=122;r++) {
				
				
					
				//String sql = "INSERT INTO mitarbeiter(ANGA, GruppenNr, AAktiv, PersNr, StichtagUrlaub, Eintritt, SVNr, Name, Vorname, NameuVorname, PlzOrt, Strasse, GebDat, Stand, Geschl, Nation, Tel, Handy, Email, gelernterBeruf, Aufgabenbereich, Sicherheitsvertrauensperson, Firmenhandy, Kennzeichen, Fahrzeug, FIN, Handwerkerbefreiung, Führerscheine, FührerscheinNr, Behörde, Ausstelldat, gültigbis, Führerscheinüberprüfung, InfoschrPrivatnutzung, EzB, FahrbewilligungNr, Kranführerausbildung, C95LKW, HubstaplerÖ, HubstaplerDE, Notfallperson, NotfallsTel,TShirt, Jacke, Schuhe, GültigkeitA1, SicherheitspassNr, Bürounterweisung, VBFnAPG, Bayernwerkgültigbis, TenneT, TowerLatchSystem, Steigschulung, GUntersuchungenfälligam, GUntersuchungenfällig, PSAÜberprüfung, EHKursDauer, EHKursam, ErsthelferbisDE, nötigeDauer, APGSchulung, RichtigesPrüfenARCUS, LumpiSeilschulung, Compliance, Teleskopstapler, Hubarbeitsbühnen, HubarbeitsbKartenNr)"
						//+"VALUES('"+ExcelReader.mitarbeiter[0][r]+"','"+ExcelReader.mitarbeiter[1][r]+"','"+ExcelReader.mitarbeiter[2][r]+"','"+ExcelReader.mitarbeiter[3][r]+"','"+ExcelReader.mitarbeiter[4][r]+"','"+ExcelReader.mitarbeiter[5][r]+"','"+ExcelReader.mitarbeiter[6][r]+"','"+ExcelReader.mitarbeiter[7][r]+"','"+ExcelReader.mitarbeiter[8][r]+"','"+ExcelReader.mitarbeiter[9][r]+"','"+ExcelReader.mitarbeiter[10][r]+"','"+ExcelReader.mitarbeiter[11][r]+"','"+ExcelReader.mitarbeiter[12][r]+"','"+ExcelReader.mitarbeiter[13][r]+"','"+ExcelReader.mitarbeiter[14][r]+"','"+ExcelReader.mitarbeiter[15][r]+"','"+ExcelReader.mitarbeiter[16][r]+"','"+ExcelReader.mitarbeiter[17][r]+"','"+ExcelReader.mitarbeiter[18][r]+"','"+ExcelReader.mitarbeiter[19][r]+"','"+ExcelReader.mitarbeiter[20][r]+"','"+ExcelReader.mitarbeiter[21][r]+"','"+ExcelReader.mitarbeiter[22][r]+"','"+ExcelReader.mitarbeiter[23][r]+"','"+ExcelReader.mitarbeiter[24][r]+"','"+ExcelReader.mitarbeiter[25][r]+"','"+ExcelReader.mitarbeiter[26][r]+"','"+ExcelReader.mitarbeiter[27][r]+"','"+ExcelReader.mitarbeiter[28][r]+"','"+ExcelReader.mitarbeiter[29][r]+"','"+ExcelReader.mitarbeiter[30][r]+"','"+ExcelReader.mitarbeiter[31][r]+"','"+ExcelReader.mitarbeiter[32][r]+"','"+ExcelReader.mitarbeiter[33][r]+"','"+ExcelReader.mitarbeiter[34][r]+"','"+ExcelReader.mitarbeiter[35][r]+"','"+ExcelReader.mitarbeiter[36][r]+"','"+ExcelReader.mitarbeiter[37][r]+"','"+ExcelReader.mitarbeiter[38][r]+"','"+ExcelReader.mitarbeiter[39][r]+"','"+ExcelReader.mitarbeiter[40][r]+"','"+ExcelReader.mitarbeiter[41][r]+"','"+ExcelReader.mitarbeiter[42][r]+"','"+ExcelReader.mitarbeiter[43][r]+"','"+ExcelReader.mitarbeiter[44][r]+"','"+ExcelReader.mitarbeiter[45][r]+"','"+ExcelReader.mitarbeiter[46][r]+"','"+ExcelReader.mitarbeiter[47][r]+"','"+ExcelReader.mitarbeiter[48][r]+"','"+ExcelReader.mitarbeiter[49][r]+"','"+ExcelReader.mitarbeiter[50][r]+"','"+ExcelReader.mitarbeiter[51][r]+"','"+ExcelReader.mitarbeiter[52][r]+"','"+ExcelReader.mitarbeiter[53][r]+"','"+ExcelReader.mitarbeiter[54][r]+"','"+ExcelReader.mitarbeiter[55][r]+"','"+ExcelReader.mitarbeiter[56][r]+"','"+ExcelReader.mitarbeiter[57][r]+"','"+ExcelReader.mitarbeiter[58][r]+"','"+ExcelReader.mitarbeiter[59][r]+"','"+ExcelReader.mitarbeiter[60][r]+"','"+ExcelReader.mitarbeiter[61][r]+"','"+ExcelReader.mitarbeiter[62][r]+"','"+ExcelReader.mitarbeiter[63][r]+"','"+ExcelReader.mitarbeiter[64][r]+"','"+ExcelReader.mitarbeiter[65][r]+"','"+ExcelReader.mitarbeiter[66][r]+"')";
				
				String sql = "INSERT INTO mitarbeiter(ANGA, GruppenNr, AAktiv, PersNr, StichtagUrlaub, Eintritt, SVNr, Name, Vorname)"
						+"VALUES('"+ExcelReader.mitarbeiter[0][r]+"','"+ExcelReader.mitarbeiter[1][r]+"','"+ExcelReader.mitarbeiter[2][r]+"','"+ExcelReader.mitarbeiter[3][r]+"','"+ExcelReader.mitarbeiter[4][r]+"','"+ExcelReader.mitarbeiter[5][r]+"','"+ExcelReader.mitarbeiter[6][r]+"','"+ExcelReader.mitarbeiter[7][r]+"','"+ExcelReader.mitarbeiter[8][r]+"')";
				
				ResultSet res= stmt.executeQuery(sql);	
				res.close();
				
				}
			
				stmt.close();
			}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		
			
		
		}
		
	//David
		public void deleteEmployee(String PersNr) {
			ResultSet res =null;
			
			try {
				
				Statement stmt =con.createStatement();
			
				//SQL Befehl
				
				String sql = "DELETE FROM mitarbeiter WHERE PersNr= '"+PersNr+ "'";
			
			
				 res= stmt.executeQuery(sql);
				
				
				res.close();
				stmt.close();
			
				}
					catch(SQLException e)
					{
					e.printStackTrace(); 
					}
			
			
		}
		
		//David
		public void deleteProject(String ProjektNr) {
			ResultSet res =null;
			
			try {
				
				Statement stmt =con.createStatement();
			
				//SQL Befehl
				
				String sql = "DELETE FROM projekt WHERE ProjektNr='"+ProjektNr+"'";
			
			
				 res= stmt.executeQuery(sql);
				
				
				res.close();
				stmt.close();
			
				}
					catch(SQLException e)
					{
					e.printStackTrace(); 
					}
			
			
		}
		
		
	
	
	public String selectPasswort() 
	{
		String passwort=null;
		try {
		
			Statement stmt =con.createStatement();
		
			//SQL Befehl
			
			String sql = "SELECT password FROM passwort";
		
		
			ResultSet res= stmt.executeQuery(sql);	
			
			
				while(res.next())
					{
						passwort = res.getString(1);                     //Spalte 1
						//String firstname = res.getString(2);			  //Spalte 2
						//String lastname = res.getString(3);			      //...
						//String name = res.getString(4);
						
						//Ausgabe
			
						
			
					}
		
				res.close();
				stmt.close();
		
			}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		System.out.println(passwort);
		return passwort;
	}
	
	public void updatePasswort(String neuesPasswort) {
		
		String passwort=neuesPasswort;
		try {
		
			Statement stmt =con.createStatement();
		
			//SQL Befehl
			
			String sql = "UPDATE passwort SET password=  '"+passwort+"';";
		
		
			ResultSet res= stmt.executeQuery(sql);	
			
			
				
				res.close();
				stmt.close();
		
			}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
		System.out.println(passwort);
	}
	
	// David auf marcel angepasst
		public void updateMitarbeiter(String PersoNr, List<String> tablecontent) {
			ResultSet res=null;
			String PersNr=PersoNr;
			
			try {
			
				Statement stmt =con.createStatement();
			
				//SQL Befehl
				
				//String sql = "UPDATE mitarbeiter SET ANGA='"+tablecontent.get(0)+"', GruppenNr='"+tablecontent.get(1)+"', AAktiv='"+tablecontent.get(2)+"',PersNr='"+tablecontent.get(3)+"', StichtagUrlaub='"+tablecontent.get(4)+"', Eintritt='"+tablecontent.get(5)+"', SVNr='"+tablecontent.get(6)+"', Name='"+tablecontent.get(7)+"', Vorname='"+tablecontent.get(8)+"'WHERE PersNr='"+PersNr+"'";//, NameuVorname='"+tablecontent.get(9)+"', PlzOrt='"+tablecontent.get(10)+"', Strasse='"+tablecontent.get(11)+"', GebDat='"+tablecontent.get(12)+"', Stand='"+tablecontent.get(13)+"', Geschl='"+tablecontent.get(14)+"', Nation='"+tablecontent.get(15)+"', Tel='"+tablecontent.get(16)+"', Handy='"+tablecontent.get(17)+"', Email='"+tablecontent.get(18)+"', gelernterBeruf='"+tablecontent.get(19)+"', Aufgabenbereich='"+tablecontent.get(20)+"', Sicherheitsvertrauensperson='"+tablecontent.get(21)+"', Firmenhandy='"+tablecontent.get(22)+"', Kennzeichen='"+tablecontent.get(23)+"', Fahrzeug='"+tablecontent.get(24)+"', FIN='"+tablecontent.get(25)+"', Handwerkerbefreiung='"+tablecontent.get(26)+"', Führerscheine='"+tablecontent.get(27)+"', FührerscheinNr='"+tablecontent.get(28)+"', Behörde='"+tablecontent.get(29)+"', Ausstelldat='"+tablecontent.get(30)+"', gültigbis='"+tablecontent.get(31)+"', Führerscheinüberprüfung='"+tablecontent.get(32)+"', InfoschrPrivatnutzung='"+tablecontent.get(33)+"', EzB='"+tablecontent.get(34)+"', FahrbewilligungNr='"+tablecontent.get(35)+"', Kranführerausbildung='"+tablecontent.get(36)+"', C95LKW='"+tablecontent.get(37)+"', HubstaplerÖ='"+tablecontent.get(38)+"', HubstaplerDE='"+tablecontent.get(39)+"', Notfallperson='"+tablecontent.get(40)+"', NotfallsTel='"+tablecontent.get(41)+"',TShirt='"+tablecontent.get(42)+"', Jacke='"+tablecontent.get(43)+"', Schuhe='"+tablecontent.get(44)+"', GültigkeitA1='"+tablecontent.get(45)+"', SicherheitspassNr='"+tablecontent.get(46)+"', Bürounterweisung='"+tablecontent.get(47)+"', VBFnAPG='"+tablecontent.get(48)+"', Bayernwerkgültigbis='"+tablecontent.get(49)+"', TenneT='"+tablecontent.get(50)+"', TowerLatchSystem='"+tablecontent.get(51)+"', Steigschulung='"+tablecontent.get(52)+"', GUntersuchungenfälligam='"+tablecontent.get(53)+"', GUntersuchungenfällig='"+tablecontent.get(54)+"', PSAÜberprüfung='"+tablecontent.get(55)+"', EHKursDauer='"+tablecontent.get(56)+"', EHKursam='"+tablecontent.get(57)+"', ErsthelferbisDE='"+tablecontent.get(58)+"', nötigeDauer='"+tablecontent.get(59)+"', APGSchulung='"+tablecontent.get(60)+"', RichtigesPrüfenARCUS='"+tablecontent.get(61)+"', LumpiSeilschulung='"+tablecontent.get(62)+"', Compliance='"+tablecontent.get(63)+"', Teleskopstapler='"+tablecontent.get(64)+"', Hubarbeitsbühnen='"+tablecontent.get(65)+"', HubarbeitsbKartenNr='"+tablecontent.get(66)+"'WHERE PersNr='"+PersNr+"'";
				String sql = "UPDATE mitarbeiter SET PersNr='"+tablecontent.get(0)+"', Name='"+tablecontent.get(1)+"', Vorname='"+tablecontent.get(2)+"'WHERE PersNr='"+PersNr+"'";
				
			
				 res= stmt.executeQuery(sql);	
				
					res.close();
					stmt.close();
			
				}
					catch(SQLException e)
					{
					e.printStackTrace(); 
					}
			
			
			
			
		}
		// David
		public void updateProjekt(String ProjektNr, List<String> tablecontent) {
			ResultSet res=null;
			
			
			try {
			
				Statement stmt =con.createStatement();
			
				//SQL Befehl
				
				String sql = "UPDATE projekte SET ProjektNr='"+tablecontent.get(0)+"', Projektname='"+tablecontent.get(1)+"', GruppenNr='"+tablecontent.get(2)+"', sonstigeMitarbeiterPersNr='"+tablecontent.get(3)+"', Ort='"+tablecontent.get(4)+"', Startdatum='"+tablecontent.get(5)+"', Enddatum='"+tablecontent.get(6)+"' WHERE ProjektNr='"+ProjektNr+"'";
			
			
				 res= stmt.executeQuery(sql);	
				
					res.close();
					stmt.close();
			
				}
					catch(SQLException e)
					{
					e.printStackTrace(); 
					}
			
			
			
			
		}
		
		public void insertProject(int ProjektNr, String Projektname, String Ort, String Startdatum, String Enddatum ){
			
			ResultSet res=null;
			
			
				try {
				
					Statement stmt =con.createStatement();
					
					
					 //SQL Befehl
					
				String sql = "INSERT INTO projekt VALUES ('"+ProjektNr+"','"+Projektname+"','"+Ort+"','"+Startdatum+"','"+Enddatum+"')";
					 res= stmt.executeQuery(sql);	
					
						res.close();		
						stmt.close();
					}
						catch(SQLException e)
						{
						e.printStackTrace(); 
						}
			}
		
		public void mitarbeiterliste_füllen(int persnr) {
			
			
			
			Employee xx =new Employee(persnr);
			mitarbeiter.add(xx);
			
		}
		
		public int Projektecount() 
		{
			
			String count=null;
			int countint=0;
			ResultSet res=null;
			
			try {
			
				Statement stmt =con.createStatement();
			
				//SQL Befehl
				
				//String sql = "SELECT ANGA, GruppenNr, AAktiv, PersNr, StichtagUrlaub, Eintritt, SVNr, Name, Vorname";//, NameuVorname, PlzOrt, Strasse, GebDat, Stand, Geschl, Nation, Tel, Handy, Email, gelernterBeruf, Aufgabenbereich, Sicherheitsvertrauensperson, Firmenhandy, Kennzeichen, Fahrzeug, FIN, Handwerkerbefreiung, Führerscheine, FührerscheinNr, Behörde, Ausstelldat, gültigbis, Führerscheinüberprüfung, InfoschrPrivatnutzung, EzB, FahrbewilligungNr, Kranführerausbildung, C95LKW, HubstaplerÖ, HubstaplerDE, Notfallperson, NotfallsTel,TShirt, Jacke, Schuhe, GültigkeitA1, SicherheitspassNr, Bürounterweisung, VBFnAPG, Bayernwerkgültigbis, TenneT, TowerLatchSystem, Steigschulung, GUntersuchungenfälligam, GUntersuchungenfällig, PSAÜberprüfung, EHKursDauer, EHKursam, ErsthelferbisDE, nötigeDauer, APGSchulung, RichtigesPrüfenARCUS, LumpiSeilschulung, Compliance, Teleskopstapler, Hubarbeitsbühnen, HubarbeitsbKartenNr FROM mitarbeiter";
				
			String sql = "SELECT COUNT(ProjektNr) FROM projekt";
			
				res= stmt.executeQuery(sql);	
				
				while(res.next())
				{
					count = res.getString(1);
					
		
					
		
				}	
				
				countint=Integer.parseInt(count);
				res.close();
				stmt.close();
			
				}
				catch(SQLException e)
				{
				e.printStackTrace(); 
				}
			
			
			return countint;
		}
		
		
		public String Verfuegbarkeit(int PersNr){
			
			
			
			String verfueg=null;
			ResultSet res=null;
			String von=null;
			String bis=null;
			
			try {
			
				Statement stmt =con.createStatement();
				
			
				//SQL Befehl
				
				String sql = "SELECT * FROM ist WHERE PersNr='"+PersNr+"'";
				
				res= stmt.executeQuery(sql);
				
				
				

				while(res.next())
				{
			
					
					
					
					
					von = res.getString(1);
					bis = res.getString(2);
					verfueg = res.getString(3);
					
					
		
				}	
				
				
				}
			
					catch(SQLException e)
					{
					e.printStackTrace(); 
					}
			    
			    
			
			return verfueg;
			
			
		}
		
		public void projektliste_füllen(int projektnummer, String projektname, Date startdatum, Date enddatum) {
			
			
			
			Project xx =new Project(projektnummer, projektname,startdatum, enddatum);
			projekte.add(xx);
			
		}
		
		


		public ArrayList<Employee> getMitarbeiter() {
			return mitarbeiter;
		}


		public void setMitarbeiter(ArrayList<Employee> mitarbeiter) {
			this.mitarbeiter = mitarbeiter;
		}


		public ArrayList<Project> getProjekte() {
			return projekte;
		}


		public void setProjekte(ArrayList<Project> projekte) {
			this.projekte = projekte;
		}
		
		

}