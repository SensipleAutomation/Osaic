package datadriven;

import java.time.LocalDateTime;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
public class resultupdate {
	
public void resultupdatepass(String TC) throws FilloException{

	Fillo updateresult=new Fillo();
	Connection connection = updateresult.getConnection(System.getProperty("user.dir") + "\\Result\\resultsheet.xlsx");
	
	Object qry1 = Integer.parseInt(TC.replaceAll("[\\D]", ""));
	System.out.println(qry1);
	
	String strQuery="Update Sheet1 Set Result='PASS' where TCID='"+qry1+"'";
	 System.out.println(strQuery);
	connection.executeUpdate(strQuery);
	 
	connection.close();

}
public void resultupdatefail() throws FilloException{

	Fillo updateresult=new Fillo();
	Connection connection = updateresult.getConnection("C:\\Test.xlsx");
	
	
	String strQuery="Update Sheet1 Set Result='FAIL' where ID=100 and name='John'";
	 
	connection.executeUpdate(strQuery);
	 
	connection.close();


}
public static synchronized void updateexcel(String ucolumn, String data, String qry) throws FilloException{
	
		synchronized(resultupdate.class){
		
	Fillo updateresult=new Fillo();
	Connection connection = updateresult.getConnection(System.getProperty("user.dir") + "\\Result\\resultsheet.xlsx");
	
	
	
	String strQuery="Update Sheet1 Set "+ucolumn+"='"+data+"'where TCID='"+qry+"'";
	
	//String strQuery=uqry;
	 
	connection.executeUpdate(strQuery);
	 
	connection.close();
	
	}

}

/*public static synchronized boolean updateexcel(String ucolumn, String data, String qry) {
   // synchronized (DataBook.class) {
        try {
            System.out.println("Start Time:= " + Thread.currentThread().getId() + " :: " + LocalDateTime.now().toString());
            updateData( ucolumn, data, qry);
            System.out.println("End Time:= " + Thread.currentThread().getId() + " :: "  + LocalDateTime.now().toString());
            return true;

        } catch (FilloException flex) {
            flex.printStackTrace();
            return false;
        }
    }*/
//}
public static void updateData(String ucolumn, String data, String qry) throws FilloException {
	Fillo updateresult=new Fillo();
	Connection connection = updateresult.getConnection(System.getProperty("user.dir") + "\\Result\\resultsheet.xlsx");
	
	
	
	String strQuery="Update Sheet1 Set "+ucolumn+"='"+data+"'where TCID='"+qry+"'";
	
	//String strQuery=uqry;
	 
	connection.executeUpdate(strQuery);
	 
	connection.close();

    try {
        Thread.sleep(10000);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}

}