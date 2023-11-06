package ku.cs.backendapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class RestaurantTableType {
    @Id
    private UUID id;
    private int numOfTable;

    @ManyToOne
    private TableType tableType;

    @ManyToOne
    private Restaurant restaurant;
}
