package ku.cs.backendapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class TableType {
    @Id
    @GeneratedValue
    private UUID idTableType;

    private int seatNumber;

    @OneToOne
    private Booking booking;
}
