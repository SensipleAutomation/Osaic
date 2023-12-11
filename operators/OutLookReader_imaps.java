package operators;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class OutLookReader_imaps {
	

    Folder inbox;

    // Constructor of the calss.

    public OutLookReader_imaps() {
        System.out.println("Inside MailReader()...");
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        // Set manual Properties
        props.setProperty("mail.imaps.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imaps.socketFactory.fallback", "false");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imaps.socketFactory.port", "993");
        props.put("mail.imaps.host", "outlook.office365.com");


        try {
            /* Create the session and get the store for read the mail. */

            Session session = Session.getDefaultInstance(System.getProperties(), null);
            Store store = session.getStore("imaps");

            store.connect("outlook.office365.com", 993, "agautomationqa@advisorgroup.onmicrosoft.com", "Cad10252");

            /* Mention the folder name which you want to read. */


            inbox = store.getFolder("INBOX");

            /* Open the inbox using store. */

            //inbox.open(Folder.READ_ONLY);
            inbox.open(Folder.READ_WRITE);

            Message messages[] = inbox.search(new FlagTerm(new Flags(
                    Flags.Flag.ANSWERED), false));
            //Message[] msgs = inbox.getMessages();

            System.out.println("No. of Unread Messages : " + inbox.getUnreadMessageCount());
            System.out.println("No. of Messages : " + inbox.getMessageCount());
            System.out.println("No. of Deleted Messages : " + inbox.getMode());

            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);

            inbox.fetch(messages, fp);

            try {

                printAllMessages(messages);

                inbox.close(true);
                store.close();

            } catch (Exception ex) {
                System.out.println("Exception arise at the time of read mail");
                ex.printStackTrace();
            }

        } catch (MessagingException e) {
            System.out.println("Exception while connecting to server: " + e.getLocalizedMessage());
            e.printStackTrace();
            System.exit(2);
        }

    }

    public void printAllMessages(Message[] msgs) throws Exception {
        for (int i = 0; i < msgs.length; i++) {
            System.out.println("MESSAGE #" + (i + 1) + ":");
            printEnvelope(msgs[i]);
        }
    }

    public void printEnvelope(Message message) throws Exception {

        Address[] a;

        if ((a = message.getFrom()) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("Email From : " + a[j].toString());
            }
        }

        String subject = message.getSubject();

        Date receivedDate = message.getReceivedDate();
        Date sentDate = message.getSentDate(); 

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Email Subject : " + subject);

        if (receivedDate != null) {
            System.out.println("Received Date: " + df.format(receivedDate));
        }

        System.out.println("Sent Date : " + df.format(sentDate));
    }


    public static void main(String args[]) {
        new OutLookReader_imaps();
    }

}
