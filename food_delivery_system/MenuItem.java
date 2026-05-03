public class MenuItem{

    private String id;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
    private Boolean isAvailable;
    private Restaurant restaurant;


    public MenuItem(String id, String name, String description, Integer price, Integer quantity, Boolean isAvailable, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
        this.restaurant = restaurant;
    }
}