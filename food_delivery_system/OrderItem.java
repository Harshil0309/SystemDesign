public class OrderItem{

    private String id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Restaurant restaurant;


    public OrderItem(String id, String name, Integer price, Integer quantity,  Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.restaurant = restaurant;
    }
}