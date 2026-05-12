import java.util.UUID;
public class DriverService{
    private DriverRepository driverRepository ;

    public DriverService(DriverRepository driverRepository){
        this.driverRepository=driverRepository;
    }

    public Driver addDriver(String name,String contact,Vehicle vehicle){

        String id=UUID.randomUUID().toString();
        Driver driver=new Driver(id,name,contact,vehicle);
        this.driverRepository.save(driver);
        return driver;

    }
    public void updateAvailability(String id, boolean availability){
        Driver driver=this.driverRepository.getDriverById(id);
        if(driver!=null){
            driver.setIsAvailable(availability);
            this.driverRepository.save(driver);
        }
    }

    public Driver getDriverById(String driverId){
        Driver driver=this.driverRepository.getDriverById(driverId);
        return driver;
    }

    public Driver findAvailableDriver(){
        Driver driver=this.driverRepository.findAvailableDriver();
        return driver;
    }

}