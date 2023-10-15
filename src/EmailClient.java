
import java.util.ArrayList;
import java.util.Objects;

public class EmailClient{
    private static EmailClient instance = null;
    private ArrayList<Wishable> wishableRecipients = new ArrayList<Wishable>();
    private ArrayList<Wishable> newTodayWishableRecipients = new ArrayList<Wishable>();
    private ArrayList<Recipient> allRecipients = new ArrayList<Recipient>();
    private ArrayList<Email> SentEmailList = new ArrayList<Email>();

    private EmailClient(){
        this.allRecipients = ContentReader.getRecipientList();
        if(!this.allRecipients.isEmpty()){
            this.SentEmailList = ContentReader.getEmailList();
            this.wishableRecipients = getwishableRecipients();
        }
    }

    // Applying singleton design pattern such that there can be only one EmailClient Object.
    public static EmailClient getInstance() {
        if (instance == null) {
            instance = new EmailClient();
        }
        return instance;
    } 
    public ArrayList<Wishable> getwishableRecipients(){
        ArrayList<Wishable> wishableRecipientsTemp = new ArrayList<Wishable>();
        if (!this.allRecipients.isEmpty()) {
            for (Recipient recipient : allRecipients) {
                if (recipient instanceof Wishable) {
                    wishableRecipientsTemp.add((Wishable) recipient);
                }
            }
        }
        return wishableRecipientsTemp;
    }
    public void addRecipient(String recipientData){

        Recipient recipient = makeRecipient(recipientData);
        this.allRecipients.add(recipient);
        if(recipient instanceof Wishable){
            this.wishableRecipients.add((Wishable) recipient);
            this.newTodayWishableRecipients.add((Wishable) recipient);
        }
    }

    public void addNormalEmailDetails(Email email){
        this.SentEmailList.add(email);
    }
    public static Recipient makeRecipient(String recipientData){
        String[] details = recipientData.split(":");
        String type = details[0];
        String[] recipientDataPart = details[1].trim().split(",");
        if(type.equals("Official")){
            return new OfficialRecipient(recipientDataPart[0],recipientDataPart[1],recipientDataPart[2]);

        }
        else if(type.equals("Official_friend")){
            String[] birthdayDetails = recipientDataPart[3].split("/");
            Date birthday = new Date(birthdayDetails[0],birthdayDetails[1],birthdayDetails[2]);
            return new OfficialRecipientFriend(recipientDataPart[0],recipientDataPart[1],recipientDataPart[2],birthday);

        }
        else if(type.equals("Personal")){
            String[] birthdayDetails = recipientDataPart[3].split("/");
            Date birthday = new Date(birthdayDetails[0],birthdayDetails[1],birthdayDetails[2]);
            return new PersonalRecipient(recipientDataPart[0],recipientDataPart[1],recipientDataPart[2],birthday);
        }
        else{
            return null;
        }



    }
    public void printBirthdayRecipientsSetToGivenDate(Date date){
        ArrayList<Wishable> getBirthdayRecipients;
        getBirthdayRecipients = getBirthdayRecipientsSetToGivenDate(this.wishableRecipients,date);
        if(getBirthdayRecipients.isEmpty()){
            System.out.println("No one has a birthday on "+date.getYear()+"/"+date.getMonth()+"/"+date.getDay());
        }
        else {
            System.out.println("Birthdays on "+date.getYear()+"/"+date.getMonth()+"/"+date.getDay()+" are :");
            for (Wishable person : getBirthdayRecipients) {
                System.out.println((person).getName());
            }
        }

    }
    public ArrayList<Wishable> getBirthdayRecipientsSetToGivenDate(ArrayList<Wishable> wishableRecipientList, Date date){
        ArrayList<Wishable> getBirthdayRecipientsSetToGivenDate = new ArrayList<Wishable>();
        if(wishableRecipientList.isEmpty()){
            return getBirthdayRecipientsSetToGivenDate;
        }
        else{
            for(Wishable person: wishableRecipientList){
                if(date.getMonth().equals(person.getBirthday().getMonth())
                        && date.getDay().equals(person.getBirthday().getDay())){
                    getBirthdayRecipientsSetToGivenDate.add(person);
                }
            }
        }
        return getBirthdayRecipientsSetToGivenDate;
    }
    public void printEmailsSetToGivenDate(Date date){
        boolean haveEmails = false;
        int emailCount = 0;
        for(Email email: SentEmailList){
            if(date.getYear().equals(email.getSentDate().getYear())
                    && date.getMonth().equals(email.getSentDate().getMonth())
                    && date.getDay().equals(email.getSentDate().getDay())){
                if(emailCount==0){
                    System.out.println("Emails sent on "+date.getYear()+"/"+date.getMonth()+"/"+date.getDay()+" are :");
                }
                System.out.println("Email sent To: "+email.getRecipientAddress());
                System.out.println("Email Subject: "+email.getSubject());
                System.out.println("Email Body: "+email.getBody());
                System.out.println(" ");
                haveEmails = true;
                emailCount++;
            }
        }
        if(!haveEmails){
            System.out.println("No emails sent on "+date.getYear()+"/"+date.getMonth()+"/"+date.getDay());
        }
    }
    public void printRecipientCount(){
        System.out.println("\nNo of Recipients: "+this.allRecipients.size());
    }

    public void sendEmails(String status){
        Date today = new Date();
        String birthdaySubject = "Happy Birthday.";
        ArrayList<Wishable> todayBirthdayRecipients = null;
        if(Objects.equals(status, "begin")){
            todayBirthdayRecipients = getBirthdayRecipientsSetToGivenDate(this.wishableRecipients,today);
        }
        else{
            todayBirthdayRecipients = getBirthdayRecipientsSetToGivenDate(this.newTodayWishableRecipients,today);
        }
        if(todayBirthdayRecipients.isEmpty()){
            if(!allRecipients.isEmpty()){
                System.out.println("No new wishes sent today.");
            }

        }
        else{
            for(Wishable person: todayBirthdayRecipients){
                if(person instanceof PersonalRecipient){
                    Email wish  = new Email(((PersonalRecipient) person).getEmail(),birthdaySubject,((PersonalRecipient) person).sendWishes());
                        SendEmailTLS.sendMail(wish);
                        this.SentEmailList.add(wish);
                }
                else if(person instanceof OfficialRecipientFriend) {
                    Email wish = new Email(((OfficialRecipientFriend) person).getEmail(), birthdaySubject, ((OfficialRecipientFriend) person).sendWishes());
                        SendEmailTLS.sendMail(wish);
                        this.SentEmailList.add(wish);
                }
            }
            System.out.println("Today birthday wishes are sent.");
        }
    }

    public void serializeAllEmails(){
        ArrayList<Email> emails = new ArrayList<Email>(this.SentEmailList);
        ContentWriter.saveEmailList(emails);
        System.out.println("All email objects are saved to the disk.");
    }
}

