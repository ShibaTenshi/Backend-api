package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class UnApprovedRestaurantTitle {
    private String restaurantName;
    private String category;
    private String dateAdded;
}