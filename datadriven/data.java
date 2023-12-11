package datadriven;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import operators.kuidropdown;
import operators.opera;
import operators.kuiradiobutton;

import com.github.javafaker.Faker;

public class data {
	public static Faker getData = new Faker();
	public static String ssn = null;
	public static String env = getParameter("env");
	//public static String env = getdevopsEnv("RELEASE_ENVIRONMENTNAME");
	//public static String TestDataFile = getParameter("testdatafilepath");
	//public static String testdatapath = System.getProperty("user.dir") + "\\resources\\"+TestDataFile+".xlsx";
	
	//static WebDriver driver;
	
	public static String getTestValue(String fieldName, String qry) throws FilloException{
      //  String testString=xlTesting(fieldName,qry);
		String testfield= fieldName+"_"+qry;
		System.out.println("testfield :"+testfield);
        String testString =hashdatafromexcel.hmap.get(testfield);
        return testString;
    }  

	public static boolean checkTestValueifContains(String fieldName, String qry, String text) throws FilloException{
	      //  String testString=xlTesting(fieldName,qry);
			boolean flag = false;
			String testfield= fieldName+"_"+qry;
			System.out.println("testfield :"+testfield);
	        String testString =hashdatafromexcel.hmap.get(testfield);
	        
	        String[] namesList = testString.split(",");
	        
	        for(String name : namesList)
	        {
	        	if(name.equalsIgnoreCase(text))
	        		flag = true;
	        
	        }
	        return flag;
	    } 
	
	public static String getTestValue1(String fieldName, String qry, String sheetName) throws FilloException{
	      //  String testString=xlTesting(fieldName,qry);
			String testfield= fieldName+"_"+qry;
			System.out.println("testfield :"+testfield);
	        //String testString =hashdatafromexcel.hmap.get(testfield);
	        String testString =hashdatafromexcel.hmap.get(testfield);
	        return testString;
	    }  
	
