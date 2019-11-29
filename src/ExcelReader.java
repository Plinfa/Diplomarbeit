import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

	public void openFile(String filePath) throws EncryptedDocumentException, IOException {
		File file = new File(filePath);
		fileInputStream = new FileInputStream(file);
		
		mitarbeiter = new String[130][130]; 
		
		
		// Exel Datei lesen / erstellen
		workbook = WorkbookFactory.create(fileInputStream);

		// Tabelle laden - Tabellenname eingeben
		sheet = workbook.getSheet("Tabelle1");
		//sheet = workbook.getSheetAt(0);
		
		
		
		lastRowNum = sheet.getLastRowNum();
		
		
		
	System.out.println(lastRowNum);
	
		//letzte zeile herausfinden
		for( r=0;r<=lastRowNum;r++) {
			
			
			 lastCellNum = sheet.getRow(r).getLastCellNum();
			
			// System.out.println(lastCellNum);
			
			 
			
			for( c=1;c<=lastCellNum;c++) {
				
				
				//System.out.print(sheet.getRow(r).getCell(c-1)); //c-1 da der Zähler der letzten Zelle nicht bei 0 beginnt
				
				
				cell= sheet.getRow(r).getCell(c-1);
				
			
				
			
				
				
					
					 if(cell!=null) {
						 mitarbeiter[c-1][r]=cell.toString(); 
						
					 }
					 else {
						 mitarbeiter[c-1][r]="";
						 
					 }
					
				
				
						 
				//System.out.print(mitarbeiter[c-1][r]);
				System.out.print(" ");
				
			}
			
			System.out.println("");
			
			
		}
		
	
		
		fileInputStream.close();
		workbook.close();
		
	}


}
