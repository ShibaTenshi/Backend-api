package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class BookingRequest {
    private String tokenId;
    private String restaurantName;
    private String numSeat;
    private String time;
    private String year;
    private String month;
    private String dayTh;
    private String dayOfWeek;
}
