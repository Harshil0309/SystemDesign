public class Restaurant{
    private String id;
    private String name;
    private String address;
    private String contact;
    private List<MenuItem> menu;
    
    public Restaurant(String id,String name,String address,String contact,List<MenuItem>menu){
        this.id=id;
        this.name=name;
        this.address=address;
        this.contact=contact;
        this.menu=menu;
    }

}