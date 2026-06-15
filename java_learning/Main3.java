import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public class Main3 {

    static class demo implements DemoInterface{
         public void function(){
            System.out.println("implemented function");
         }

         public void function2(){
            System.out.println("implemented function2");
         }
    }

    public static void main(String[] args){
        System.out.println("this is the main funciton");
        demo demoObj=new demo();
        demoObj.function();
        demoObj.function2();
    }
    
}
