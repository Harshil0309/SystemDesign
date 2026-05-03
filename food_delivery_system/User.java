public class User{
    private String id;
    private String name;
    private String address;
    private String contact;

    public User(String id,String name,String address,String contact){
        this.id=id;
        this.name=name;
        this.address=address;
        this.contact=contact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateContact(String contact) {
        this.contact = contact;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

}