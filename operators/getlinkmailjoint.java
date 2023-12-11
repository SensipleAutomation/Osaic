package operators;

import java.io.IOException;
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

import org.openqa.selenium.WebDriver;

import com.codoid.products.exception.FilloException;

import datadriven.custdata;
import datadriven.data;



public class getlinkmailjoint {
public static String getmaillink(String acc_num) throws MessagingException, IOException, FilloException {
		
	
	
	
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
	
	/*String temp1 = null;
	String host = "pop3-mail.outlook.com";
    String mailStoreType = "imap";
    String username = "agautomationqa@advisorgroup.onmicrosoft.com";
    String password = "Cad10251";
    
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
         Folder emailFolder = store.getFolder("INBOX");
         emailFolder.open(Folder.READ_ONLY);
         String emailSubject ="Time To Finalize Your Account";
         //System.out.println(setenvlink());
         SearchTerm sender = new SubjectTerm(setenvlink());
         // retrieve the messages from the folder in an array and print it
         Message[] messages = emailFolder.search(sender);
         System.out.println("messages.length---" + messages.length);
         
            int n=messages.length;
            //int limit=n-10;
            OUTER_LOOP:
            for (int i = n-1; i>n-100; i--) {
            Message message = messages[i];
            //System.out.println(messages[i]);
            ArrayList<String> links = new ArrayList<String>();
            String sentence  = message.getContent().toString();
            String search= acc_num;
            //if(message.getSubject().contains("Time To Finalize Your Account [Email Originally From: testmailid@test.com;]")){
            	if(sentence.toLowerCase().indexOf(search.toLowerCase()) != -1){	
            	String desc=message.getContent().toString();

       //  System.out.println(desc);
          
          /*<a id="emailpreviewlink" href="https://qa.wfsequipt.com/Registration/Activate?token=FkzoUMyCazvDM2XlKBRdxPfDATHqkuAQAFFCVqb1pBU%2BGESQAO662P9VMH9hu6obwvKmc4eAVCXCB%2BSv4%2FbTt4Uq%2B3%2BOegeTmFj8qKu9NYN51Gzfg%2FxqcAfT%2Bl6UKiOY9q6LjeDk4LqFMFnXAVizICe3j8rmk7w5GZnnbZGegWRJZ%2BuMb3lRf23t6bwcROew&amp;TransactionId=f4033d17-f05c-4474-acda-6366e3f435a8&amp;SignerNumber=">E-Sign Your Documents</a>
          */
          
          /*"Please <a class="h5" href="https://newstaging.mobilous.com/en/user-register/******" target="_blank">click here</a> to complete your registration".
          */
         	Pattern linkPattern = Pattern.compile("<a\\b[^>]*href=\"([^\"]*)[^>]*>(.*?)</a>",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
          Pattern linkPattern1 = Pattern.compile("Dear Pasha,",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
         
          //System.out.println(linkPattern);
          Matcher pageMatcher = linkPattern.matcher(desc);
          Matcher pageMatcher1 = linkPattern1.matcher(desc);
          while(pageMatcher.find()&&pageMatcher1.find()){
              links.add(pageMatcher.group(1));
              
              temp1=links.get(0);
              System.out.println("Email:"+ links + "wanted");
             break OUTER_LOOP ;
          } 
          
         
         // System.out.println("Email:"+ links + "wanted");
         /* Pattern linkPattern = Pattern.compile(" <a\\b[^>]*href=\"[^>]*>(.*?)</a>",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
           Matcher pageMatcher = linkPattern.matcher(desc);

           while(pageMatcher.find()){
               links.add(pageMatcher.group());
           } */
            }/*else{
            System.out.println("Email:"+ i + " is not a wanted email");
            }*/
            	// System.out.println(links);
           /* for(String temp:links){
            if(temp.contains("Registration")){
                System.out.println(temp);
                String temp2= temp;
                temp1=temp;
            }
            } */ 
	}
    //close the store and folder objects
    emailFolder.close(false);
    store.close();
    
	return temp1;
    
		
	}
  
		
	public static void main(String[] args) throws MessagingException, IOException, FilloException {
        // TODO Auto-generated method stub
		
	//	String fval=data.getTestValue("emailpreview_addmessage", "Select * from Sheet1 where TCID=1");
		String link = getmaillink("D4W013899");
		System.out.println(link);
	}
	
	public static void getlink(WebDriver driver,String fieldName,String qry) throws MessagingException, IOException, FilloException{
		String fval=data.getTestValue(fieldName, qry);
		String link = getmaillink(fval);
		//driver.navigate().to(link);
		driver.get(link);
	}
	public static void esign_getlink(WebDriver driver,String fieldName,String qry) throws MessagingException, IOException, FilloException{
		String fval=custdata.getTestValue(fieldName, qry);
		String link = getmaillink(fval);
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
