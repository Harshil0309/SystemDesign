import java.util.UUID;

public class RiderService{
    private RiderRepository riderRepository;

    public RiderService(RiderRepository riderRepository){
        this.riderRepository=riderRepository;
    }

    private boolean isInputValid(String name,String contact){
        if(name.length() ==0 || contact.length()==0){
            return false;
        }
        return true;
    }

    public String createRider(String name,String contact){
        if(!isInputValid(name,contact)){
            throw new IllegalArgumentException("Details are missing");
        }
        Rider exists = this.riderRepository.getRiderByContact(contact);
        if(exists != null){
            throw new IllegalArgumentException("Rider already exists");
        }
        String id=UUID.randomUUID().toString();
        Rider rider=new Rider(id,name,contact);
        this.riderRepository.save(rider);
        return "Rider created successfully";
    }

    public String assignRider(String orderId){
        Rider rider=this.riderRepository.getAvailableRider();
        if(rider==null){
            throw new IllegalStateException("No available riders");
        }
        rider.assignOrder(orderId);
        this.riderRepository.save(rider);
        return rider.getId();
    }

    public String completeDelivery(String orderId){
        Rider rider=this.riderRepository.getRiderByOrderId(orderId);
        if(rider==null){
            throw new IllegalStateException("Order was never assigned");
        }
        rider.completeOrder();
        this.riderRepository.save(rider);
        return "Order completed";
    }
    
}