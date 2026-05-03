import java.util.*;

public class Screen {
     private String screenId;
     private List<Seat> seats;

     public Screen(String screenId,List<Seat>seats){
          this.screenId=screenId;
          this.seats=seats;
     }

     public List<Seat> getSeats(){
          return this.seats;
     }
}
