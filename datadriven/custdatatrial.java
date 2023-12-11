package datadriven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class custdatatrial {

	public static HashMap<String, String> hmapcust = new HashMap<String, String>();
	public static void main(String[] args) throws FilloException {
		// TODO Auto-generated method stub
		String tcid="10";
		String qry="Select * from Sheet1 where TCID='"+tcid+"'";
		System.out.println("/------------------------------- Start time = "+java.time.LocalTime.now()+"/-------------------------------/");
	
		String wes=getTestValue("Username",qry);
		System.out.println(wes);
		
		/*System.out.println(System.getProperty("REFBD"));
		System.out.println(System.getProperty("recir_regtyp"));*/
		
		 Set set = hmapcust.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	      }
			//recir_regtyp
	}
	
	
public static String testdatapath = System.getProperty("user.dir") + "\\Result\\resultsheet.xlsx";
	
	//static WebDriver driver;
	
	public static String getTestValue(String fieldName, String qry) throws FilloException{
        String testString=xlTesting(fieldName,qry);
        return testString;
    }   
    public static String xlTesting(String fieldName, String qry) throws FilloException{
    	String[] parts = qry.split("where");
    	String part2 = parts[1];
    	
    	
    	System.out.println(part2);
    	Object qry1 = Integer.parseInt(part2.replaceAll("[\\D]", ""));
    	System.out.println(qry1);
    	
        String testval=null;
        Fillo fillo=new Fillo();
       // System.setProperty("ROW", "700");
        Connection connection=fillo.getConnection(testdatapath);
        
        //connection.createTable("Table Name",new String[]{"Column_1","Column_2"});
        String sqry=qry;
       // Recordset recordset=connection.createTable("1");
       
       
        Recordset recordset=connection.executeQuery(sqry);
       // recordset.where(strCondition)
        System.out.println("/------------------------------- Start time = "+java.time.LocalTime.now()+"/-------------------------------/");
		
        System.out.println("recordset: "+ recordset.getCount());
       
        while(recordset.next()){
            ArrayList<String> dataColl=recordset.getFieldNames();
            Iterator<String> dataIterator=dataColl.iterator();
            System.out.println("dataColl: "+dataColl);
            System.out.println("dataIterator: "+dataIterator);
            while(dataIterator.hasNext()){
                for (int i=0;i<=dataColl.size()-1;i++){
                    String data=dataIterator.next();
                    String dataVal=recordset.getField(data);
                    System.out.println(data);
                    System.out.println(dataVal);
                  //  String datakey=data+"_"+qry1;
                    hmapcust.put(data, dataVal);
                    //System.setProperty(data, dataVal);
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
    public static void createhashmap(String tcid) throws FilloException{
    	/*String[] parts = qry.split("where");
    	String part2 = parts[1];
    	
    	
    	System.out.println(part2);
    	Object qry1 = Integer.parseInt(part2.replaceAll("[\\D]", ""));
    	System.out.println(qry1);*/
    	
        String testval=null;
        Fillo fillo=new Fillo();
       // System.setProperty("ROW", "700");
        Connection connection=fillo.getConnection(testdatapath);
        
        //connection.createTable("Table Name",new String[]{"Column_1","Column_2"});
        String qry="Select * from Sheet1 where TCID='"+tcid+"'";
        String sqry=qry;
       // Recordset recordset=connection.createTable("1");
       
       
        Recordset recordset=connection.executeQuery(sqry);
       // recordset.where(strCondition)
        System.out.println("/------------------------------- Start time = "+java.time.LocalTime.now()+"/-------------------------------/");
		
        System.out.println("recordset: "+ recordset.getCount());
       
        while(recordset.next()){
            ArrayList<String> dataColl=recordset.getFieldNames();
            Iterator<String> dataIterator=dataColl.iterator();
           // System.out.println("dataColl: "+dataColl);
          //  System.out.println("dataIterator: "+dataIterator);
            while(dataIterator.hasNext()){
                for (int i=0;i<=dataColl.size()-1;i++){
                    String data=dataIterator.next();
                    String dataVal=recordset.getField(data);
                   // System.out.println(data);
                  //  System.out.println(dataVal);
                    String datakey=data+"_"+tcid;
                    hmapcust.put(datakey, dataVal);
                    //System.setProperty(data, dataVal);
                   
                }

                break;
            }
            }

            recordset.close();
            connection.close();
             
        }

}
