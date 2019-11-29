/*import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
 
public class ExcelWriter {
     
    // Spalten der Tabelle bestimmen
    private String[] colums = {"Name", "Email", "Geburtstag"};
    private List<Employee> employees = new ArrayList<>();
     
    public void createEmployee() {
        
        
        employees.add(new Employee("Christian Müller"));
         
        
        employees.add(new Employee("Thomas Zimmermann"));
 
        
        employees.add(new Employee("Steve olgi "));
         
        
        employees.add(new Employee("Katja rode"));
    }
     
    public void createFile(String filePath) throws Exception {
        //Exel Datei erstellen
        // HSSFWorkbook Klasse für .xls Exel Dateien
        // XSSFWorkbook für .xlsx Exel Dateien
     
        Workbook workbook = new XSSFWorkbook();
         
        // Hilft uns, Objekte wie Hyperlinks, verschiede Textformate zu erstellen
        CreationHelper creationHelper = workbook.getCreationHelper();
     
        // Tabelle in Exel Datei erstellen
        Sheet sheet = workbook.createSheet("Angestellte");
         
        // Schrift stylen - Hier die Spaltenüberschrift
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 16); // Schriftgröße
        headerFont.setColor(IndexedColors.BLACK.getIndex()); // Schriftfarbe
        headerFont.setBold(true); // Text dick 
         
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
         
        // Zeile (Reihe) erstellen
        Row headerRow = sheet.createRow(0);
         
        // Kästchen Inhalt erstellen - Hier die Spaltenüberschriften
        for(int i = 0; i < colums.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colums[i]);
            cell.setCellStyle(headerCellStyle);
        }
         
        // Kästchen Style für das Datum erstellen
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));
         
        // Angestellte in Tabelle schreiben
        int rowNum = 1;
        for(Employee employee : employees) {
            Row row = sheet.createRow(rowNum++);  // ++ da in der ersten Zeile, die Spaltenüberschriften stehen
             
            row.createCell(0).setCellValue(employee.getName()); // Name
             
            // Datum
            Cell dateOfBirthCell = row.createCell(2);
            dateOfBirthCell.setCellStyle(dateCellStyle); // Style vom Datum festlegen
             
            
        }
         
        // Spaltengröße an Inhalt anpassen
        for(int i = 0; i < colums.length; i++) {
            sheet.autoSizeColumn(i);
        }
         
        // Datei schreiben / erstellen
        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
         
        fileOutputStream.close();
        workbook.close();
         
    }
    }*/