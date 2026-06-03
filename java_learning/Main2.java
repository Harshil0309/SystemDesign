import java.util.*;
public class Main2{
    public static void main(String []args){
        System.out.println("this is the main2");
        // int[] arr={5,9,1,10};
        // Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));
        Employee[] emp=new Employee[4];
        emp[0]=new Employee(5,"john");
        emp[1]=new Employee(9,"jake");
        emp[2]=new Employee(1,"paul");
        emp[3]=new Employee(10,"amy");
        Arrays.sort(emp);
        System.out.println(Arrays.toString(emp));

        Object o= new Object();
        System.out.println(o.getClass());
    }
}