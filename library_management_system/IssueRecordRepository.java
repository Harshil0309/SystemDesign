import java.util.*;
public class IssueRecordRepository{
    private Map<String,IssueRecord>issueRecordStore=new HashMap<>();

    public void save(IssueRecord record){
        this.issueRecordStore.put(record.getId(),record);
        return ;
    }

    public IssueRecord getRecordIssueById(String id){
        return this.issueRecordStore.get(id);
    }
}