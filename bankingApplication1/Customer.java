package bankingApplication1;

public class Customer {
    private int idCard;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Customer(int idCard, String firstName, String lastName, String phoneNumber) {
        this.idCard = idCard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public int getIdCard(){
        return this.idCard;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
}
