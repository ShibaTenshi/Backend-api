package ku.cs.backendapi.entity;

import jakarta.persistence.*;
import ku.cs.backendapi.common.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue
    private UUID idBooking;
    private LocalDateTime dateTime;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private RestaurantTableType restaurantTableType;

    @Enumerated(EnumType.STRING)
    private Status status;
}
