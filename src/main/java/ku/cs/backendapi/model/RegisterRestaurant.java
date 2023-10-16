package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class RegisterRestaurant {
    private String category;
    private String imageLink;
    private String ownerName;
    private String username;
    private String email;
    private String restaurantName;
    private String description;
    private String menuLink;
    private String openTime;
    private String closeTime;
    private String location;
    private String mapLink;
    private String openDate;
}