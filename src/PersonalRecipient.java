public class PersonalRecipient extends Recipient implements Wishable{
    private String nickname;
    Date birthday = new Date();

    public PersonalRecipient(String name, String nickname, String email, Date birthday){
        super(name,email); // calling the Recipient class constructor and setting the id.
        this.nickname = nickname;
        this.birthday = birthday;
    }

    @Override
    public String sendWishes(){
        return "Hugs and love on your birthday."+"\n Prasanjith";
    }
    public String getNickname() {
        return this.nickname;
    }

    @Override
    public Date getBirthday(){
        return this.birthday;
    }


}
