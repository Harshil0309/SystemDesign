class Counter{
    private int count=0;

    public synchronized void increment(){
        String threadName = Thread.currentThread().getName();
        System.out.println("Running in thread: " + threadName);
        count ++;
    }

    public int getCount(){
        return count;
    }
}


public class Main{
    public static void main(String[] args) throws Exception{
        Counter counter=new Counter();

        Thread t1=new Thread(()->{
            for(int i=0;i<10;i++){
                counter.increment();
            }
        });

        Thread t2=new Thread(()->{
            for(int i=0;i<10;i++){
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getCount());



    }
}