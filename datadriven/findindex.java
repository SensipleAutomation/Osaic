package datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.collect.Iterators;

import operators.ExcelUtils;

public class findindex {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//String env = getParameter("env");
		
		String testdatapath = System.getProperty("user.dir") + "\\resources\\TestData_QA.xlsx";
	 // String path = System.getProperty("user.dir") + "\\resources\\TestData_single.xlsx";

        // Object[] testObjArray = ExcelUtils.getTableArray(testdatapath,"Sheet1"); 
       System.out.println(findtcidindex("329"));
       

	}
	
	
	
	 private static int findtcidindex( String tcid) throws EncryptedDocumentException, InvalidFormatException, IOException {
		    
		  String filepath =  System.getProperty("user.dir") + "\\resources\\TestData_QA.xlsx";

		  String data;
		    ArrayList<String> strList=new ArrayList<>();
		    
		    
		        InputStream is = new FileInputStream(filepath);
		        Workbook wb = WorkbookFactory.create(is);
		        Sheet sheet = wb.getSheetAt(0);
		        
		        Iterator<Row> rowIterator = sheet.iterator(); // Traversing over each row of XLSX file
		        Row r = (Row)rowIterator.next();
		
		        while (rowIterator.hasNext()) {
		            Row row = rowIterator.next(); // For each row, iterate through each columns
		            
		            Iterator<Cell> cellIterator = row.cellIterator();
		            int lastrow=sheet.getLastRowNum();
		           
		            while (cellIterator.hasNext()) {
		                Cell cell = cellIterator.next();	       
		                if(cell.getColumnIndex()==2)//for example of c
		                {
		                	DataFormatter df = new DataFormatter();
			                data = df.formatCellValue(cell);
			                int rownum = cell.getRowIndex();
			                	if(rownum>=1){
			                		Cell cellvalue1=sheet.getRow(rownum).getCell(0);
			                		Cell cellvalue2=sheet.getRow(rownum).getCell(1);
			                		String asItLooksInExcel1 = df.formatCellValue(cellvalue2);
			                		String asItLooksInExcel = df.formatCellValue(cellvalue1);
			                
			                		//System.out.println("Data: "+ asItLooksInExcel);
			                		if(asItLooksInExcel1.equals("Y")){
			                		strList.add(asItLooksInExcel);
			                		}
		                	           
			                	}
		                }
		            }
		            
		        }
		        
		   	       
		        System.out.println("arr : " + strList);	
		        int findindex =strList.indexOf(tcid);
		        System.out.println("findindex : " + findindex);	
		    return findindex;
		    
			}

}
