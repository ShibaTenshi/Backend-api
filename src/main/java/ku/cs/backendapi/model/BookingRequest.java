package ku.cs.backendapi.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingRequest {
    private String tokenId;
    private String restaurantName;
    private int seatNumber;
    private String dateTime;
}
