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

    @OneToOne
    private Restaurant idRestaurant;

    @OneToOne
    private TableType idTableType;

    private Status status;

}