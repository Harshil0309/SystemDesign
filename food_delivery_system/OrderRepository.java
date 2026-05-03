import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    private Map<String, Order> orderStore;

    public OrderRepository() {
        this.orderStore = new HashMap<>();
    }

    public void save(Order order) {
        orderStore.put(order.getId(), order);
    }

    public Order getOrderById(String orderId) {
        return orderStore.get(orderId);
    }

    public boolean exists(String orderId) {
        return orderStore.containsKey(orderId);
    }

    public void delete(String orderId) {
        orderStore.remove(orderId);
    }

    public Map<String, Order> getAll() {
        return orderStore;
    }
}