import java.util.*;
public class BookService{
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public Book addBook(String title){
        String bookId= UUID.randomUUID().toString();
        Book book=new Book(bookId,title);
        this.bookRepository.save(book);
        return book;
    }

    public Book getBookById(String id){
        return this.bookRepository.getBookById(id);
    }

    public void updateBookStatus(String id, BookStatus status){
        Book book=this.bookRepository.getBookById(id);
        book.setStatus(status);
        this.bookRepository.save(book);
        return;
    }
}