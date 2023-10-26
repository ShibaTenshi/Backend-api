package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class RestaurantBookingDTO {
    private String date;
    private String name;
    private String time;
    private int seatNumber;
}
