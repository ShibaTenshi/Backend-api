package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class RestaurantBookingDTO {
    private String name;
    private String dateTime;
    private String seatNumber;
}
