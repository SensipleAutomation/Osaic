package operators;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.codoid.products.exception.FilloException;

import datadriven.custdata;
import datadriven.data;

public class getlinkmailinvitecustomer {
	
public static String getmaillink(String SRID) throws MessagingException, IOException, FilloException {
		
	String temp1 = null;
	String host = "outlook.office365.com";
    String username = "agautomationqa@advisorgroup.onmicrosoft.com";
    String password = "Cad10252";
    
    Properties properties = new Properties();
    properties.put("mail.imap.starttls.enable", "true");
    properties.setProperty("ssl.SocketFactory.provider", "operators.ExchangeSSLSocketFactory");
    properties.setProperty("mail.imap.socketFactory.class", "operators.ExchangeSSLSocketFactory");

    Session emailSession = Session.getDefaultInstance(properties,null);
    Store store = emailSession.getStore("imap");
    store.connect(host, username, password);
        //outlook.office365.com
        
        /*Properties properties = new Properties();

        properties.put("mail.imaps.host",host);
        properties.put("mail.imaps.port", "993");
        properties.put("mail.imaps.starttls.enable", "true");
        properties.setProperty("mail.imaps.socketFactory.class","javax.net.ssl.SSLSocketFactory");
          properties.setProperty("mail.imaps.socketFactory.fallback", "false");
          properties.setProperty("mail.imaps.socketFactory.port",String.valueOf(993));
        Session emailSession = Session.getDefaultInstance(properties);

        //create the POP3 store object and connect with the pop server
        Store store = emailSession.getStore("imap");*/


         /* properties.put("mail.imap.host",host);
          properties.put("mail.imap.port", "993");
          properties.put("mail.imap.starttls.enable", "true");
          //properties.put("mail.smtp.auth.plain.disable", "true");
          properties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
          properties.setProperty("mail.imap.socketFactory.fallback", "false");
          properties.setProperty("mail.imap.socketFactory.port",String.valueOf(993));
          Session emailSession = Session.getDefaultInstance(properties);

        //create the POP3 store object and connect with the pop server
         Store store = emailSession.getStore("imap")*/;

      //   store.connect(host, username, password);
         
         Folder emailFolder = store.getFolder("INBOX");
         emailFolder.open(Folder.READ_ONLY);
         
         
         String emailSubject ="Time To Finalize Your Account";
         System.out.println("Let's Start Opening Your Account [Email Originally From:");
         SearchTerm sender = new SubjectTerm(setenvlink());
         // retrieve the messages from the folder in an array and print it
         Message[] messages = emailFolder.search(sender);
         System.out.println("messages.length---" + messages.length);
         
            int n=messages.length;
            //int limit=n-10;
            OUTER_LOOP:
            for (int i = n-1; i>n-500; i--) {
            Message message = messages[i];
            System.out.println("messages_ i---" + messages[i]);
            ArrayList<String> links = new ArrayList<String>();
            String sentence  = message.getContent().toString();
			System.out.println(sentence);
            String search= SRID;
            //if(message.getSubject().contains("Time To Finalize Your Account [Email Originally From: testmailid@test.com;]")){
            	if(sentence.toLowerCase().indexOf(search.toLowerCase()) != -1){	
            	String desc=message.getContent().toString();

         System.out.println(desc);
          
          /*<a id="emailpreviewlink" href="https://qa.wfsequipt.com/Registration/Activate?token=FkzoUMyCazvDM2XlKBRdxPfDATHqkuAQAFFCVqb1pBU%2BGESQAO662P9VMH9hu6obwvKmc4eAVCXCB%2BSv4%2FbTt4Uq%2B3%2BOegeTmFj8qKu9NYN51Gzfg%2FxqcAfT%2Bl6UKiOY9q6LjeDk4LqFMFnXAVizICe3j8rmk7w5GZnnbZGegWRJZ%2BuMb3lRf23t6bwcROew&amp;TransactionId=f4033d17-f05c-4474-acda-6366e3f435a8&amp;SignerNumber=">E-Sign Your Documents</a>
          */
          
          /*"Please <a class="h5" href="https://newstaging.mobilous.com/en/user-register/******" target="_blank">click here</a> to complete your registration".
          */
          Pattern linkPattern = Pattern.compile("<a\\b[^>]*href=\"([^\"]*)[^>]*>(.*?)</a>",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
          Matcher pageMatcher = linkPattern.matcher(desc);
          while(pageMatcher.find()){
        	  System.out.println("Email1:"+ links + "wanted");
        	  links.add(pageMatcher.group(1));
              //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
              System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
              System.out.println("Email:"+ links + "wanted");
              int devilsiz = links.size();
              System.out.println("devil:"+ devilsiz + "wanted");
              temp1=links.get(0);
              System.out.println("temp1:"+ temp1 + "wanted");
              break OUTER_LOOP ;
          } 
            	 }
          
        
            	
           /* for(String temp:links){
            	 System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
            if(temp.contains("Registration")){
                System.out.println(temp);
                System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
                //String temp2= temp;
                temp1=temp;
            }
            }*/  
	}
    //close the store and folder objects
            System.out.println("beforeemailfolderclose: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
    emailFolder.close(false);
    store.close();
    System.out.println("afteremailfolderclose: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
	return temp1;
    
		
	}
  
		
	public static void main(String[] args) throws MessagingException, IOException, FilloException {
        // TODO Auto-generated method stub
		
		String fval="122054";
		String emailaddress="arun.vincent@advisorgroup.com";
		String link = getmaillink(fval);
		 System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		System.out.println(link);
	}
	
	public static void getlink(WebDriver driver,String fval) throws MessagingException, IOException, FilloException{
		//String fval=data.getTestValue(fieldName, qry);
		
		String link = getmaillink(fval);
		System.out.println("getlink: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		((JavascriptExecutor)driver).executeScript("window.open()");
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(2));
		//driver.navigate().to(link);
		driver.get(link);
	}
	public static void esign_getlink(WebDriver driver,String fieldName,String qry) throws MessagingException, IOException, FilloException{
		String fval=custdata.getTestValue(fieldName, qry);
		System.out.println("fval: "+fval);
		String emailaddress=custdata.getTestValue("ipci_email", qry);
		System.out.println("emailaddress: "+emailaddress);
		String link = getmaillink(fval);
		System.out.println("getlink: "+link);
		System.out.println("getlink: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		//driver.navigate().to(link);
		driver.get(link);
	}
	
	public static String setenvlink(){
		
		String env = System.getProperty("env");
		if (env.equals("PROD")){
			
	        return "Let's Start Opening Your Account";
	    }
	    else
	    {
	        
	        return "Let's Start Opening Your Account [Email Originally From:";
	    }
	}
		
	}


