package operators;
import microsoft.exchange.webservices.data.*;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.LogicalOperator;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class getlinkmailews {
	
	private static ExchangeService service;
    public static Integer NUMBER_EMAILS_FETCH = 5; // only latest 5 emails/appointments are fetched.
   
    public static Map readEmailItem(ItemId itemId) {
        Map messageData = new HashMap();
        try {
            Item itm = Item.bind(service, itemId, PropertySet.FirstClassProperties);
            EmailMessage emailMessage = EmailMessage.bind(service, itm.getId());
            messageData.put("emailItemId", emailMessage.getId().toString());
            messageData.put("subject", emailMessage.getSubject().toString());
            messageData.put("fromAddress", emailMessage.getFrom().getAddress().toString());
            messageData.put("senderName", emailMessage.getSender().getName().toString());
            Date dateTimeCreated = emailMessage.getDateTimeCreated();
            messageData.put("SendDate", dateTimeCreated.toString());
            Date dateTimeRecieved = emailMessage.getDateTimeReceived();
            messageData.put("RecievedDate", dateTimeRecieved.toString());
            messageData.put("Size", emailMessage.getSize() + "");
            messageData.put("emailBody", emailMessage.getBody().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageData;
    }
/**
 * Number of email we want to read is defined as NUMBER_EMAILS_FETCH, 
 */
    
    /**
     * Reading one appointment at a time. Using Appointment ID of the email.
     * Creating a message data map as a return value.
     * @throws Exception 
     */
    
    public static void main(String[] args) throws Exception {

		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		service.setUrl(new URI("https://outlook.office365.com"));
		String username = "agautomationqa@advisorgroup.onmicrosoft.com";
        String password = "Cad10252";
		ExchangeCredentials credentials = new WebCredentials(username, password);
				service.setCredentials(credentials);
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//Strinintg	readerTime = "1572414743000"; // Epoch Time
						//df lastTime = formatter.format(new Date(readerTime.toLong()))
						Date startDate = formatter.parse("2020-06-21 8:00:00");
				ItemView itemview = new ItemView(100);
						SearchFilter isGreaterThan = new SearchFilter.IsGreaterThan(ItemSchema.DateTimeReceived, startDate);
				FindItemsResults findResults = service.findItems(WellKnownFolderName.Inbox, new SearchFilter.SearchFilterCollection(LogicalOperator.And,  isGreaterThan ), itemview);
				// Number emails are found
				//println 
				
				System.out.println("No. of records: "+findResults.getTotalCount());
    }

}
