package ku.cs.backendapi.model;

import ku.cs.backendapi.entity.Category;
import lombok.Data;

@Data
public class RestaurantInformationDTO {
    private Category category;
    private String restaurantName;
    private String description;
    private String openTime;
    private String closeTime;
    private String location;
    private String mapLink;
    private String openDate;
}
