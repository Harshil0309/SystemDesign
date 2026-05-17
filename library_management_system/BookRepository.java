import java.util.*;
public class BookRepository{
    private Map<String,Book> bookStore=new HashMap<>();

    public void save(Book book){
        this.bookStore.put(book.getId(),book);
        return ;
    }

    public Book getBookById(String id){
        return this.bookStore.get(id);
    }
}