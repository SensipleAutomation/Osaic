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

public class hashdatafromexcel {
	
	public static HashMap<String, String> hmap = new HashMap<String, String>();
	public static void main(String[] args) throws FilloException {
		
		String tcid="10";
		String qry="Select * from Sheet1 where TCID='"+tcid+"'";
		
		String wes=getTestValue("Username",qry);
		System.out.println("Wes:"+wes);
		
		/*System.out.println(System.getProperty("REFBD"));
		System.out.println(System.getProperty("recir_regtyp"));*/
		
		 Set set = hmap.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	      }
			//recir_regtyp
	}
	//static String env = getdevopsEnv("RELEASE_ENVIRONMENTNAME");	
	static String env = System.getProperty("env");
	
	
	
//public static String testdatapath = System.getProperty("user.dir") + "\\resources\\TestData_"+env+".xlsx";
	
	//static WebDriver driver;
	
	public static String getTestValue(String fieldName, String qry) throws FilloException{
        String testString=xlTesting(fieldName,qry);
        return testString;
    }   
    public static String xlTesting(String fieldName, String qry) throws FilloException{
    	String testdatapath = System.getProperty("user.dir") + "\\resources\\TestData_"+env+".xlsx";
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
                    hmap.put(data, dataVal);
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
    public synchronized static void createhashmapfromexcel(String tcid,String filename,String sheetname) throws FilloException{
    	synchronized(hashdatafromexcel.class){
    	String testdatapath = System.getProperty("user.dir") + "\\resources\\"+filename+".xlsx";
        String testval=null;
        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(testdatapath);
        String qry="Select * from "+ sheetname +" where TCID='"+tcid+"'";
        String sqry=qry;      
        Recordset recordset=connection.executeQuery(sqry);
       // System.out.println("recordset: "+ recordset.getCount());
       
        while(recordset.next()){
            ArrayList<String> dataColl=recordset.getFieldNames();
            Iterator<String> dataIterator=dataColl.iterator();
            while(dataIterator.hasNext()){
                for (int i=0;i<=dataColl.size()-1;i++){
                    String data=dataIterator.next();
                    String dataVal=recordset.getField(data);
                   
                    String datakey=data+"_"+tcid;
                    //System.out.println(datakey+" : "+dataVal);
                    hmap.put(datakey, dataVal);   
                }
                break;
            }
            }

            recordset.close();
            connection.close();
    	}     
        }
    public synchronized static void createhashmapfromexcel(String tcid,String atcid,String filename,String Firstsheetname,String secondsheetname) throws FilloException{
    	synchronized(hashdatafromexcel.class){
    	String testdatapath = System.getProperty("user.dir") + "\\resources\\"+filename+".xlsx";
        String testval=null;
        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(testdatapath);
        System.out.println("/n");
        System.out.println("Accounnttcid:"+atcid);
        System.out.println("/n");
        System.out.println("cliendtid:"+tcid);
        if(atcid== null)
        {
        String qry="Select * from "+Firstsheetname+" where TCID='"+tcid+"'";
        System.out.println(Firstsheetname);
        System.out.println(tcid);
        Recordset recordset=connection.executeQuery(qry);
        System.out.println("recordset: "+ recordset.getCount());
        //System.out.println(recordset);
        while(recordset.next()){
            ArrayList<String> dataColl=recordset.getFieldNames();
            Iterator<String> dataIterator=dataColl.iterator();
            while(dataIterator.hasNext()){
                for (int i=0;i<=dataColl.size()-1;i++){
                    String data=dataIterator.next();
                    String dataVal=recordset.getField(data);
                    String datakey=data+"_"+tcid;
                //    System.out.println(datakey+" : "+dataVal);
                    hmap.put(datakey, dataVal);   
                }
                break;
            }
           
        }
        recordset.close();
        }
        else {
        	String qry="Select * from "+Firstsheetname+" where TCID='"+tcid+"'";
        	String comqry="Select * from "+secondsheetname+" where TCID='"+atcid+"'";
         
        	Recordset recordset=connection.executeQuery(qry);
            Recordset recordset2=connection.executeQuery(comqry);
        
        System.out.println("recordset: "+ recordset.getCount());
        System.out.println("recordset2: "+ recordset2.getCount());
        
      // System.out.println(recordset);
        while(recordset.next()){
            ArrayList<String> dataColl=recordset.getFieldNames();
            Iterator<String> dataIterator=dataColl.iterator();
            while(dataIterator.hasNext()){
                for (int i=0;i<=dataColl.size()-1;i++){
                    String data=dataIterator.next();
                    String dataVal=recordset.getField(data);
                   
                    String datakey=data+"_"+tcid;
                 //   System.out.println(datakey+" : "+dataVal);
                    hmap.put(datakey, dataVal);   
                }
                break;
            }
        }
            recordset.close();
            
            while(recordset2.next()){
                ArrayList<String> dataColl1=recordset2.getFieldNames();
                Iterator<String> dataIterator1=dataColl1.iterator();
                while(dataIterator1.hasNext()){
                    for (int i=0;i<=dataColl1.size()-1;i++){
                        String data=dataIterator1.next();
                        String dataVal=recordset2.getField(data);
                       
                        String datakey=data+"_"+atcid;
                        System.out.println(datakey+" : "+dataVal);
                        hmap.put(datakey, dataVal);   
                    }
                    break;
                }
            }

           
            recordset2.close();
        }
            connection.close();
    	}     
    	}
    private static String getParameter(String name) {
	      String value = System.getProperty(name);

	      if (value == null)
	          throw new RuntimeException(name + " is not a parameter!");
	      if (value.isEmpty())
	          throw new RuntimeException(name + " is empty!");

	      return value;
	  }
    private static String getdevopsEnv(String name) {
		  
		
	      String value = System.getenv(name);

	      if (value == null)
	          throw new RuntimeException(name + " is not a devops parameter!");
	      if (value.isEmpty())
	          throw new RuntimeException(name + " is empty!");

	      return value;
	  }

}
