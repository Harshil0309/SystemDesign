import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository {

    private Map<String, Restaurant> restaurantStore;

    public RestaurantRepository() {
        this.restaurantStore = new HashMap<>();
    }

    public void save(Restaurant restaurant) {
        restaurantStore.put(restaurant.getId(), restaurant);
    }

    public Restaurant getRestaurantById(String restaurantId) {
        return restaurantStore.get(restaurantId);
    }

    public boolean exists(String restaurantId) {
        return restaurantStore.containsKey(restaurantId);
    }

    public void delete(String restaurantId) {
        restaurantStore.remove(restaurantId);
    }

    public Map<String, Restaurant> getAll() {
        return restaurantStore;
    }
}