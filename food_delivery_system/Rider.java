public class Rider{
    private String id;
    private String name;
    private String contact;
    private boolean isAvailable;
    private String currentOrderId;

    public Rider(String id,String name,String contact){
        this.id=id;
        this.name=name;
        this.contact=contact;
        this.isAvailable=true; 
        this.currentOrderId=null;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean isAvailable){
        this.isAvailable=isAvailable;
    }

    public String getCurrentOrderId() {
        return currentOrderId;
    }

    public void assignOrder(String orderId){
        if(isAvailable){
            this.currentOrderId=orderId;
            this.isAvailable=false;
        }
    }

    public void completeOrder(){
        if(!isAvailable){
            this.currentOrderId=null;
            this.isAvailable=true;
        }
    }

}