public class OfficialRecipientFriend extends OfficialRecipient implements Wishable{
    Date birthday = new Date();

    public OfficialRecipientFriend(String name, String email, String designation, Date birthday){
        super(name,email,designation); // calling the Official Recipient class constructor and setting the id.
        this.birthday = birthday;
    }

    @Override
    public String sendWishes(){
        return "Wish you a Happy Birthday."+"\n Prasanjith";
    }

    @Override
    public Date getBirthday(){
        return this.birthday;
    }


}
