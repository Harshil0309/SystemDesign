import java.util.*;

public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public String createRestaurant(String name, String address, String contact) {

        if (name == null || name.isEmpty() ||
            address == null || address.isEmpty() ||
            contact == null || contact.isEmpty()) {
            throw new RuntimeException("Invalid input");
        }

        for (Restaurant r : restaurantRepository.getAll().values()) {
            if (r.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Restaurant already exists");
            }
        }

        String restaurantId = UUID.randomUUID().toString();
        Map<String, MenuItem> menu = new HashMap<>();
        Restaurant restaurant = new Restaurant(
            restaurantId,
            name,
            address,
            contact,
            menu
        );
        restaurantRepository.save(restaurant);
        return "Restaurant created successfully";
    }

    public Restaurant getRestaurantById(String restaurantId){
      if(restaurantId==null){
        throw new RuntimeException("Restaurant Id is missing");
      }
      Restaurant restaurant=this.restaurantRepository.getRestaurantById(restaurantId);
      if(restaurant==null){
        throw new RuntimeException("Restaurant doesn't exist");
      }
      return restaurant;
    }

    public void updateRestaurant(String restaurantId,String name,String contact){
       Restaurant restaurant=this.restaurantRepository.getRestaurantById(restaurantId);
       if(restaurant==null){
        throw new RuntimeException("Restaurant doesn't exist");
       }
       if(name==null || contact==null){
        throw new RuntimeException("Details missing");
       }
       restaurant.setName(name);
       restaurant.setContact(contact);
       this.restaurantRepository.save(restaurant);
    }

    public String addMenuItem(String restaurantId, String name, String description, Integer price, Integer quantity) {

        if (restaurantId == null || name == null || name.isEmpty() ||
            price == null || price <= 0 || quantity == null || quantity < 0) {
            throw new RuntimeException("Invalid input");
        }

        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found");
        }
        for (MenuItem item : restaurant.getMenu().values()) {
            if (item.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Menu item already exists");
            }
        }
        String itemId = UUID.randomUUID().toString();
        MenuItem menuItem = new MenuItem(
            itemId,
            name,
            description,
            price,
            quantity,
            true,
            restaurant
        );

        restaurant.getMenu().put(itemId, menuItem);
        restaurantRepository.save(restaurant);
        return "Menu item added successfully";
    }

    public void removeMenuItem(String restaurantId,String itemId){
      if(restaurantId==null || itemId==null){
        throw new RuntimeException("Invalid details");
      }

      Restaurant restaurant= this.restaurantRepository.getRestaurantById(restaurantId);
      if(restaurant==null){
          throw new RuntimeException("Restaurant not found");
      }

      MenuItem menuItem=restaurant.getItemById(itemId);
      if(menuItem==null){
        throw new RuntimeException("Item doesnt exist on the menu");
      }

      restaurant.getMenu().remove(itemId);
      this.restaurantRepository.save(restaurant);

    }

    public String updateMenuItem(String restaurantId, String itemId, String name, String description, Integer price) {

        if (restaurantId == null || itemId == null || name == null || name.isEmpty() || price == null || price <= 0) {
            throw new RuntimeException("Invalid input");
        }

        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found");
        }

        MenuItem item = restaurant.getItemById(itemId);
        if (item == null) {
            throw new RuntimeException("Menu item not found");
        }

        for (MenuItem m : restaurant.getMenu().values()) {
            if (!m.getId().equals(itemId) && m.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Another item with same name exists");
            }
        }

        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);

        restaurantRepository.save(restaurant);

        return "Menu item updated";
    }

    public String updateItemQuantity(String restaurantId, String itemId, Integer quantity) {

        if (restaurantId == null || itemId == null || quantity == null || quantity < 0) {
            throw new RuntimeException("Invalid input");
        }

        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found");
        }

        MenuItem item = restaurant.getItemById(itemId);
        if (item == null) {
            throw new RuntimeException("Menu item not found");
        }

        item.setQuantity(quantity);

        if (quantity == 0) {
            item.setIsAvailable(false);
        } else {
            item.setIsAvailable(true);
        }

        restaurantRepository.save(restaurant);

        return "Quantity updated";
    }

    public String setItemAvailability(String restaurantId, String itemId, Boolean isAvailable) {

        if (restaurantId == null || itemId == null || isAvailable == null) {
            throw new RuntimeException("Invalid input");
        }

        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found");
        }

        MenuItem item = restaurant.getItemById(itemId);
        if (item == null) {
            throw new RuntimeException("Menu item not found");
        }

        item.setIsAvailable(isAvailable);

        restaurantRepository.save(restaurant);

        return "Availability updated";
    }

    public MenuItem getMenuItem(String restaurantId,String itemId){
      if(restaurantId==null || itemId==null){
        throw new RuntimeException("Invalid details");
      }

      Restaurant restaurant= this.restaurantRepository.getRestaurantById(restaurantId);
      if(restaurant==null){
          throw new RuntimeException("Restaurant not found");
      }

      MenuItem menuItem=restaurant.getItemById(itemId);
      if(menuItem==null){
        throw new RuntimeException("Item doesnt exist on the menu");
      }
      return menuItem;
    }

    public Map<String, MenuItem> getMenu(String restaurantId){
       if(restaurantId==null){
        throw new RuntimeException("Invalid details");
      }

      Restaurant restaurant= this.restaurantRepository.getRestaurantById(restaurantId);
      if(restaurant==null){
          throw new RuntimeException("Restaurant not found");
      }
      return restaurant.getMenu();
    }
}

