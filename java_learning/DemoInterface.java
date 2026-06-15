// @FunctionalInterface
public interface DemoInterface {

    public abstract void function();
    default void function2(){
        System.out.println("this is the dummy implementation");
    }
    
}
