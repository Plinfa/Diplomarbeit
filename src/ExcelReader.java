import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.sql.Date;
import java.util.Date;
//import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	private Workbook workbook; // Die Exel Datei
	private Sheet sheet; // Eine Exel Tabelle
	private Row row; // Eine Zeile in der Tabelle
	private Cell cell; // Ein KÃ¤stchen
	public static  String[][] mitarbeiter;
	private int lastCellNum;
	private int lastRowNum;
	private int r;
	private int c;

	private FileInputStream fileInputStream;
	
	
	public void openfile(String filePath, JDBC_MariaDB jdbc)throws EncryptedDocumentException, IOException {
		try {
		
		File file = new File(filePath);
		fileInputStream = new FileInputStream(file);
		
		//mitarbeiter = new String[130][130]; 
		
		
		// Exel Datei lesen / erstellen
		workbook = WorkbookFactory.create(fileInputStream);

		// Tabelle laden - Tabellenname eingeben
		sheet = workbook.getSheet("Tabelle1");
		//sheet = workbook.getSheetAt(0);
		
		
		
		
				
				
					
					lastRowNum = sheet.getLastRowNum();
					
					System.out.println(lastRowNum);
					

				
					//letzte zeile herausfinden
					for( r=1;r<=lastRowNum;r++) {
						
						
						 //lastCellNum = sheet.getRow(r).getLastCellNum();
						
						// System.out.println(lastCellNum);
						
						 
						
						//for( c=1;c<=lastCellNum;c++) {
							
							
							//System.out.print(sheet.getRow(r).getCell(c-1)); //c-1 da der Zähler der letzten Zelle nicht bei 0 beginnt
							
							
							//cell= sheet.getRow(r).getCell(0);
						
							//String PersoNr=sheet.getRow(r).getCell(0).toString();
							//int PersNr= Integer.parseInt(PersoNr);
							
						double Persnr=sheet.getRow(r).getCell(0).getNumericCellValue();
							
							int PersNr=(int)Persnr;
							
							String Name=sheet.getRow(r).getCell(2).getStringCellValue();
							
							String Nachname=sheet.getRow(r).getCell(1).getStringCellValue();
							
							Date GebDat1= sheet.getRow(r).getCell(3).getDateCellValue();
							java.sql.Date GebDat = new java.sql.Date(GebDat1.getTime());
							
							String Tätigkeit=sheet.getRow(r).getCell(4).getStringCellValue();
							
							String EMail=sheet.getRow(r).getCell(5).getStringCellValue();
							
							//String Fuehrerschein=sheet.getRow(r).getCell(6).getStringCellValue();
							
							
							jdbc.insertallfromExcel(PersNr,Name,Nachname, GebDat, Tätigkeit, EMail);
					}
					fileInputStream.close();
					workbook.close();
					
					}catch (Exception e) {
					e.printStackTrace();
				}
				
				/*
					
					 if(cell!=null) {
						 
						 if(c==3) {
							 mitarbeiter[c-1][r]=cell.getDateCellValue();
						 }
						 mitarbeiter[c-1][r]=cell.toString(); 
						 
						
					 }
					 else {
						 mitarbeiter[c-1][r]="";
						 
					 }
					*/
				
				
						 
				//System.out.print(mitarbeiter[c-1][r]);
				//System.out.print(" ");
				
			
			
			//System.out.println("");
			
			
		}
		
}
		
		
		
	



