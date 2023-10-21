package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class BookingRequest {
    private String tokenId;
    private String restaurantName;
    private int seatNumber;
    private String dateTime;
}
