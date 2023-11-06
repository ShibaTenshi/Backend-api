package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class SearchRestaurantDTO {
    private String category;
    private String restaurantName;
}