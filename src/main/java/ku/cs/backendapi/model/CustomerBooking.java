package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class CustomerBooking {
    private String id;
    private String restaurantName;
    private String logo;
    private String status;
    private String dateTime;
    private String numSeat;
}
