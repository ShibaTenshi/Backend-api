package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class SelectedRestaurant {
    private String restaurantName;
    private String description;
    private String openTime;
    private String closeTime;
    private String location;
    private String mapLink;
    private String openDate;
    private String category;
}
