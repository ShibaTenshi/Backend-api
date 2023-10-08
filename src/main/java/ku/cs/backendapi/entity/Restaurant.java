package ku.cs.backendapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Restaurant implements User{
    @Id
    @GeneratedValue
    private UUID idRestaurant;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Admin admin;

    @OneToOne
    private Booking booking;

    private String restaurantImage;
    private String username;
    private String password;
    private String email;
    private String restaurantName;
    private String description;

    @Override
    public UUID getId() {
        return idRestaurant;
    }
}
