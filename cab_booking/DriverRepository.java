import java.util.*;
public class DriverRepository{
    private Map<String,Driver> driverStore=new HashMap<>();

    public void save(Driver driver){
        driverStore.put(driver.getId(),driver);
    }

    public Collection<Driver> getAllDrivers() {
        return driverStore.values();
    }

    public Driver getDriverById(String id) {
        return driverStore.get(id);
    }

    public Driver findAvailableDriver(){
        for(Driver driver: driverStore.values()){
            if(driver.getIsAvailable()==true){
                return driver;
            }
        }
        return null;
    }
}