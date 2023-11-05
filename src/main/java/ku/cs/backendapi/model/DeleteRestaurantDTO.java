package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class DeleteRestaurantDTO {
    String tokenId;
    String idRestaurant;
    String reason;
}
