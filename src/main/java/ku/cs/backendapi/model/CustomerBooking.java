package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class CustomerBooking {
    private String bookingId;
    private String restaurantName;
    private String description;
    private String status;
    private String date;
    private String time;
}
