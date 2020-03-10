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
import java.time.format.*;
import javax.swing.JOptionPane;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class JDBC_MariaDB {
	private ArrayList<Employee> mitarbeiter = new ArrayList<Employee>();
	private ArrayList<Project> projekte = new ArrayList<Project>();
	Connection con = null;

	// Konstruktor
	public JDBC_MariaDB() {

		try {
			Class.forName("org.mariadb.jdbc.Driver");

		} catch (ClassNotFoundException e)

		{
			return;
		}

		try {

			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eqospersonalplanung", "root",
					"5455809Otto");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Methoden
	// david update 25,09,2019
	public ResultSet selectAllMitarbeiter() {

		ResultSet res = null;
		ResultSet resu = null;
		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT * FROM mitarbeiter";

			res = stmt.executeQuery(sql);
			resu = stmt.executeQuery(sql);

			while (!resu.isLast()) // as long as valid data is in the result set
			{

				resu.next(); // go to next line in the customer table

				int persnr = resu.getInt(1);

				mitarbeiterliste_füllen(persnr);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public ResultSet selectTabelleMitarbeiter() {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT * FROM mitarbeiter";

			res = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public ResultSet selectMitarbeiterinfo() {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT PersNr, Name, Nachname FROM mitarbeiter";

			res = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public ResultSet selectMitarbeiterPartieinfo() {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT PersNr, Name, Nachname FROM mitarbeiter WHERE PersNr NOT IN (SELECT PersNr FROM leitetpartie) AND PersNr NOT IN (SELECT PersNr FROM zugeteilt)";

			res = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	public ResultSet selectPartieleiterinfo() {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT PersNr, Name, Nachname FROM mitarbeiter WHERE PersNr NOT IN (SELECT PersNr FROM leitetpartie) AND PersNr NOT IN (SELECT PersNr FROM zugeteilt) AND Tätigkeit='Partieführer'";

			res = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	public ResultSet selectPartieLeiter() {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT leitetpartie.PersNr, leitetpartie.PartieNr, mitarbeiter.Name, mitarbeiter.Nachname  FROM leitetpartie JOIN mitarbeiter ON leitetpartie.PersNr=mitarbeiter.PersNr";

			res = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public ResultSet selectAbwesenheiten(int PersNr) {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT von, bis, Grund FROM abwesenheit WHERE PersNr='" + PersNr + "'";

			res = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	public ResultSet selectPartien(int PartieNr) {

		ResultSet res = null;
		//int PersNrLeiter=PersNr;
		//int PartieNr=0;
		

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

		 
		
			String sql1= "SELECT  mitarbeiter.PersNr, mitarbeiter.Name, mitarbeiter.Nachname FROM mitarbeiter JOIN zugeteilt ON mitarbeiter.PersNr=zugeteilt.PersNr WHERE PartieNr = '"+PartieNr+"' ";
			res = stmt.executeQuery(sql1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return res;
	}
	public ResultSet selectZuteilungen(int PersNr) {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT von, bis, ProjektNr FROM arbeitet WHERE PersNr='" + PersNr + "'";

			res = stmt.executeQuery(sql);
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public ResultSet selectPersbedarfplanungfürProj(int ProjNr) {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT von, bis, Anzahl FROM benötigt WHERE ProjektNr='" + ProjNr + "'";

			res = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public ResultSet selectAllProjects() {
		ResultSet res = null;
		// ResultSet resu = null;
		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT * FROM projekt";

			res = stmt.executeQuery(sql);
			// resu = stmt.executeQuery(sql);

			while (!res.isLast()) // as long as valid data is in the result set
			{

				res.next(); // go to next line in the customer table

				int projektnummer = res.getInt(1);
				String projektname = res.getString(2);
				Date startdatum = res.getDate(4);
				Date enddatum = res.getDate(5);

				projektliste_füllen(projektnummer, projektname, startdatum, enddatum);

			}
			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public ResultSet selectTabelleProjects() {
		ResultSet res = null;
		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT ProjektNr, Name, Ort, von, bis FROM projekt";

			res = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public IntervalCategoryDataset zeitplanung() {

		ResultSet res = null;
		TaskSeriesCollection dataset = new TaskSeriesCollection();
		TaskSeries series1 = new TaskSeries("erwartet");

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT * FROM projekt";

			res = stmt.executeQuery(sql);

			while (!res.isLast()) // as long as valid data is in the result set
			{

				res.next(); // go to next line in the customer table

				java.sql.Date heute = new java.sql.Date(Calendar.getInstance().getTime().getTime());

				String projektname = res.getString(2);
				Date startdatum = res.getDate(4);
				Date enddatum = res.getDate(5);
				if (enddatum.compareTo(heute) < 0) {

				}

				else {
					
					if (startdatum.compareTo(heute) < 0) {

						startdatum = heute;

					}
					
					series1.add(new Task(projektname, startdatum, enddatum));
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		dataset.add(series1);

		return dataset;

	}

	public IntervalCategoryDataset zuteilungdetailansicht(int PersNr) {

		ResultSet res = null;
		TaskSeriesCollection dataset = new TaskSeriesCollection();
		TaskSeries series1 = new TaskSeries("erwartet");

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT projekt.Name,arbeitet.von, arbeitet.bis FROM arbeitet JOIN projekt ON arbeitet.ProjektNr=projekt.ProjektNr WHERE PersNr= '"
					+ PersNr + "'";

			res = stmt.executeQuery(sql);

			while (!res.isLast()) // as long as valid data is in the result set
			{

				res.next(); // go to next line in the customer table

				java.sql.Date heute = new java.sql.Date(Calendar.getInstance().getTime().getTime());

				String projektname = res.getString(1);
				Date startdatum = res.getDate(2);
				Date enddatum = res.getDate(3);
				
				if (enddatum.compareTo(heute) < 0) {

				}

				else {
					
					if (startdatum.compareTo(heute) < 0) {

						startdatum = heute;

					}
					
					series1.add(new Task(projektname, startdatum, enddatum));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dataset.add(series1);
		return dataset;

	}

	public void Mitarbeiterzuteilen(int PersNr, java.sql.Date von, java.sql.Date bis, int projnr) {

		ResultSet res = null;
		boolean verfueg = verfuegbarkeitabfrage(PersNr, von, bis);

		try {

			Statement stmt = con.createStatement();

			if (verfueg == false) {
				JOptionPane.showMessageDialog(null,
						"Mitarbeiter ist in diesem Zeitraum in mindestens einer Woche nicht verfügbar", "Fehler",
						JOptionPane.ERROR_MESSAGE);

			} else {
				String sql = "INSERT INTO arbeitet (von, bis, ProjektNr, PersNr) VALUES('" + von + "','" + bis + "','"
						+ projnr + "','" + PersNr + "')";
				res = stmt.executeQuery(sql);
				res.close();
				stmt.close();
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		// JOptionPane.showMessageDialog(null, "Mitarbeiter erfolgreich hinzugefügt ",
		// "Bestätigen", JOptionPane.OK_CANCEL_OPTION);

	}
	
	public void Partiehinzufuegen(int PersNr) {

		ResultSet res = null;
		

		try {

			Statement stmt = con.createStatement();

			
				String sql = "INSERT INTO leitetpartie (PersNr) VALUES('" + PersNr + "')";
				res = stmt.executeQuery(sql);
				res.close();
				stmt.close();
			

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	public void Partiezuteilen(int PersNr, java.sql.Date von, java.sql.Date bis, int projnr) {

		ResultSet res = null;
		int PersNrLeiter=PersNr;
		
		boolean verfueg = verfuegbarkeitabfrage(PersNr, von, bis);
		int PartieNr=0;
		int PersNrMitarbeiter=0;

		try {
			
			 Mitarbeiterzuteilen(PersNrLeiter, von,  bis, projnr);
			Statement stmt = con.createStatement();
			String sql= "SELECT PartieNr FROM leitetpartie WHERE PersNr='"+PersNrLeiter+"' ";
			res = stmt.executeQuery(sql);
			

			
			while (!res.isLast()) // as long as valid data is in the result set
			{

				res.next(); 
				PartieNr = res.getInt(1);
				
			    System.out.print(PartieNr);
			}
				 
				
			    
			
			
			
			String sql1= "SELECT  mitarbeiter.PersNr FROM mitarbeiter JOIN zugeteilt ON mitarbeiter.PersNr=zugeteilt.PersNr WHERE PartieNr = '"+PartieNr+"' ";
			res = stmt.executeQuery(sql1);
			
			while (!res.isLast()) // as long as valid data is in the result set
			{

				res.next(); 
				PersNrMitarbeiter= res.getInt(1);
				
				 Mitarbeiterzuteilen(PersNrMitarbeiter, von,  bis, projnr);
				
			    
			}
		
			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void MitarbeiterzuPartie(int PersNr, int PartieNr) {

		ResultSet res = null;
		

		try {

			Statement stmt = con.createStatement();

		/*
				JOptionPane.showMessageDialog(null,
						"Mitarbeiter ist in diesem Zeitraum in mindestens einer Woche nicht verfügbar", "Fehler",
						JOptionPane.ERROR_MESSAGE);

			*/
				String sql = "INSERT INTO zugeteilt (PersNr, PartieNr) VALUES('"+ PersNr + "','" + PartieNr + "')";
				res = stmt.executeQuery(sql);
				res.close();
				stmt.close();
			

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void insertEmployee(int PersoNr, String Name, String Nachname) {

		ResultSet res = null;
		int PersNr = PersoNr;
		// String Name=Name;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "INSERT INTO mitarbeiter (PersNr, Name, Nachname) VALUES ('" + PersNr + "','" + Name + "','"
					+ Nachname + "')";
			res = stmt.executeQuery(sql);

			res.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// David angepasst für marcel
	public void insertallfromExcel(int PersNr, String Name, String Nachname, java.sql.Date GebDat, String Tätigkeit, String EMail /*String Fuehrerschein*/) { // bearbeiten reihenanzahl von excelreader fehlt nicht sicher ob benötigt

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl
			String sql="INSERT INTO mitarbeiter(PersNr, Name,Nachname,GebDat,Tätigkeit,EMail) VALUES('"+PersNr+"','"+Name+"','"+Nachname+"','"+GebDat+"','"+Tätigkeit+"','"+EMail+"')";

			//String sql="INSERT INTO mitarbeiter(PersNr, Name, Nachname, GebDat, Tätigkeit, EMail, Führerschein) VALUES('"+PersNr+"','"+Name+"','"+Nachname+"','"+GebDat+"','"+Tätigkeit+"','"+EMail+"','"+Fuehrerschein+"')";
			/*
			for (int r = 1; r <= 122; r++) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MM, yyyy", Locale.ENGLISH);
				LocalDate date1 = LocalDate.parse(ExcelReader.mitarbeiter[3][r], formatter);
				Date date = Date.valueOf(date1);
				//Date GebDate= ExcelReader.mitarbeiter[3][r];		//in Java Date speichern
				
				 
				
				System.out.println(ExcelReader.mitarbeiter[3][r]);
				//ExcelReader.mitarbeiter[3][r];
				 Date date1=Date.valueOf(ExcelReader.mitarbeiter[3][r]);//converting string into sql date  
				 java.sql.Date date = new java.sql.Date(date1.getTime());
				 //System.out.println(date); 
				    
				String sql= "INSERT INTO mitarbeiter (PersNr, Name, Nachname, GebDat, Tätigkeit, EMail, Führerschein) "
						+ "VALUES ('"+ExcelReader.mitarbeiter[0][r]+"','"+ExcelReader.mitarbeiter[2][r]+"','"+ExcelReader.mitarbeiter[1][r]+"','"+date+"','"+ExcelReader.mitarbeiter[5][r]+"','"+ExcelReader.mitarbeiter[4][r]+"','"+ExcelReader.mitarbeiter[6][r]+"')";

				// String sql = "INSERT INTO mitarbeiter(ANGA, GruppenNr, AAktiv, PersNr,
				// StichtagUrlaub, Eintritt, SVNr, Name, Vorname, NameuVorname, PlzOrt, Strasse,
				// GebDat, Stand, Geschl, Nation, Tel, Handy, Email, gelernterBeruf,
				// Aufgabenbereich, Sicherheitsvertrauensperson, Firmenhandy, Kennzeichen,
				// Fahrzeug, FIN, Handwerkerbefreiung, Führerscheine, FührerscheinNr, Behörde,
				// Ausstelldat, gültigbis, Führerscheinüberprüfung, InfoschrPrivatnutzung, EzB,
				// FahrbewilligungNr, Kranführerausbildung, C95LKW, HubstaplerÖ, HubstaplerDE,
				// Notfallperson, NotfallsTel,TShirt, Jacke, Schuhe, GültigkeitA1,
				// SicherheitspassNr, Bürounterweisung, VBFnAPG, Bayernwerkgültigbis, TenneT,
				// TowerLatchSystem, Steigschulung, GUntersuchungenfälligam,
				// GUntersuchungenfällig, PSAÜberprüfung, EHKursDauer, EHKursam,
				// ErsthelferbisDE, nötigeDauer, APGSchulung, RichtigesPrüfenARCUS,
				// LumpiSeilschulung, Compliance, Teleskopstapler, Hubarbeitsbühnen,
				// HubarbeitsbKartenNr)"
				// +"VALUES('"+ExcelReader.mitarbeiter[0][r]+"','"+ExcelReader.mitarbeiter[1][r]+"','"+ExcelReader.mitarbeiter[2][r]+"','"+ExcelReader.mitarbeiter[3][r]+"','"+ExcelReader.mitarbeiter[4][r]+"','"+ExcelReader.mitarbeiter[5][r]+"','"+ExcelReader.mitarbeiter[6][r]+"','"+ExcelReader.mitarbeiter[7][r]+"','"+ExcelReader.mitarbeiter[8][r]+"','"+ExcelReader.mitarbeiter[9][r]+"','"+ExcelReader.mitarbeiter[10][r]+"','"+ExcelReader.mitarbeiter[11][r]+"','"+ExcelReader.mitarbeiter[12][r]+"','"+ExcelReader.mitarbeiter[13][r]+"','"+ExcelReader.mitarbeiter[14][r]+"','"+ExcelReader.mitarbeiter[15][r]+"','"+ExcelReader.mitarbeiter[16][r]+"','"+ExcelReader.mitarbeiter[17][r]+"','"+ExcelReader.mitarbeiter[18][r]+"','"+ExcelReader.mitarbeiter[19][r]+"','"+ExcelReader.mitarbeiter[20][r]+"','"+ExcelReader.mitarbeiter[21][r]+"','"+ExcelReader.mitarbeiter[22][r]+"','"+ExcelReader.mitarbeiter[23][r]+"','"+ExcelReader.mitarbeiter[24][r]+"','"+ExcelReader.mitarbeiter[25][r]+"','"+ExcelReader.mitarbeiter[26][r]+"','"+ExcelReader.mitarbeiter[27][r]+"','"+ExcelReader.mitarbeiter[28][r]+"','"+ExcelReader.mitarbeiter[29][r]+"','"+ExcelReader.mitarbeiter[30][r]+"','"+ExcelReader.mitarbeiter[31][r]+"','"+ExcelReader.mitarbeiter[32][r]+"','"+ExcelReader.mitarbeiter[33][r]+"','"+ExcelReader.mitarbeiter[34][r]+"','"+ExcelReader.mitarbeiter[35][r]+"','"+ExcelReader.mitarbeiter[36][r]+"','"+ExcelReader.mitarbeiter[37][r]+"','"+ExcelReader.mitarbeiter[38][r]+"','"+ExcelReader.mitarbeiter[39][r]+"','"+ExcelReader.mitarbeiter[40][r]+"','"+ExcelReader.mitarbeiter[41][r]+"','"+ExcelReader.mitarbeiter[42][r]+"','"+ExcelReader.mitarbeiter[43][r]+"','"+ExcelReader.mitarbeiter[44][r]+"','"+ExcelReader.mitarbeiter[45][r]+"','"+ExcelReader.mitarbeiter[46][r]+"','"+ExcelReader.mitarbeiter[47][r]+"','"+ExcelReader.mitarbeiter[48][r]+"','"+ExcelReader.mitarbeiter[49][r]+"','"+ExcelReader.mitarbeiter[50][r]+"','"+ExcelReader.mitarbeiter[51][r]+"','"+ExcelReader.mitarbeiter[52][r]+"','"+ExcelReader.mitarbeiter[53][r]+"','"+ExcelReader.mitarbeiter[54][r]+"','"+ExcelReader.mitarbeiter[55][r]+"','"+ExcelReader.mitarbeiter[56][r]+"','"+ExcelReader.mitarbeiter[57][r]+"','"+ExcelReader.mitarbeiter[58][r]+"','"+ExcelReader.mitarbeiter[59][r]+"','"+ExcelReader.mitarbeiter[60][r]+"','"+ExcelReader.mitarbeiter[61][r]+"','"+ExcelReader.mitarbeiter[62][r]+"','"+ExcelReader.mitarbeiter[63][r]+"','"+ExcelReader.mitarbeiter[64][r]+"','"+ExcelReader.mitarbeiter[65][r]+"','"+ExcelReader.mitarbeiter[66][r]+"')";

				String sql = "INSERT INTO mitarbeiter(ANGA, GruppenNr, AAktiv, PersNr, StichtagUrlaub, Eintritt, SVNr, Name, Vorname)"
						+ "VALUES('" + ExcelReader.mitarbeiter[0][r] + "','" + ExcelReader.mitarbeiter[1][r] + "','"
						+ ExcelReader.mitarbeiter[2][r] + "','" + ExcelReader.mitarbeiter[3][r] + "','"
						+ ExcelReader.mitarbeiter[4][r] + "','" + ExcelReader.mitarbeiter[5][r] + "','"
						+ ExcelReader.mitarbeiter[6][r] + "','" + ExcelReader.mitarbeiter[7][r] + "','"
						+ ExcelReader.mitarbeiter[8][r] + "')";
*/
				ResultSet res = stmt.executeQuery(sql);
				res.close();

			

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void MitarbeiterausPartieloeschen(int PersNr) {
		ResultSet res=null;
		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "DELETE FROM zugeteilt WHERE PersNr='"+PersNr+"' ";
			res = stmt.executeQuery(sql);

			res.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// David
	public void deleteEmployee(String PersNr) {
		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl
			String deletearbeitet = "DELETE FROM arbeitet WHERE PersNr= '" + PersNr + "'";
			String deleteabwesenheit = "DELETE FROM abwesenheit WHERE PersNr= '" + PersNr + "'";
			String deletefaehrt = "DELETE FROM fährt WHERE PersNr= '" + PersNr + "'";
			String deletemitarbeiter = "DELETE FROM mitarbeiter WHERE PersNr= '" + PersNr + "'";

			res = stmt.executeQuery(deletearbeitet);
			res = stmt.executeQuery(deleteabwesenheit);
			res = stmt.executeQuery(deletefaehrt);
			res = stmt.executeQuery(deletemitarbeiter);

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// David
	public void deleteProject(int ProjektNr) {
		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String deletebenötigt = "DELETE FROM benötigt WHERE ProjektNr= '" + ProjektNr + "'";
			String deletearbeitet = "DELETE FROM arbeitet WHERE ProjektNr= '" + ProjektNr + "'";
			String deleteleitet = "DELETE FROM leitetprojekt WHERE ProjektNr= '" + ProjektNr + "'";
			String deleteproject = "DELETE FROM projekt WHERE ProjektNr='" + ProjektNr + "'";

			res = stmt.executeQuery(deletebenötigt);
			res = stmt.executeQuery(deletearbeitet);
			res = stmt.executeQuery(deleteleitet);
			res = stmt.executeQuery(deleteproject);

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteabwesenheit(String von, String bis, int PersNr) {
		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String deleteabwesenheit = "DELETE FROM abwesenheit WHERE von= '" + von + "' AND bis= '" + bis
					+ "' AND PersNr='" + PersNr + "' ";

			res = stmt.executeQuery(deleteabwesenheit);

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteZuteilung(String von, String bis, int PersNr) {
		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String deleteabwesenheit = "DELETE FROM arbeitet WHERE von= '" + von + "' AND bis= '" + bis
					+ "' AND PersNr='" + PersNr + "' ";

			res = stmt.executeQuery(deleteabwesenheit);

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deletebenoetigt(String von, String bis, String Anzahl, int ProjNr) {
		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String deletebenoetigt = "DELETE FROM benötigt WHERE von= '" + von + "' AND bis= '" + bis + "' AND Anzahl='"
					+ Anzahl + "' AND ProjektNr='" + ProjNr + "' ";

			res = stmt.executeQuery(deletebenoetigt);

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void deletePartie(int PartieNr) {
		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			
			String deletezuteilung ="DELETE FROM zugeteilt WHERE PartieNr='"+PartieNr+"'";
			String deletepartieleiter ="DELETE FROM leitetpartie WHERE PartieNr='"+PartieNr+"'";
			
			
			res = stmt.executeQuery(deletezuteilung);
			res = stmt.executeQuery(deletepartieleiter);
			
			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String selectPasswort() {
		String passwort = null;
		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT password FROM passwort";

			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {
				passwort = res.getString(1); // Spalte 1

			}

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(passwort);
		return passwort;
	}

	public void updatePasswort(String neuesPasswort) {

		String passwort = neuesPasswort;
		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "UPDATE passwort SET password=  '" + passwort + "';";

			ResultSet res = stmt.executeQuery(sql);

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(passwort);
	}

	// David auf marcel angepasst
	public void updateMitarbeiter(String PersoNr, String Nachname, String GebDat, String Taetigkeit, String EMail,
			String Fuehrerschein) {
		ResultSet res = null;
		String PersNr = PersoNr;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "UPDATE mitarbeiter SET  Nachname='" + Nachname + "',GebDat='" + GebDat + "', Tätigkeit='"
					+ Taetigkeit + "', EMail= '" + EMail + "', Führerschein='" + Fuehrerschein + "' WHERE PersNr='"
					+ PersNr + "'";

			res = stmt.executeQuery(sql);

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// David
	public void updateProjekt(String ProjektNr, String Name, String Ort, String von, String bis) {
		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "UPDATE projekt SET  Name='" + Name + "',Ort='" + Ort + "', von='" + von + "', bis= '" + bis
					+ "'WHERE ProjektNr='" + ProjektNr + "'";

			res = stmt.executeQuery(sql);

			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertProject(int ProjektNr, String Projektname, String Ort, java.sql.Date Startdatum,
			java.sql.Date Enddatum) {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "INSERT INTO projekt (ProjektNr, Name, Ort, von, bis) VALUES ('" + ProjektNr + "','"
					+ Projektname + "','" + Ort + "','" + Startdatum + "','" + Enddatum + "')";
			res = stmt.executeQuery(sql);

			res.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void mitarbeiterliste_füllen(int persnr) {

		Employee xx = new Employee(persnr);
		mitarbeiter.add(xx);

	}

	public int Projektecount() {

		String count = null;
		int countint = 0;
		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT COUNT(ProjektNr) FROM projekt";

			res = stmt.executeQuery(sql);

			while (res.next()) {
				count = res.getString(1);

			}

			countint = Integer.parseInt(count);
			res.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return countint;
	}

	public boolean verfuegbarkeitabfrage(int PersNr, java.sql.Date von, java.sql.Date bis) {

		java.util.Date vonStart = new java.util.Date(von.getTime());
		java.util.Date bisEnd = new java.util.Date(bis.getTime());

		boolean verfueg = true;
		ResultSet res = null;
		ResultSet res1 = null;
		Date von1 = null;
		Date bis1 = null;
		Date von2 = null;
		Date bis2 = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT * FROM abwesenheit WHERE PersNr='" + PersNr + "'";

			res = stmt.executeQuery(sql);

			while (res.next()) {

				von1 = res.getDate(4);
				bis1 = res.getDate(5);
				// verfueg = res.getString(2);
				if (vonStart.compareTo(von1) < 0 && bisEnd.compareTo(von1) < 0
						|| vonStart.compareTo(bis1) > 0 && bisEnd.compareTo(bis1) > 0) {

					verfueg = true;

				} else {
					verfueg = false;
				}

			}

			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			Statement stmt1 = con.createStatement();

			// SQL Befehl

			String sql1 = "SELECT * FROM arbeitet WHERE PersNr='" + PersNr + "'";

			res1 = stmt1.executeQuery(sql1);

			while (res1.next()) {

				von2 = res1.getDate(3);
				bis2 = res1.getDate(4);
				// verfueg = res.getString(2);

				if (verfueg == true) {

					if (vonStart.compareTo(von2) < 0 && bisEnd.compareTo(von2) < 0
							|| vonStart.compareTo(bis2) > 0 && bisEnd.compareTo(bis2) > 0) {

						verfueg = true;

					}

					else {
						verfueg = false;

					}

				}
			}

			res1.close();
			stmt1.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return verfueg;

	}

	public void unverfuegbarSetzen(int PersNr, java.sql.Date von, java.sql.Date bis, String Grund) {

		ResultSet res = null;

		try {
			boolean verfueg = true;
			String projnr = null;
			java.util.Date von1 = new java.util.Date(von.getTime());
			java.sql.Date von3 = bis;
			java.util.Date bis1 = new java.util.Date(bis.getTime());
			java.sql.Date bis3 = von;
			int pastDay = 7;
			java.sql.Date pastDate = subtractDays(bis3, pastDay);
			Statement stmt = con.createStatement();

			// SQL Befehl
			
			verfueg = verfuegbarkeitabfrage(PersNr, von, bis);

			if (verfueg == false) {
				
				String case1 = "SELECT ProjektNr,von,bis FROM arbeitet WHERE  '" + bis
						+ "' BETWEEN von AND bis AND PersNr='" + PersNr + "'";
				
				String case2 = "SELECT ProjektNr,von,bis FROM arbeitet WHERE  '" + von
						+ "' BETWEEN von AND bis AND PersNr='" + PersNr + "'";
				
				String case3 = "SELECT ProjektNr,von,bis FROM arbeitet WHERE  '" + von
						+ "' AND '"+bis+"' BETWEEN von AND bis AND PersNr='" + PersNr + "'";
				
				String case4 = "SELECT ProjektNr,von,bis FROM arbeitet WHERE von AND bis BETWEEN '"+von
						+"' AND '"+bis+"'  AND PersNr='" + PersNr + "'";
				
				String case5 = "SELECT ProjektNr,von,bis FROM arbeitet WHERE von='"+von
						+"' AND bis='"+bis+"' AND PersNr='" + PersNr + "'";
				
				res = stmt.executeQuery(case1);
				res = stmt.executeQuery(case2);
				res = stmt.executeQuery(case3);
				res = stmt.executeQuery(case4);
				res = stmt.executeQuery(case5);
				
				while (res.next()) {

					projnr = res.getString(1);
					von1 = res.getDate(2);
					bis1 = res.getDate(3);

				}
				if (projnr==null) {
					
					JOptionPane.showMessageDialog(null, "Mitarbeiter ist in diesem Zeitraum schon in mindestens einer Woche abwesend",
							"Fehler", JOptionPane.ERROR_MESSAGE);
							
					
				}
				
				else {
					int ProjektNr = Integer.parseInt(projnr);
					java.sql.Date von2 = new java.sql.Date(von1.getTime());
					java.sql.Date bis2 = new java.sql.Date(bis1.getTime());

					String sql2 = " DELETE FROM arbeitet WHERE '" + von + "' BETWEEN von AND bis AND PersNr='" + PersNr
							+ "'";
					res = stmt.executeQuery(sql2);
					// Zuteilung vorher setzen
					Mitarbeiterzuteilen(PersNr, von2, pastDate, ProjektNr);
					// Zuteilung nachher setzen
					Mitarbeiterzuteilen(PersNr, von3, bis2, ProjektNr);
					
					String sql = "INSERT INTO abwesenheit (PersNr, Grund, von, bis) VALUES ('" + PersNr + "','" + Grund + "','"
							+ von + "','" + bis + "')";
						res = stmt.executeQuery(sql);
				}
			} 
			
			else {
				String sql = "INSERT INTO abwesenheit (PersNr, Grund, von, bis) VALUES ('" + PersNr + "','" + Grund + "','"
					+ von + "','" + bis + "')";
				res = stmt.executeQuery(sql);

				res.close();
				stmt.close();
			}

			
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void persbedarfplanen(int ProjNr, java.sql.Date von, java.sql.Date bis, int anzahl) {

		ResultSet res = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl
			// i think u meant this
			String sql = "INSERT INTO benötigt (Anzahl, von, bis, ProjektNr) VALUES ('" + anzahl + "','" + von + "','"
					+ bis + "','" + ProjNr + "')";

			res = stmt.executeQuery(sql);

			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String countkrank(java.sql.Date abfrage1) {

		ResultSet res = null;
		String count = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT COUNT(PersNr)FROM abwesenheit WHERE Grund = 'krank' AND '" + abfrage1
					+ "' BETWEEN von AND bis";

			res = stmt.executeQuery(sql);

			while (res.next()) {

				count = res.getString(1);

			}

			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return count;

	}

	public String countschulung(java.sql.Date abfrage1) {

		ResultSet res = null;
		String count = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT COUNT(PersNr)FROM abwesenheit WHERE Grund = 'schulung' AND '" + abfrage1
					+ "' BETWEEN von AND bis";

			res = stmt.executeQuery(sql);

			while (res.next()) {

				count = res.getString(1);

			}

			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return count;

	}

	public String counturlaub(java.sql.Date abfrage1) {

		ResultSet res = null;
		String count = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT COUNT(PersNr)FROM abwesenheit WHERE Grund = 'urlaub' AND '" + abfrage1
					+ "' BETWEEN von AND bis";

			res = stmt.executeQuery(sql);

			while (res.next()) {

				count = res.getString(1);

			}

			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return count;

	}

	public String countmitarbeiter() {

		ResultSet res = null;
		String count = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			String sql = "SELECT COUNT(PersNr)FROM mitarbeiter";

			res = stmt.executeQuery(sql);

			while (res.next()) {

				count = res.getString(1);

			}

			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public String countzugeteilt(java.sql.Date abfrage1, int ProjNr) {

		ResultSet res = null;
		String count = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			if (ProjNr == 0) {
				String sql = "SELECT COUNT(PersNr)FROM arbeitet WHERE  '" + abfrage1 + "' BETWEEN von AND bis";
				res = stmt.executeQuery(sql);
			} else {
				String sql = "SELECT COUNT(PersNr)FROM arbeitet WHERE ProjektNr='" + ProjNr + "' AND '" + abfrage1
						+ "' BETWEEN von AND bis";

				res = stmt.executeQuery(sql);

			}

			while (res.next()) {

				count = res.getString(1);

			}

			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return count;

	}

	public String countbenötigt(java.sql.Date abfrage1, int ProjNr) {

		ResultSet res = null;
		String count = null;

		try {

			Statement stmt = con.createStatement();

			// SQL Befehl

			if (ProjNr == 0) {
				String sql = "SELECT SUM(Anzahl)FROM benötigt WHERE  '" + abfrage1 + "' BETWEEN von AND bis";
				res = stmt.executeQuery(sql);
			} else {
				String sql = "SELECT SUM(Anzahl)FROM benötigt WHERE ProjektNr='" + ProjNr + "' AND  '" + abfrage1
						+ "' BETWEEN von AND bis  ";

				res = stmt.executeQuery(sql);

			}
			while (res.next()) {

				count = res.getString(1);
				if (count == null) {
					count = "0";
				}

			}

			res.close();
			stmt.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return count;

	}

	public int auslastung(java.sql.Date abfrage1, int ProjNr) {

		double zugeteilt = Double.parseDouble(countzugeteilt(abfrage1, ProjNr));
		double krank = Double.parseDouble(countkrank(abfrage1));
		double schulung = Double.parseDouble(countschulung(abfrage1));
		double urlaub = Double.parseDouble(counturlaub(abfrage1));
		double mitarbeiter = Double.parseDouble(countmitarbeiter());
		double auslastung = 0;

		auslastung = ((zugeteilt + krank + urlaub + schulung) / mitarbeiter) * 100;

		int auslastung1 = (int) auslastung;

		return auslastung1;
	}

	public void projektliste_füllen(int projektnummer, String projektname, Date startdatum, Date enddatum) {

		Project xx = new Project(projektnummer, projektname, startdatum, enddatum);
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

	public static Date subtractDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -days);
		return new Date(c.getTimeInMillis());
	}
}