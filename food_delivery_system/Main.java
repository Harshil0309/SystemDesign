import java.util.*;

public class Main {

    public static void main(String[] args) {

        AppLogger.log("🚀 Starting Food Delivery System...");

        // ------------------ Repositories ------------------
        UserRepository userRepository = new UserRepository();
        RiderRepository riderRepository = new RiderRepository();
        RestaurantRepository restaurantRepository = new RestaurantRepository();
        OrderRepository orderRepository = new OrderRepository();

        AppLogger.log("Repositories initialized");

        // ------------------ Services ------------------
        UserService userService = new UserService(userRepository);
        RiderService riderService = new RiderService(riderRepository);
        RestaurantService restaurantService = new RestaurantService(restaurantRepository);
        OrderService orderService = new OrderService(orderRepository, riderService, restaurantService);

        AppLogger.log("Services initialized");

        // ------------------ Create User ------------------
        AppLogger.log("Creating user...");
        userService.createUser("Harshil", "Bangalore", "9999999999");
        User user = userRepository.getUserByContact("9999999999");
        AppLogger.log("User created with ID: " + user.getId());

        // ------------------ Create Restaurant ------------------
        AppLogger.log("Creating restaurant...");
        restaurantService.createRestaurant("Dominos", "Bangalore", "8888888888");

        Restaurant restaurant = null;
        for (Restaurant r : restaurantRepository.getAll().values()) {
            if (r.getName().equals("Dominos")) {
                restaurant = r;
                break;
            }
        }

        if (restaurant == null) {
            AppLogger.log("ERROR: Restaurant creation failed");
            return;
        }

        String restaurantId = restaurant.getId();
        AppLogger.log("Restaurant created with ID: " + restaurantId);

        // ------------------ Add Menu Items ------------------
        AppLogger.log("Adding menu items...");
        restaurantService.addMenuItem(restaurantId, "Pizza", "Cheese Pizza", 200, 10);
        restaurantService.addMenuItem(restaurantId, "Burger", "Veg Burger", 100, 5);

        AppLogger.log("Menu items added");

        // Fetch menu item IDs
        List<String> itemIds = new ArrayList<>(restaurant.getMenu().keySet());

        // ------------------ Create Rider ------------------
        AppLogger.log("Creating rider...");
        riderService.createRider("Rider1", "7777777777");
        AppLogger.log("Rider created");

        // ------------------ Place Order ------------------
        AppLogger.log("Placing order...");
        List<ItemRequest> items = new ArrayList<>();
        items.add(new ItemRequest(itemIds.get(0), 2));
        items.add(new ItemRequest(itemIds.get(1), 1));

        orderService.placeOrder(user.getId(), restaurantId, items);
        AppLogger.log("Order placed successfully");

        // ------------------ Fetch Order ------------------
        Order order = null;
        for (Order o : orderRepository.getAll().values()) {
            order = o;
            break;
        }

        if (order == null) {
            AppLogger.log("ERROR: Order not found after placement");
            return;
        }

        String orderId = order.getId();
        AppLogger.log("Order ID: " + orderId);

        // ------------------ Order Lifecycle ------------------
        AppLogger.log("Accepting order...");
        orderService.acceptOrder(orderId);

        AppLogger.log("Starting preparation...");
        orderService.startPreparing(orderId);

        AppLogger.log("Out for delivery...");
        orderService.markOutForDelivery(orderId);

        AppLogger.log("Marking delivered...");
        orderService.markDelivered(orderId);

        AppLogger.log("✅ Order completed successfully!");
    }
}