/**
 * 
 */
package tnp.qa.utils;

import java.io.IOException;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Avinash
 *
 */
public class ExcelUtilities {
	
	private final String EXCEL_FILE_PATH = System.getProperty("user.dir")+"\\testdata\\tnp.xlsx";
	//private XSSFWorkbook workBook;
	private XSSFWorkbook wb;
	private Object[][] dataObject;
	
	
	public ExcelUtilities() {
		/*
		 * The problem with this way of loading excel file and reading data from excel file is that
		 * the consumes too much memory as the fileinputstream first loads the file into the memory.
		 * Using OPCPackage is the best solution.
		 * 
		 * File excelFile = new File(EXCEL_FILE_PATH); try { FileInputStream excelFis =
		 * new FileInputStream(excelFile); workBook = new XSSFWorkbook(excelFis);
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		
		OPCPackage pkg;
		try {
			pkg = OPCPackage.open(EXCEL_FILE_PATH);
			wb = new XSSFWorkbook(pkg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Object[][] getExcelData(String sheetName) {
		
		
		XSSFSheet sheet = wb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();
		int totalColumns = sheet.getRow(0).getLastCellNum();
		
		dataObject = new Object[totalRows][totalColumns];
		
		for(int i=0; i<totalRows; i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0; j<totalColumns; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					dataObject[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					dataObject[i][j]=Integer.toString((int)cell.getNumericCellValue());
//				case BOOLEAN:
//					dataObject[i][j]=cell.getBooleanCellValue();
//					break;
				default:
					break;
				}
			}
		}
		
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataObject;
	}

}
