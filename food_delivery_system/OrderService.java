import java.util.UUID;
import java.util.*;

public class OrderService{
    private OrderRepository orderRepository;
    private RiderService riderService;
    private RestaurantService restaurantService;

    public OrderService(OrderRepository orderRepository, RiderService riderService,RestaurantService restaurantService) {
        this.orderRepository = orderRepository;
        this.riderService = riderService;
        this.restaurantService=restaurantService;
    }

    public void placeOrder(String userId,String restaurantId,List<ItemRequest> items){
      if(userId==null || restaurantId==null || items==null){
        throw new RuntimeException("Missing details, cannot place order");
      }
      Restaurant restaurant=this.restaurantService.getRestaurantById(restaurantId);
      if (restaurant == null) {
          throw new RuntimeException("Restaurant not found");
      }

      for(ItemRequest item : items){
        MenuItem menuItem= restaurant.getItemById(item.getItemId());
        if(!menuItem.getIsAvailable() || menuItem.getQuantity()<item.getQuantity()){
           throw new RuntimeException("Item not available");
        }   
      }

      List<OrderItem>list=new ArrayList<>();

      for(ItemRequest item : items){
        MenuItem menuItem= restaurant.getItemById(item.getItemId());
        OrderItem orderItem= new OrderItem(menuItem.getId(),menuItem.getName(),menuItem.getPrice(),item.getQuantity(),restaurantId);  
        list.add(orderItem); 
      }

      double billAmount=0;
      for(OrderItem item:list){
        double temp= item.getPrice()*item.getQuantity();
        billAmount+=temp;
      }

      String orderId= UUID.randomUUID().toString();

      Order order=new Order(orderId,list,userId,restaurantId,billAmount);

      this.orderRepository.save(order);
      
    }

    public Order getOrderById(String orderId){
      return this.orderRepository.getOrderById(orderId);
    }

    public void acceptOrder(String orderId){
      Order order=this.orderRepository.getOrderById(orderId);
      if(order==null){
        throw new RuntimeException("Order doesn't exist");
      }
      order.acceptOrder();
      this.orderRepository.save(order);
    }

    public void startPreparing(String orderId){
      Order order=this.orderRepository.getOrderById(orderId);
      if(order==null){
        throw new RuntimeException("Order doesn't exist");
      }
      order.startPreparing();
      String riderId= this.riderService.assignRider(orderId);
      order.assignRider(riderId);
      this.orderRepository.save(order);
    }

    public void markOutForDelivery(String orderId){
      Order order=this.orderRepository.getOrderById(orderId);
      if(order==null){
        throw new RuntimeException("Order doesn't exist");
      }
      order.outForDelivery();
      this.orderRepository.save(order);
    }

    public void markDelivered(String orderId){
      Order order=this.orderRepository.getOrderById(orderId);
      if(order==null){
        throw new RuntimeException("Order doesn't exist");
      }
      order.markDelivered();
      this.riderService.completeDelivery(orderId);
      this.orderRepository.save(order);
    }

    public void rejectOrder(String orderId){
      Order order=this.orderRepository.getOrderById(orderId);
      if(order==null){
        throw new RuntimeException("Order doesn't exist");
      }
      order.rejectOrder();
      this.orderRepository.save(order);
    }
    
}