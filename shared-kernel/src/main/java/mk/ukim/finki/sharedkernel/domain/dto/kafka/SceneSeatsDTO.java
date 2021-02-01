package mk.ukim.finki.sharedkernel.domain.dto.kafka;

public class SceneSeatsDTO {

    private int capacity;

    private int seatsInRow;

    public SceneSeatsDTO(int capacity,int seatsInRow)
    {
        this.capacity=capacity;
        this.seatsInRow=seatsInRow;
    }

    public SceneSeatsDTO(){}

    public int getCapacity() {
        return capacity;
    }

    public int getSeatsInRow() {
        return seatsInRow;
    }
}
