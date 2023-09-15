public class OfficialRecipient extends Recipient{
    private String designation;

    public OfficialRecipient(String name, String email, String designation){
        super(name,email); // calling the Recipient class constructor and setting the id.
        this.designation = designation;
    }

    public String getDesignation(){
        return this.designation;
    }
}
