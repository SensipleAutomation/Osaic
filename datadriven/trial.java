package datadriven;

import org.apache.commons.exec.util.StringUtils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class trial {

	public static void main(String[] args) throws FilloException {
		resultupdate rs =new resultupdate();
		rs.resultupdatepass("3");
		
		String qry="Select * from Sheet1 where TCID='3'";
		String[] parts = qry.split("where");
		String part2 = parts[1];
		
		
		System.out.println(part2);
		Object qry1 = Integer.parseInt(part2.replaceAll("[\\D]", ""));
		System.out.println(qry1);
		
		String ucolumn = "ACC_NUM";
		String data = "dfgsdf345";
		
		String strQuery="Update Sheet1 Set "+ucolumn+"='"+data+"' where TCID='"+qry1+"'";
		System.out.println(strQuery);
	}

}
