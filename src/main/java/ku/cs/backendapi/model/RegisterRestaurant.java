package ku.cs.backendapi.model;

import ku.cs.backendapi.entity.Category;
import lombok.Data;

import java.sql.Time;

@Data
public class RegisterRestaurant {
    private String name;

    private String username;

    private String email;

    private String password;

    private String restaurantName;

    private String description;

    private String imageLink;

    private String location;

    private String mapLink;

    private String menu;

    private String openTime;

    private String closeTime;

    private String openDate;

    private String category;

}