    public static String xlTesting(String fieldName, String qry) throws FilloException{
        String testval=null;
        Fillo fillo=new Fillo();

        Connection connection=fillo.getConnection(env);
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
    
    /*public static void selectDropDown(WebElement element ,String fieldName ,String qry  ) throws FilloException {
		element.click();
		List<WebElement> ddnValues  = element.findElements(By.xpath("//div[@class='ng-select-container ng-has-value']"));
		for(int i=0;i<ddnValues.size();i++) {
    		String links=ddnValues.get(i).getText();
    		String ddn=data.getTestValue(fieldName, qry);
    		 		if(links.equalsIgnoreCase(ddn)) {
    			System.out.println(links);
    		ddnValues.get(i).click();;
    		break;
    		}
		}  
		}*/

        public static void inText(WebElement driver, String fieldName, String  qry) throws FilloException{
        	//String fval=getTestValue1(fieldName, qry,); 
        	String fval=getTestValue(fieldName, qry);
        	driver.click();
            driver.clear();
            driver.sendKeys(Keys.CONTROL,Keys.HOME);
            driver.sendKeys(fval);
            System.out.println(fieldName +" : "+ fval);
            
            /* if(fieldName.equalsIgnoreCase("ipci_FirstName") 
            	 
            		|| fieldName.equalsIgnoreCase("ipci_LastName") 
            		|| fieldName.equalsIgnoreCase("aoaown_taxid1")
            		||	fieldName.equalsIgnoreCase("aoaown_dob")
            		|| fieldName.equalsIgnoreCase("ipci_zip")
            		||fieldName.equalsIgnoreCase("aoaown_fname")
            		||fieldName.equalsIgnoreCase("aoaown_lname")
					||fieldName.equalsIgnoreCase("ipci_email")
            		||fieldName.equalsIgnoreCase("aoaown_taxid2")){
            	 
            	
            	resultupdate excelresult = new resultupdate();
            	excelresult.updateexcel(fieldName, fval, qry);
            	
            }*/
            //System.out.println(fval);
        }
        
        
        public static void inTextOtherDetails(WebElement element, String  value) throws FilloException{
        	
        	element.click();
        	element.sendKeys(value);
        	
        }
        public static void buttonSelect(WebElement element) throws FilloException{
            
        	element.click();
        }
        
        public static void elementclick(WebElement element) throws InterruptedException
        {
            Thread.sleep(500);
            element.click();
            System.out.println("Element got clicked.");
        }
        
        public static void outText(String fieldName,String value, String qry) throws FilloException{
        	
        	Fillo updateresult=new Fillo();
        	Connection connection = updateresult.getConnection(env);
        	
        	String[] parts = qry.split("where");
        	String part2 = parts[1];
        	
        	
        	//System.out.println(part2);
        	Object qry1 = Integer.parseInt(part2.replaceAll("[\\D]", ""));
        	//System.out.println(qry1);
        	
        	String strQuery="Update Sheet1 Set "+fieldName+"='"+value+"'where TCID='"+qry1+"'";
        	
        	//String strQuery=uqry;
        	 
        	connection.executeUpdate(strQuery);
        	 
        	connection.close();
        	
        	
        	}
        
        
        public static void radioSelect(List<WebElement> objradio, String fieldName, String  qry) throws FilloException, InterruptedException{
            String fval=getTestValue(fieldName, qry);
            Thread.sleep(100);
            
        
    		int count = objradio.size();
    		System.out.println(fieldName+"count:" +count);
    		for (int i = 0; i < count; i++) {
    			//System.out.println(i);
    			// Store the checkbox name to the string variable, using 'Value'
    			// attribute
    			String sValue = objradio.get(i).getText();
    			//System.out.println(sValue);
    			// Select the checkbox if its value is the same that you want.
    			if (((String) sValue).equalsIgnoreCase(fval)) {
    				
    				//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+objradio.get(i).getLocation().x+")");
    				 
    				objradio.get(i).click();
    				System.out.println(fieldName +" : "+ sValue);
    				// This statement will get you out of the for loop.
    				break;
    			}
    		}
            
    		}
    		
    		public static boolean checkIfRecordExistInTable(List<WebElement> tableRows, String name) throws FilloException, InterruptedException {
                boolean flag = false;
    			
                Thread.sleep(100);
                
            
        		int count = tableRows.size();
        		System.out.println(name+"count:" +count);
        		for (int i = 0; i < count; i++) {
        			//System.out.println(i);
        			// Store the checkbox name to the string variable, using 'Value'
        			// attribute
        			String sValue = tableRows.get(i).getText();
        			//System.out.println(sValue);
        			// Select the checkbox if its value is the same that you want.
        			if (((String) sValue).equalsIgnoreCase(name)) {
        				
        				//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+objradio.get(i).getLocation().x+")");
        				System.out.println(name +" : "+ sValue);
        				flag = true;
        				
        				
        				
        			}
                
        		}
        		
        		//if(flag ==1)
        			
				return flag;
           
        }
        
        public static void checkBoxSelect(List<WebElement> objradio, String fieldName, String  qry) throws FilloException, InterruptedException{
            String fval=getTestValue(fieldName, qry);
            Thread.sleep(100);
            
        
    		int count = objradio.size();
    		//System.out.println(fieldName+"count:" +count);
    		for (int i = 0; i < count; i++) {
    			//System.out.println(i);
    			// Store the checkbox name to the string variable, using 'Value'
    			// attribute
    			String sValue = objradio.get(i).getText();
    			//System.out.println(sValue);
    			// Select the checkbox if its value is the same that you want.
    			if (((String) sValue).equalsIgnoreCase(fval)) {
    				
    				//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+objradio.get(i).getLocation().x+")");
    				 
    				objradio.get(i).click();
    				System.out.println(fieldName +" : "+ fval);
    				// This statement will get you out of the for loop.
    				break;
    			}
            
    		}
           
        }
        
                
        
        public static void checkBoxMultiSelect(List<WebElement> objradio, String fieldName, String  qry) throws FilloException, InterruptedException{
            String fval=getTestValue(fieldName, qry);
            String[] namesList = fval.split(",");
            Thread.sleep(100);
            
        
    		int count = objradio.size();
    		for (String name : namesList )
    		{
    		for (int i = 0; i < count; i++) {
    			//System.out.println(i);
    			// Store the checkbox name to the string variable, using 'Value'
    			// attribute
    			String sValue = objradio.get(i).getText();
    			//System.out.println(sValue);
    			// Select the checkbox if its value is the same that you want.
    			if (((String) sValue).equalsIgnoreCase(name)) {
    				
    				//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+objradio.get(i).getLocation().x+")");
    				 
    				objradio.get(i).click();
    				System.out.println(fieldName +" : "+ fval);
    				// This statement will get you out of the for loop.
    				break;
    			}
            
    			}
    		}
           
        }
        public static void radioselecthardcodedvalue(List<WebElement> objradio, String fvalue) throws FilloException, InterruptedException{
            String fval=fvalue;
            Thread.sleep(100);
                    
    		int count = objradio.size();    		
    		for (int i = 0; i < count; i++) {    			
    			String sValue = objradio.get(i).getText();   			
    			if (((String) sValue).equalsIgnoreCase(fval)) {
    				objradio.get(i).click();
    				break;
    			}
            
    		}
           
        }
        public static void dropDownSelect(WebDriver driver, WebElement dropelement, String fieldName, String  qry) throws FilloException, InterruptedException {

    		
    		//System.out.println(xpathfordrp);
    		dropelement.click();
    		//dropelement.getClass()
    		Thread.sleep(2000);
    		//String xpathfordrpitem="//p-dropdownitem/li[@aria-label='"+data.getTestValue(fieldName, qry)+"']";
    		String xpathfordrpitem="//p-dropdownitem/li[contains(@aria-label,'"+data.getTestValue(fieldName, qry)+"')]";
    		System.out.println(xpathfordrpitem);
    	//	driver.findElement(By.xpath(xpathfordrpitem)).click();
    		JavascriptExecutor js= (JavascriptExecutor)driver;
    		js.executeScript("arguments[0].click()",driver.findElement(By.xpath(xpathfordrpitem)));
        }
        
        public static void dropDownSelect1(WebDriver driver, WebElement dropelement, String fieldName, String  qry) throws FilloException, InterruptedException {

    		
    		//System.out.println(xpathfordrp);
    		dropelement.click();
    		//dropelement.getClass()
    		Thread.sleep(2000);
    		//String xpathfordrpitem="//p-dropdownitem/li[@aria-label='"+data.getTestValue(fieldName, qry)+"']";
    		String xpathfordrpitem="//p-dropdownitem/li[contains(@aria-label,"+"\""+data.getTestValue(fieldName, qry)+"\""+")]"; 
    		System.out.println(xpathfordrpitem);
    	//	driver.findElement(By.xpath(xpathfordrpitem)).click();
    		JavascriptExecutor js= (JavascriptExecutor)driver;
    		js.executeScript("arguments[0].click()",driver.findElement(By.xpath(xpathfordrpitem)));
        }
        
//        public static void dropDownSelect(WebDriver driver, WebElement dropelement, String fieldName, String  qry) throws FilloException, InterruptedException {
//
//    		String classn=dropelement.getAttribute("class");
//    		String[] definedclass=classn.split("ng-pristine");
//    		String needed1=definedclass[0];
//    		String needed=needed1.substring(0,needed1.length()-1);		
//    		String xpathfordrp="//div[@role='button'][contains(@class,'"+needed+"')]";
//    		System.out.println(xpathfordrp);
//    		driver.findElement(By.xpath(xpathfordrp)).click();
//    		String xpathfordrpitem="//p-dropdownitem[contains(@class,'"+needed+"')]/li[@aria-label='"+data.getTestValue(fieldName, qry)+"']";
//    		driver.findElement(By.xpath(xpathfordrpitem)).click();
//        }
        
        public static void dropDownSelect2(WebDriver driver, WebElement dropelement, String fieldName, String  qry) throws FilloException, InterruptedException {

    		String classn=dropelement.getAttribute("class");
    		String[] definedclass=classn.split("ng-pristine");
    		String needed1=definedclass[0];
    		String needed=needed1.substring(0,needed1.length()-23);		
    		String xpathfordrp="//div[@role='button'][contains(@class,'"+needed+"')]";
    		System.out.println(xpathfordrp);
    		driver.findElement(By.xpath(xpathfordrp)).click();
    		String xpathfordrpitem="//p-dropdownitem[contains(@class,'"+needed+"')]/li[@aria-label='"+data.getTestValue(fieldName, qry)+"']";
    		JavascriptExecutor executor = (JavascriptExecutor)driver;
    		executor.executeScript("arguments[0].click();", xpathfordrpitem);
    		//driver.findElement(By.xpath(xpathfordrpitem)).click();
        }
        
        public static void kuidrpselect(WebDriver driver, String dropelement, String fieldName, String  qry) throws FilloException, InterruptedException{
            String fval=getTestValue(fieldName, qry);
          
           //String drp="driver.findElement(By.cssSelector(dropelement).cssSelector(disabled)";
            kuidropdown dropdown = new kuidropdown(driver
    				.findElement(By.cssSelector(dropelement)));	
           
    		dropdown.selectItem(fval); 
            //driver.sendKeys(fval);
            System.out.println(fieldName +" : "+ fval);
            //System.out.println(fval);
        }

        public static void kuidrpselecthardcodevalue(WebDriver driver, String dropelement, String fval) throws FilloException, InterruptedException{
            //String fval=getTestValue(fieldName, qry);
          
           //String drp="driver.findElement(By.cssSelector(dropelement).cssSelector(disabled)";
            kuidropdown dropdown = new kuidropdown(driver
    				.findElement(By.cssSelector(dropelement)));	
           
    		dropdown.selectItem(fval); 
            //driver.sendKeys(fval);
           // System.out.println(fieldName +" : "+ fval);
            //System.out.println(fval);
        }
        
        
        public static void chkboxSelect(List<WebElement> objradio, String fieldName, String  qry) throws FilloException, InterruptedException{
            String fval=getTestValue(fieldName, qry);
            Thread.sleep(100);
    	
    		int count = objradio.size();
    		System.out.println(fieldName+"count:" +count);
    		for (int i = 0; i < count; i++) {
    			////System.out.println(i);
    			// Store the checkbox name to the string variable, using 'Value'
    			// attribute
    			String sValue = objradio.get(i).getCssValue("value");
    			//System.out.println(sValue);
    			// Select the checkbox if its value is the same that you want.
    			if (((String) sValue).equalsIgnoreCase(fval)) {
    				
    				//((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+o_aoaownkyc.getLocation().x+")");
    				 
    				objradio.get(i).click();
    				System.out.println(fieldName +" : "+ fval);
    				// This statement will get you out of the for loop.
    				break;
    		}
            
    		}
           
        }
        
        public static void radiotick(WebDriver driver, List<WebElement> objradio, String fieldName, String  qry) throws FilloException, InterruptedException{
            String fval=getTestValue(fieldName, qry);
            Thread.sleep(100);
            
            
           /* kuiradiobutton krb = new kuiradiobutton();
            
            krb.kuiradiobuttonset(objradio, fval);
            System.out.println(fieldName +" : "+ fval);*/
    	
    		int count = objradio.size();
    		System.out.println(fieldName+"count:" +count);
    		for (int i = 0; i < count; i++) {
    			//System.out.println(i);
    			// Store the checkbox name to the string variable, using 'Value'
    			// attribute
    			String sValue = objradio.get(i).getText();
    			//System.out.println(sValue);
    			// Select the checkbox if its value is the same that you want.
    			if (((String) sValue).equalsIgnoreCase(fval)) {
    				
    				
    				try{
    					objradio.get(i).click(); 
    				}
    				catch (Exception e)
    				{
    				
    				/*JavascriptExecutor jse=(JavascriptExecutor)driver;
    				jse.executeScript("scroll(0,"+objradio.get(i).getLocation()+")");
    				
    				Point loc = objradio.get(i).getLocation();    
    				loc.x = objradio.get(i).getLocation().getX() -1000;
    				loc.y = objradio.get(i).getLocation().getY() -1000;
    				jse.executeScript("scroll(0,"+loc+")");
    				Thread.sleep(1000);
    				objradio.get(i).click(); */
    				Point loc = objradio.get(i).getLocation(); 
    				loc.y = objradio.get(i).getLocation().getY() -150;
    				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+loc+")");
    				 //("window.scrollTo(0," + (location.y - 70)+ ")")
    				objradio.get(i).click();
    				System.out.println(fieldName +" : "+ fval);
    				// This statement will get you out of the for loop.
    				break;
    		}
            
    		}
           
        }}
        
