package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class MailDeleteRestaurantBody {
    private String email;
    private String username;
    private String restaurantName;
    private String reason;

    public MailDeleteRestaurantBody(String email, String username, String restaurantName, String reason) {
        this.email = email;
        this.username = username;
        this.restaurantName = restaurantName;
        this.reason = reason;
    }
}
