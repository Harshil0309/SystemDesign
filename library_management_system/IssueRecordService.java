import java.util.*;
public class IssueRecordService{
    private IssueRecordRepository issueRecordRepository;
    private BookRepository bookRepository;
    
    public IssueRecordService(IssueRecordRepository issueRecordRepository, BookRepository bookRepository){
        this.issueRecordRepository=issueRecordRepository;
        this.bookRepository=bookRepository;
    }

    public IssueRecord issueBook(String userId,String bookId,String issueDate){
        Book book=this.bookRepository.getBookById(bookId);
        if(book.getStatus()==BookStatus.AVAILABLE){
            String id=UUID.randomUUID().toString();
            IssueRecord record=new IssueRecord(id,userId,bookId,issueDate);
            this.issueRecordRepository.save(record);
            book.setStatus(BookStatus.ISSUED);
            this.bookRepository.save(book);
            return record;
        }
        return null;
    }

    public void returnBook(String recordId, String returnDate){
        IssueRecord record=this.issueRecordRepository.getRecordIssueById(recordId);
        record.setReturnDate(returnDate);
        this.issueRecordRepository.save(record);
        Book book = this.bookRepository.getBookById(record.getBookId());
        book.setStatus(BookStatus.AVAILABLE);
        this.bookRepository.save(book);
        return;

    }
}