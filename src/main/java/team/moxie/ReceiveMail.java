package team.moxie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.swing.*;
import java.util.Arrays;
import com.sun.mail.pop3.POP3Store;

public class ReceiveMail{

    public static void receiveEmail(String pop3Host, String storeType,
                                    String user, String password) {
        try {
            /**
             * Initiates the session object using the gmail input properties.
             */

            Properties properties = new Properties();
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port","995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            /**
             * create the POP3 store object and connect with the pop server
             */
            Store emailStore = emailSession.getStore("pop3s");
            emailStore.connect(pop3Host,user, password);
            System.out.println(emailStore);

            /**
             * Creates the emailFolder object and opens it
             */
            Folder emailFolder = emailStore.getFolder("INBOX");
            System.out.println(emailFolder);
            emailFolder.open(Folder.READ_ONLY);


            /**
             * Retrieves the messages from the folder in an array.  Then stores the date and email from
             * the message into "entry" indices 0 and 1. Then, temporarily saves the content data in the array called
             * "content" while making every character uppercase for formatting.
             */

            Message[] messages = emailFolder.getMessages();
            System.out.println("You have " + messages.length + " new order(s) submitted via email.");
            String [] entry = new String [5];
            ArrayList<String[]> entries = new ArrayList<String []>();

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                Multipart tempMultipart = (Multipart) message.getContent();
                BodyPart bp = tempMultipart.getBodyPart(0);
                System.out.println(bp.toString());
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                Address[] address = message.getFrom();
                String email = address[0].toString();
                System.out.println(email);
                int emailIndex = email.indexOf("<") + 1;
                email = email.substring(emailIndex,email.length()-1);
                String date = message.getSentDate().toString();
                entry[0] = date;
                entry[1] = email;
                String [] content = bp.getContent().toString().toUpperCase().split(",");

                /**
                 * The following foreach statement is used for formatting each string within an "entry" array.
                 * It removes "\n" endings, other special characters, and white spaces from the "content" array
                 * for formatting consistency." It then stores the address, supply id, and quantity ordered and saves
                 * them in the "entry" array under indices 2,3,and 4.
                 */
                int counter = 2;
                for (String each : content) {
                    if (counter == 2){
                        each = each.replace("\n","");
                    }
                    else {
                        each = each.replace(" ", "");
                        each = each.replace("\n", "");
                        each = each.replace("[^\\x00-\\x7F]", "");
                        each = each.replace("[\\p{Cntrl}&&[^\r\n\t]]", "");
                        each = each.replace("\\p{C}", "");
                    }
                    entry[counter] = each;
                    counter ++;
                }
                for (String each :entry) {
                    System.out.println(each);
                }

                //Adds each order to the ArrayList for database updates.
                entries.add(entry);
            }

            /**
             * The following uses the "entries" ArrayList and updates the inventory database quantities after a correctly
             *  formatted email is accepted.
             */
            DbDriver driver = new DbDriver("50.116.26.153", "3306", "inv", "team", "GJ&8YahAh%kS#i");
            for (int i = 0; i < entries.size() ; i++) {

                String currEntryID = entries.get(i)[3];
                int currEntryQuantity = (int) Double.parseDouble(entries.get(i)[4]);

                if(driver.searchById(currEntryID) == null ){

                    System.out.println("No match for Inventory ID " + currEntryID + ".");
                }

                else{
                    dbEntry currentDBentry = driver.searchById(currEntryID);
                    String id = currentDBentry.getId();
                    int quantity = currentDBentry.getQuantity();
                    double wholesalePrice = currentDBentry.getWholesalePrice();
                    double salePrice = currentDBentry.getSalePrice();
                    String supplierID = currentDBentry.getSupplierId();
                    driver.updateEntry(id,(quantity-currEntryQuantity),wholesalePrice,salePrice,supplierID);
                    System.out.println(driver.searchById(currEntryID) + "\n\n");
                }

            } //End of database updates.




            //5) close the store and folder objects
            emailFolder.close(false);
            emailStore.close();

        }
        catch (java.sql.SQLException e) {e.printStackTrace();}
        catch (NoSuchProviderException e) {e.printStackTrace();}
        catch (MessagingException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    public static void main(String[] args) {

        String host = "pop.gmail.com";//change accordingly
        String mailStoreType = "pop3";
        String username= "teammoxierus@gmail.com";
        String password= "mox1e4u5";//change accordingly

        receiveEmail(host, mailStoreType, username, password);

    }
}