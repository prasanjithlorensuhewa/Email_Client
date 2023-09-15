import java.io.*;
import java.util.ArrayList;

public abstract class ContentReader {
    // deserializing sent email objects and assign them to an arraylist.
    public static ArrayList<Email> getEmailList() {
        ArrayList<Object> objects = null;
        FileInputStream filestream = null;
        ObjectInputStream os = null;
        try {
            filestream = new FileInputStream("objects.ser");
            os = new ObjectInputStream(filestream);
            Object object = os.readObject();

            objects = (ArrayList<Object>) object;
        }
        catch(IOException e){
            System.out.println("Serialized Email objects can't be Deserialized");
        }
        catch(ClassNotFoundException e){
            objects = new ArrayList<Object>();
        }
        finally{
            if(filestream != null){
                try{
                    filestream.close();
                }
                catch(Exception ignoreMe){}
            }
            if(os != null){
                try{
                    os.close();
                }
                catch(Exception ignoreMe){}
            }
        }
        if(objects == null){
            return new ArrayList<Email>();
        }
        System.out.println("Previously sent Email are loaded");
        return (ArrayList<Email>) (ArrayList<?>) objects;
    }

    // read recipient data from the clientList.txt when starting the email client app.
    public static ArrayList<String> readFileContent(){
        ArrayList<String> recipientData = new ArrayList<String>();
        FileReader fileReader = null;
        BufferedReader reader = null;
        try{
            fileReader = new FileReader("clientList.txt");
            reader = new BufferedReader(fileReader);
            String line = null;
            while((line = reader.readLine()) != null){
                recipientData.add(line);
            }
        }
        catch (FileNotFoundException e){

        }
        catch (IOException e){
            System.out.println("Recipient details can't be loaded");
        }
        finally{
            if(fileReader != null){
                try{
                    fileReader.close();
                }
                catch(Exception ignoreMe){}
            }
            if(reader != null){
                try{
                    reader.close();
                }
                catch(Exception ignoreMe){}
            }
        }

        return recipientData;
    }

    // making objects for each recipient by iterating through the recipientData arraylist.
    public static ArrayList<Recipient> getRecipientList(){
        ArrayList<Recipient> recipients = new ArrayList<Recipient>();
        ArrayList<String> recipientData = readFileContent();
        if(recipientData.isEmpty()){
            return recipients;
        }
        else{
            System.out.println("Recipient details are loaded");
            Recipient recipient;
            for (String recipientDetail : recipientData) {
                recipient = EmailClient.makeRecipient(recipientDetail);
                if(recipient != null){
                    recipients.add(recipient);
                }
            }

        }
        return recipients;
    }
    // reading last email sent data to a file.
    public static String readLastEmailSentDateFromFile() {
        FileReader fileReader = null;
        BufferedReader reader = null;
        String LastDate = null;
        try {
            fileReader = new FileReader("lastEmailSentDate.txt");
            reader = new BufferedReader(fileReader);
            LastDate = reader.readLine();


        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println("Recipient details can't be loaded");
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception ignoreMe) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ignoreMe) {
                }
            }
        }

        return LastDate;
    }
}