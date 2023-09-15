import java.io.*;
import java.util.ArrayList;
public abstract class ContentWriter {
    // serializing sent email objects as an arraylist
    public static void saveEmailList(ArrayList<Email> emailList){
        FileOutputStream fileStream = null;
        ObjectOutputStream os = null;
        try{
            fileStream = new FileOutputStream("Objects.ser"); //.ser file used as they are mainly used to store serialized objects
            os = new ObjectOutputStream(fileStream);
            os.writeObject(emailList);
        }
        catch(IOException e) {
            System.out.println("Email objects can't be Serialized.");
        }
        finally{
            if(os != null){
                try{
                    os.close();
                }
                catch(Exception ignoreMe){}
            }
            if(fileStream != null){
                try{
                    fileStream.close();
                }
                catch(Exception ignoreMe){}
            }

        }
    }

    // saving Recipient Data to the clientList.txt file
    public static void saveRecipientToFile(String recipientData){
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try{
            fileWriter = new FileWriter("clientList.txt",true);
            writer = new BufferedWriter(fileWriter);
            writer.write(recipientData+"\n");
        }
        catch(IOException e){
            System.out.println("Recipient Details cannot be saved in clientList file");
            e.printStackTrace();
        }
        finally{
            if(writer != null){
                try{
                    writer.close();
                }
                catch(Exception ignoreMe){}
            }
            if(fileWriter != null){
                try{
                    fileWriter.close();
                }
                catch(Exception ignoreMe){}
            }

        }
    }

    // saving last email sent data to a file.
    public static void saveLastEmailSentDateToFile(Date date){
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try{
            fileWriter = new FileWriter("lastEmailSentDate.txt");
            writer = new BufferedWriter(fileWriter);
            writer.write(date.getYear()+"/"+date.getMonth()+"/"+date.getDay()+"\n");
        }
        catch(IOException e){
            System.out.println("Last email sent date can't be saved to the logs file");
            e.printStackTrace();
        }
        finally{
            if(writer != null){
                try{
                    writer.close();
                }
                catch(Exception ignoreMe){}
            }
            if(fileWriter != null){
                try{
                    fileWriter.close();
                }
                catch(Exception ignoreMe){}
            }

        }
    }
}