
public class Employee implements Comparable<Employee>{
    private int id;
    private String name;

    public Employee(int id,String name){
        this.id=id;
        this.name=name;
    }

    public String toString(){
        return "[id " + this.id + " , name=" + this.name +"]";
    }

    public int compareTo(Employee o){
        return this.id-o.id;
    }
    
}