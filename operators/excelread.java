package operators;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import datadriven.custdatatrial;
import datadriven.datatrial;
import datadriven.resultupdate;

public class excelread {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String filepath = System.getProperty("user.dir") + "\\Result\\resultsheet.xlsx";

		int sizearr= excelReader(filepath).size();
		 System.out.println("arr : " + excelReader(filepath));
		/*for(int x = 0; x < sizearr; x++) {
			String exval = excelReader(filepath).get(x);
            System.out.println("col : " + exval);
        } */
	}
	
	private static ArrayList<String> excelReader(String filepath) throws EncryptedDocumentException, InvalidFormatException, IOException {
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
		                		String asItLooksInExcel = df.formatCellValue(cellvalue1);
		                
		                		//System.out.println("Data: "+ asItLooksInExcel);
		                		
		                		strList.add(asItLooksInExcel);
	                	           
		                	}
	                }
	            }
	            
	        }
	        
	    
	       
	    	
	    return strList;
		}
	
	public  static String getTestValue(String fieldName, String qry) throws FilloException{
		
       // String testString=xlTesting(fieldName,qry);
		String testfield= fieldName+"_"+qry;
        String testString =custdatatrial.hmapcust.get(testfield);
        return testString;
		
    }   
    public static String xlTesting(String fieldName, String qry) throws FilloException{
    	String testdatapath = System.getProperty("user.dir") + "\\Result\\resultsheet.xlsx";
    	String testval=null;
        Fillo fillo=new Fillo();

        Connection connection=fillo.getConnection(testdatapath);
        String sqry=qry;
        Recordset recordset=connection.executeQuery(sqry);

        while(recordset.next()){
            ArrayList<String> dataColl=recordset.getFieldNames();
            Iterator<String> dataIterator=dataColl.iterator();

            while(dataIterator.hasNext()){
                for (int i=0;i<=dataColl.size()-1;i++){
                    String data=dataIterator.next();
                    String dataVal=recordset.getField(data);
                    if (data.equalsIgnoreCase(fieldName)){
                        String testData=dataColl.get(i);   
                        String testValue= recordset.getField(testData);
                        testval=testValue;
                    }
                }

                break;
            }
            }

            recordset.close();
            connection.close();
            return testval; 
        }
}




/*Iterator rowIter = sheet.rowIterator();
Row r = (Row)rowIter.next();
//System.out.println("row "+r);
short lastCellNum = r.getLastCellNum();
System.out.println("lastCellNum: "+lastCellNum);
int[] dataCount = new int[lastCellNum];
int col = 1;
//rowIter = sheet.rowIterator();
// while(rowIter.hasNext()) {
	System.out.println("Move to next row: ");
   Iterator cellIter = ((Row)rowIter.next()).cellIterator();
    while(cellIter.hasNext()) {
        Cell cell = r.getCell(2);
        System.out.println("Move to next cell: ");
        col = cell.getColumnIndex();
       int rows=r.getRowNum();
        dataCount[col] = 1;
        DataFormatter df = new DataFormatter();
        data = df.formatCellValue(cell);
        System.out.println("Data: "+rows+":"+col+":"+ data);
   // }
}*/
/*is.close();
for(int x = 0; x < dataCount.length; x++) {
    System.out.println("col " + x + ": " + dataCount[x]);
}
}*/
