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

public class getlinkmailcustomerflow {

	
public static String getmaillink(String SRID,String mailsub) throws MessagingException, IOException, FilloException {
		
	String temp1 = null;
	String host = "outlook.office365.com";
    String username = "agautomationqa@advisorgroup.onmicrosoft.com";
    String password = "Cad10254";
    
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
         System.out.println("Let's Start Opening Your Account [Email Originally From:");
         SearchTerm sender = new SubjectTerm(mailsub);
         // retrieve the messages from the folder in an array and print it
         Message[] messages = emailFolder.search(sender);
         //System.out.println("messages.length---" + messages.length);
         
            int n=messages.length;
            //int limit=n-10;
            OUTER_LOOP:
            for (int i = n-1; i>n-500; i--) {
            Message message = messages[i];
            //System.out.println("messages_ i---" + messages[i]);
            ArrayList<String> links = new ArrayList<String>();
            String sentence  = message.getContent().toString();
			////System.out.println(sentence);
            String search= SRID;
            //if(message.getSubject().contains("Time To Finalize Your Account [Email Originally From: testmailid@test.com;]")){
            	if(sentence.toLowerCase().indexOf(search.toLowerCase()) != -1){	
            	String desc=message.getContent().toString();

         System.out.println(desc);
          
         
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
          
        
            	
           
	}
            System.out.println("beforeemailfolderclose: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
    emailFolder.close(false);
    store.close();
    System.out.println("afteremailfolderclose: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
	return temp1;
    
		
	}
  
		
	/*public static void main(String[] args) throws MessagingException, IOException, FilloException {
        // TODO Auto-generated method stub
		
		String fval="122054";
		String emailaddress="arun.vincent@advisorgroup.com";
		String link = getmaillink(fval,emailaddress);
		 System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		System.out.println(link);
	}*/
	
	public static void getlink_invitecustomer(WebDriver driver,String fval) throws MessagingException, IOException, FilloException{
		//String fval=data.getTestValue(fieldName, qry);
		
		String mailsubQA="Let's Start Opening Your Account [Email Originally From:";
		String mailsubPROD="Let's Start Opening Your Account";
		
		String link = null;
		String env = System.getProperty("env");
		if (env.equals("PROD")){
		link=getmaillink(fval,mailsubPROD);
		}else{
			link=getmaillink(fval,mailsubQA);
		}
		System.out.println("getlink: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		((JavascriptExecutor)driver).executeScript("window.open()");
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(2));
		//driver.navigate().to(link);
		driver.get(link);
	}
	public static void getlink_sendgoals(WebDriver driver,String fval) throws MessagingException, IOException, FilloException{
		//String fval=data.getTestValue(fieldName, qry);
		 String mailsubQA="Review Your Financial Profile and Goals [Email Originally From:";
		String mailsubPROD="Review Your Financial Profile and Goals";
		
		String link = null;
		String env = System.getProperty("env");
		if (env.equals("PROD")){
		link=getmaillink(fval,mailsubPROD);
		}else{
			link=getmaillink(fval,mailsubQA);
		}
		System.out.println("getlink: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		((JavascriptExecutor)driver).executeScript("window.open()");
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(2));
		//driver.navigate().to(link);
		driver.get(link);
	}
	public static void getlink_sendrecommendations(WebDriver driver,String fval) throws MessagingException, IOException, FilloException{
		String mailsubQA="Review Your Custom Proposal [Email Originally From:";
		String mailsubPROD="Review Your Custom Proposal";
		
		String link = null;
		String env = System.getProperty("env");
		if (env.equals("PROD")){
		link=getmaillink(fval,mailsubPROD);
		}else{
			link=getmaillink(fval,mailsubQA);
		}
		System.out.println("getlink: "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
		((JavascriptExecutor)driver).executeScript("window.open()");
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(2));
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



