package datadriven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebElement;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class custdata {
	public static HashMap<String, String> hmapcust = new HashMap<String, String>();
	public static String testdatapath = System.getProperty("user.dir") + "\\Result\\resultsheet.xlsx";
	
	public synchronized static void createhashmap(String tcid) throws FilloException{
		 synchronized(custdata.class){ 	    	
	        String testval=null;
	        Fillo fillo=new Fillo();	       
	        Connection connection=fillo.getConnection(testdatapath);
	        String qry="Select * from Sheet1 where TCID='"+tcid+"'";
	        String sqry=qry;  
	        Recordset recordset=connection.executeQuery(sqry);
	       // System.out.println("/------------------------------- Start time = "+java.time.LocalTime.now()+"/-------------------------------/");			
	       // System.out.println("recordset: "+ recordset.getCount());	       
	        while(recordset.next()){
	            ArrayList<String> dataColl=recordset.getFieldNames();
	            Iterator<String> dataIterator=dataColl.iterator();
	            while(dataIterator.hasNext()){
	                for (int i=0;i<=dataColl.size()-1;i++){
	                    String data=dataIterator.next();
	                    String dataVal=recordset.getField(data);
	                    String datakey=data+"_"+tcid;
	                    hmapcust.put(datakey, dataVal);	                   
	                }
	                break;
	            }
	            }
	            recordset.close();
	            connection.close();	             
	        }
	 }
	 
	public  static String getTestValue(String fieldName, String qry) throws FilloException{
			
	       // String testString=xlTesting(fieldName,qry);
			String testfield= fieldName+"_"+qry;
	        String testString =custdata.hmapcust.get(testfield);
	        return testString;			
	}   
	
	public static void elementclick(WebElement element) throws InterruptedException
    {
        Thread.sleep(500);
        element.click();
        System.out.println("Element got clicked.");
    }
		
	public static void inText(WebElement driver, String fieldName, String  qry) throws FilloException{
		String fval=getTestValue(fieldName, qry);            
		driver.sendKeys(fval);
		System.out.println(fieldName +" : "+ fval);                       	
    }

	public static synchronized void updateexcel(String ucolumn, String data, String qry) throws FilloException{
		synchronized(custdata.class){	
			Fillo updateresult=new Fillo();
			Connection connection = updateresult.getConnection(testdatapath);	
			String strQuery="Update Sheet1 Set "+ucolumn+"='"+data+"'where TCID='"+qry+"'";	 
			connection.executeUpdate(strQuery);	 
			connection.close();
		}
	}
}




