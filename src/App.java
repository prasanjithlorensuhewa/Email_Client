// Index number -- 200357D
// If you change the clientList.txt file after running the email client please make sure to delete the lastEmailSentDate.txt file before the running the program again.
// To exit the email client program give the command line input as "-1"(without inverted commas).
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to the command-line based email client.");
        String lastEmailSentDate = ContentReader.readLastEmailSentDateFromFile();

        EmailClient emailClient = new EmailClient();
        Date date = new Date();
        Scanner scanner = new Scanner(System.in);
        boolean runningApp = true; // to check whether email client app running or not.

        if(lastEmailSentDate!=null){
            String[] lastEmailSentDateParts = lastEmailSentDate.trim().split("/");
            Date lastEmailSentDateParts1 = new Date(lastEmailSentDateParts[0], lastEmailSentDateParts[1], lastEmailSentDateParts[2]);
            // checking whether the wish of previously added birthday recipient are already sent.
            if(!lastEmailSentDateParts1.getYear().equals(date.getYear())
                    || !lastEmailSentDateParts1.getMonth().equals(date.getMonth())
                    || !lastEmailSentDateParts1.getDay().equals(date.getDay())){
                emailClient.sendEmails("begin");
                ContentWriter.saveLastEmailSentDateToFile(date);
            }
        }
        else{
            emailClient.sendEmails("begin"); // passing a parameter "begin" to send birthday emails to previously added recipients who have birthday today
            ContentWriter.saveLastEmailSentDateToFile(date);
        }


        while (runningApp) {
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application");
            try {
                int option = Integer.parseInt(scanner.nextLine().trim());

                switch (option) {
                    case 1:
                        // adding a new Recipient
                        String recipientData = scanner.nextLine().trim();
                        emailClient.addRecipient(recipientData);
                        ContentWriter.saveRecipientToFile(recipientData);
                        // input format - Official: nimal,nimal@gmail.com,ceo
                        break;
                    case 2:
                        // sending an email
                        String[] emailParts = scanner.nextLine().trim().split(",");
                        Email email = new Email(emailParts[0], emailParts[1], emailParts[2]);
                        emailClient.addNormalEmailDetails(email);
                        SendEmailTLS.sendMail(email);
                        // input format - email, subject, content
                        break;
                    case 3:
                        //printing recipients who have birthdays on the given date
                        String[] dateParts1 = scanner.nextLine().trim().split("/");
                        Date givenDate1 = new Date(dateParts1[0], dateParts1[1], dateParts1[2]);
                        emailClient.printBirthdayRecipientsSetToGivenDate(givenDate1);
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        break;
                    case 4:
                        // printing the details of all the emails sent on the input date
                        String[] dateParts2 = scanner.nextLine().trim().split("/");
                        Date givenDate2 = new Date(dateParts2[0], dateParts2[1], dateParts2[2]);
                        emailClient.printEmailsSetToGivenDate(givenDate2);
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        break;
                    case 5:
                        // printing the number of recipient objects in the application
                        emailClient.printRecipientCount();
                        break;
                    case -1:
                        // exiting the program
                        runningApp = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Input must be an integer");
            }

        }
        emailClient.sendEmails("end"); // passing a parameter "end" to send birthday emails to newly added(while program is running) recipients who have birthday today
        emailClient.serializeAllEmails();
        scanner.close();
        System.out.println("Email Client is closing. See you again.");
    }
}


