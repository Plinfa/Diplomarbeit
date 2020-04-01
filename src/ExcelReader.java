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
	private int lastRowNum;
	private int r;


	private FileInputStream fileInputStream;
	
	
	public void openfile(String filePath, JDBC_MariaDB jdbc)throws EncryptedDocumentException, IOException {
		try {
		
		File file = new File(filePath);
		fileInputStream = new FileInputStream(file);
		
		// Exel Datei lesen / workbook erstellen
		workbook = WorkbookFactory.create(fileInputStream);

		// Tabelle laden - Tabellenname eingeben
		sheet = workbook.getSheet("Tabelle1");
		//letzte zeile herausfinden
		lastRowNum = sheet.getLastRowNum();
						
				for( r=1;r<=lastRowNum;r++) {
						
							
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
				
		}
		
}
		
		
		
	



