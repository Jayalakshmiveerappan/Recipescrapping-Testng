


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFunctions {

	public void saveDataToExcel(ArrayList<Object[]> recipedata) throws Exception {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
String[] columnheadings = {"Title","Category","Ingredients","Method","Nutrient values","Recipe Image link","Link to the recipe"};
	    XSSFSheet sheet = workbook.createSheet("Recipe List");
	    
	    Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setFontHeightInPoints((short)12);
	    headerFont.setColor(IndexedColors.BLACK.index);
	    
	    CellStyle style = workbook.createCellStyle();  
          
        style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());  
        style.setFillPattern(FillPatternType.THIN_FORWARD_DIAG);  
	    
	    Row headerRow=sheet.createRow(0);
	    
	    for(int i=0; i<columnheadings.length; i++) {
	    	
            Cell cell = headerRow.createCell(i);
            cell.setCellValue((columnheadings[i]));
            cell.setCellStyle(style);
            }
		    
	    int rownum=1;
	    for(Object[] rlist:recipedata) {
	    	
	    	XSSFRow row =sheet.createRow(rownum++);
	       	int cellnum=0;
	    	for(Object value:rlist) {
	    		
	    		XSSFCell cell = row.createCell(cellnum++);
	    	    cell.setCellValue((String)value);
	    	    		
	    	}	    	
	    }
	     
		    
	    FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\user\\Desktop\\excel1.xlsx"));
	    	workbook.write(fos);
	    	fos.close();
	}
	  	
}



