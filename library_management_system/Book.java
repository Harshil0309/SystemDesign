public class Book {

    private String id;
    private String title;
    private BookStatus status;

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
        this.status = BookStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}