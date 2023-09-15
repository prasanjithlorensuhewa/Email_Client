import java.io.Serializable;
public class Email implements Serializable{
    private final String recipientAddress;
    private final String subject;
    private final String body;
    private final Date sentDate;

    public Email(String recipientAddress, String subject, String body){
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.body = body;
        this.sentDate = new Date();
    }

    public String getRecipientAddress(){
        return this.recipientAddress;
    }

    public String getSubject(){
        return this.subject;
    }

    public String getBody(){
        return this.body;
    }

    public Date getSentDate(){
        return this.sentDate;
    }
}
