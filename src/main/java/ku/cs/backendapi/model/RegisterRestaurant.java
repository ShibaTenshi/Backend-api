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

    private String location;

    private String menu;

    private Time openTime;

    private Time closeTime;

    private Category category;

}
