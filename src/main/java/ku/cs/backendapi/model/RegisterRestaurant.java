package ku.cs.backendapi.model;

import lombok.Data;

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

    private String openTime;

    private String closeTime;

}
