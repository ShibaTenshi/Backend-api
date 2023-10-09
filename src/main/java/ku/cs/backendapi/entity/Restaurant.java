package ku.cs.backendapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.UUID;

@Data
@Entity
public class Restaurant{
    @Id
    @GeneratedValue
    private UUID idRestaurant;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Admin admin;

    @OneToOne
    private Booking booking;

    private String imageLink;
    private String username;
    private String password;
    private String email;
    private String restaurantName;
    private String description;
    private String menu;
    private Time openTime;
    private Time closeTime;
    private String location;
    private String mapLink;
}
