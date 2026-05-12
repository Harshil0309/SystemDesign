public class Driver{
    
    private String id;
    private String name;
    private String contact;
    private Vehicle vehicle;
    private boolean isAvailable;

    public Driver(String id,String name,String contact,Vehicle vehicle){
        this.id=id;
        this.name=name;
        this.contact=contact;
        this.vehicle=vehicle;
        this.isAvailable=true;

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

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}