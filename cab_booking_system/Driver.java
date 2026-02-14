public class Driver {
    private String id;
    private String name;
    private Boolean isAvailable;

    public Driver(String id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public Boolean checkAvailability() {
        return isAvailable;
    }

    public void markUnavailable() {
        this.isAvailable = false;
        return;
    }

    public void markAvailable() {
        this.isAvailable = true;
        return;
    }
}
