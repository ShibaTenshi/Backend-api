package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class OwnerProfileDTO {
    private String ownerName;
    private String username;
    private String email;
    private String restaurantName;
}
