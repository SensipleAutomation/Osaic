package operators;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

import datadriven.custdata;
import datadriven.data;



public class getlinkmail {
	
	public static String getmaillink(String acc_num, String emailaddress) throws MessagingException, IOException, FilloException {
		
		String temp1 = null;
		String host = "outlook.office365.com";
	    String username = "agautomationqa@advisorgroup.onmicrosoft.com";
	    String password = "Cad10255";
	    
	    Properties properties = new Properties();
	    properties.put("mail.imap.starttls.enable", "true");
	    properties.setProperty("ssl.SocketFactory.provider", "operators.ExchangeSSLSocketFactory");
	    properties.setProperty("mail.imap.socketFactory.class", "operators.ExchangeSSLSocketFactory");

	    Session emailSession = Session.getDefaultInstance(properties,null);
	    Store store = emailSession.getStore("imap");
	    store.connect(host, username, password);
	

        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
         
         
        String emailSubject ="Time To Finalize Your Account";
        	//System.out.println(setenvlink());
        SearchTerm sender = new SubjectTerm(setenvlink());
         	//System.out.println("sender---" + sender);
         
  // retrieve the messages from the folder in an array and print it
        
        Message[] messages = emailFolder.search(sender);
        	// System.out.println("messages.length---" + messages.length);
         
            int n=messages.length;
            OUTER_LOOP:
            for (int i = n-1; i>n-500; i--) {
            Message message = messages[i];
           System.out.println("messages_ i---" +i+"----"+ messages[i]);
            ArrayList<String> links = new ArrayList<String>();
            String sentence  = message.getContent().toString();
            	//System.out.println(sentence);
            String search= acc_num;
            //if(message.getSubject().contains("Time To Finalize Your Account [Email Originally From: testmailid@test.com;]")){
            	if(sentence.toLowerCase().indexOf(search.toLowerCase()) != -1){	
            	String desc=message.getContent().toString();
            		//System.out.println(desc);         
            		//<a id="emailpreviewlink" href="https://qa.wfsequipt.com/Registration/Activate?token=FkzoUMyCazvDM2XlKBRdxPfDATHqkuAQAFFCVqb1pBU%2BGESQAO662P9VMH9hu6obwvKmc4eAVCXCB%2BSv4%2FbTt4Uq%2B3%2BOegeTmFj8qKu9NYN51Gzfg%2FxqcAfT%2Bl6UKiOY9q6LjeDk4LqFMFnXAVizICe3j8rmk7w5GZnnbZGegWRJZ%2BuMb3lRf23t6bwcROew&amp;TransactionId=f4033d17-f05c-4474-acda-6366e3f435a8&amp;SignerNumber=">E-Sign Your Documents</a>         
            		//"Please <a class="h5" href="https://newstaging.mobilous.com/en/user-register/******" target="_blank">click here</a> to complete your registration".
          
            		Pattern linkPattern = Pattern.compile("<a\\b[^>]*href=\"([^\"]*)[^>]*>(.*?)</a>",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
            		Matcher pageMatcher = linkPattern.matcher(desc);
            			while(pageMatcher.find()){
            					//System.out.println("Email1:"+ links + "wanted");
            				links.add(pageMatcher.group(1));
            				//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            					//System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
            					//System.out.println("Email:"+ links + "wanted");
            				int devilsiz = links.size();
            					//System.out.println("devil:"+ devilsiz + "wanted");
            				temp1=links.get(0);
            					System.out.println("temp1:"+ temp1 + "wanted");
            					break OUTER_LOOP ;
          } 
            	 }
          
        
            	
            
	}
    //close the store and folder objects
            //System.out.println("beforeemailfolderclose: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
    emailFolder.close(false);
    store.close();
    //System.out.println("afteremailfolderclose: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
	return temp1;
    
		
	}
  
		
	public static void main(String[] args) throws MessagingException, IOException, FilloException {
        // TODO Auto-generated method stub
		
		String fval="NBR259794";
		String emailaddress="arun.vincent@advisorgroup.com";
		String link = getmaillink(fval,emailaddress);
		 System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		System.out.println(link);
	}
	
	public static void getlink(WebDriver driver,String fieldName,String qry) throws MessagingException, IOException, FilloException{
		String fval=data.getTestValue(fieldName, qry);
		String tcid=data.getTestValue("TCID", qry);
    	
		String sqry="Select * from Sheet1 where TCID='"+tcid+"'";
		String emailaddress=custdata.getTestValue("ipci_email", sqry);
		String link = getmaillink(fval,emailaddress);
		System.out.println("getlink: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		
		//driver.navigate().to(link);
		driver.get(link);
	}
	public static void esign_getlink(WebDriver driver,String fieldName,String qry) throws MessagingException, IOException, FilloException{
		String fval=custdata.getTestValue(fieldName, qry);
		System.out.println("fval: "+fval);
		String emailaddress=custdata.getTestValue("ipci_email", qry);
		System.out.println("emailaddress: "+emailaddress);
		String link = getmaillink(fval,emailaddress);
		System.out.println("getlink: "+link);
		System.out.println("getlink: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		//driver.navigate().to(link);
		driver.get(link);
	}
	
	public static String setenvlink(){
		
		String env = System.getProperty("env");
		if (env.equals("PROD")){
			
	        return "Time To Finalize Your Account";
	    }
	    else
	    {
	        
	        return "Time To Finalize Your Account [Email Originally From:";
	    }
	}
		
	}

//////////////////old codes//////////////////////////////		
/*String temp1 = null;
String host = "outlook.office365.com";
//String host = "imap-mail.outlook.com";
String username = "agautomationqa@advisorgroup.onmicrosoft.com";
String password = "Cad10252";



String mailStoreType = "imaps";
//   String username = "arun.vincent@advisorgroup.com";

//   String password = "Welcome12";



Properties properties = new Properties();

// properties.put("mail.imap.host",host);
// properties.put("mail.imap.port", "993");
properties.put("mail.imap.starttls.enable", "true");
properties.setProperty("mail.imaps.auth.plain.disable", "true");
properties.setProperty("mail.imaps.auth.ntlm.disable", "true");
// properties.put("mail.imap.auth.mechanisms", "XOAUTH2");
// properties.put("mail.imap.fetchsize","300000");
// properties.put("mail.imap.auth.xoauth2.disable","false");

properties.put("mail.imap.sasl.enable", "true");
properties.put("mail.imap.sasl.mechanisms", "XOAUTH2");
properties.put("mail.imap.auth.login.disable", "true");
properties.put("mail.imap.auth.plain.disable", "true");
properties.setProperty("ssl.SocketFactory.provider", "operators.ExchangeSSLSocketFactory");
properties.setProperty("mail.imap.socketFactory.class", "operators.ExchangeSSLSocketFactory");

//// properties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
////properties.setProperty("mail.imap.socketFactory.fallback", "false");
//properties.setProperty("mail.imap.socketFactory.port",String.valueOf(993));


Session emailSession = Session.getInstance(properties,
   new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(
      		   username, password);
      }
   });


Session emailSession = Session.getDefaultInstance(properties,null);
// emailSession.setDebug(true);
//create the POP3 store object and connect with the pop server
// String uname ="arun.vincent@advisorgroup.com/agautomationqa";
//  String pass= "Welcome12";
Store store = emailSession.getStore("imap");
store.connect(host, username, password);*/





/*String temp1 = null;
//String host = "pop3-mail.outlook.com";
String host = "outlook.office365.com";
String mailStoreType = "imap";
String username = "agautomationqa@advisorgroup.onmicrosoft.com";
String password = "Cad10252";

Properties properties = new Properties();

properties.put("mail.pop3.host",host);
properties.put("mail.pop3.port", "995");
properties.put("mail.pop3.starttls.enable", "true");
properties.setProperty("mail.pop3.socketFactory.class","javax.net.ssl.SSLSocketFactory");
properties.setProperty("mail.pop3.socketFactory.fallback", "false");
properties.setProperty("mail.pop3.socketFactory.port",String.valueOf(995));
Session emailSession = Session.getDefaultInstance(properties);

//create the POP3 store object and connect with the pop server
Store store = emailSession.getStore("pop3");

store.connect(host, username, password);*/
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

/////////////////oldcodes//////////////////////////
