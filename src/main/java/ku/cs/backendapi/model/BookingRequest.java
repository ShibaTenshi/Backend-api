package ku.cs.backendapi.model;

import lombok.Data;

import java.util.UUID;

@Data
public class BookingRequest {
    private UUID idTableType;

    private int seatNumber;
}