        private static String getParameter(String name) {
  	      String value = System.getProperty(name);

  	      if (value == null)
  	          throw new RuntimeException(name + " is not a parameter!");
  	      if (value.isEmpty())
  	          throw new RuntimeException(name + " is empty!");

  	      return value;
  	  }
        private static String getdevopsEnv(String name) {
  		  
    		
  	      //String value = System.getenv(name);
  	    String value = System.getProperty(name);
 System.out.println("value :"+value);
  	      if (value == null)
  	          throw new RuntimeException(name + " is not a devops parameter!");
  	      if (value.isEmpty())
  	          throw new RuntimeException(name + " is empty!");

  	      return value;
  	  }
        
        
        public static String getSSN()
        {
        	ssn = getData.number().digits(9);
        	return ssn;
        	
        }
        
        public static String getDOB() {
        	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        	 String dob = sdf.format(getData.date().birthday(20, 60));
           return dob;
        }
        
        public static String getPastDate() {
        	Date date = new Date();
        	Calendar cal = Calendar.getInstance();
        	Calendar calendar = Calendar.getInstance();
        	calendar.add(Calendar.DAY_OF_YEAR, -10);
        	Date pastDateTime = calendar.getTime();
        	
        	
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        	String formattedDate = sdf.format(pastDateTime);
        	 return  formattedDate.toString();
        }
        
        public static String getFutureDate() {
        	Date date = new Date();
        	Calendar cal = Calendar.getInstance();
        	Calendar calendar = Calendar.getInstance();
        	calendar.add(Calendar.DAY_OF_YEAR, 10);
        	Date pastDateTime = calendar.getTime();
        	
        	
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        	String formattedDate = sdf.format(pastDateTime);
        	 return  formattedDate.toString();
        }
        
        public static String getFirstName() 
        {
        	String firstName = getData.name().firstName();
        	return firstName;
        	
        }
        
        public static String getLastName() 
        {
        	String lastName = getData.name().lastName();
        	return lastName;
        	
        }
        
        public static String getMiddleName() 
        {
        	String middleName = getData.name().nameWithMiddle();
        	return middleName;
        	
        }
        
      
}
