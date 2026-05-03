import java.util.*;

public class RiderRepository{
    private Map<String, Rider> riderStore = new HashMap<>();

    public void save(Rider rider){
        riderStore.put(rider.getId(),rider);
    }

    public Rider getRider(String riderId){
        return riderStore.get(riderId);
    }

    public Rider getRiderByContact(String contact){
        for(Rider rider:riderStore.values()){
            if(rider.getContact().equals(contact)){
                return rider;
            }
        }
        return null;
    }

    public Rider getAvailableRider(){
        for(Rider rider:riderStore.values()){
            if(rider.isAvailable()) return rider;
        }
        return null;
    }

    public Rider getRiderByOrderId(String orderId){
        for(Rider rider:riderStore.values()){
            if(rider.getCurrentOrderId().equals(orderId)){
                return rider;
            }
        }
        return null;
    }
}