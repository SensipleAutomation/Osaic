package datadriven;

import java.util.ArrayList;
import java.util.Iterator;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class datahash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
public static String env = "QA";
	
	public static String testdatapath = System.getProperty("user.dir") + "\\resources\\TestData_"+env+".xlsx";
	
	//static WebDriver driver;
	
	public static String getTestValue(String fieldName, String qry) throws FilloException{
        String testString=xlTesting(fieldName,qry);
        return testString;
    }   
    public static String xlTesting(String fieldName, String qry) throws FilloException{
    	
    	
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
