package util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {
	private static Workbook workbook;
	private static Sheet sheet;
	
	public static void LoadExcel(String filepath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
	}
	public static String getCellData(int row, int col) {
		Cell cell = sheet.getRow(row).getCell(col);
//		if(cell.getCellType()==CellType.STRING)
//			return cell.getStringCellValue();
//		else if (cell.getCellType()==CellType.NUMERIC)  // this will return double
//			return String.valueOf((int)cell.getNumericCellValue());
		
	        DataFormatter formatter = new DataFormatter();
	      
	        return formatter.formatCellValue(cell);
	    
	}
	
	public static int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	public static int getColumnCount() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}
	public static void close() throws IOException {
		workbook.close();
		
	}
	
	public static Object[][] getExcelData() {
        int rowCount = getRowCount();
        int colCount = getColumnCount();
        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {  // Skip header row
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }

}
