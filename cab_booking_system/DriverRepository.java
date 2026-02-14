import java.util.*;

public class DriverRepository {
    private Map<String, Driver> drivers = new HashMap<>();

    public void addDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    public List<Driver> getAvailableDrivers() {
        return drivers.values().stream().filter(Driver::checkAvailability).toList();
    }
}
