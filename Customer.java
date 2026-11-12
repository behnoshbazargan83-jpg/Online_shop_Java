package store;

public class Customer {
    private String name;
    private String phone;
    private int customerID;
    private boolean isVip;


    public Customer(String name,String phone,int customeID,boolean isVip){
        setName(name);
        setPhone(phone);
        this.customerID = customeID;
        setisVip(isVip);
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name =name;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        if (phone == null || phone.isEmpty()){
            throw new IllegalArgumentException("phone connot be empty");
        }
        this.phone = phone;
    }
    public int getcustomerID() {
        return customerID;
    }
    public boolean getisVip(){
        return isVip;
    }
    public void setisVip(boolean isVip){
        this.isVip =isVip;
    }
    public void displayinformation(){
        System.out.println("Name" + name);
        System.out.println("Phone" + phone);
        System.out.println("CustomerID" + customerID);
        System.out.println("IsVip" + isVip);

    }

}
