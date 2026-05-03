public class Seat {
    private String id;
    private Integer row;
    private Integer number;

    public Seat(String id, Integer row, Integer number) {
        this.id = id;
        this.row = row;
        this.number = number;
    }

    public String getSeatId() {
        return this.id;
    }
}
