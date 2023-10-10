package ku.cs.backendapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Restaurant implements User{
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Admin admin;

    @OneToMany
    private List<Booking> bookingList;

    private String imageLink;
    private String username;
    private String password;
    private String email;
    private String restaurantName;
    private String description;
    private String menu;
    private String openTime;
    private String closeTime;
    private String location;
    private String mapLink;
    private String openDate;
}