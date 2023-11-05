package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class MailNewRestaurantBody {
    String email;
    String username;
    String restaurantName;
    String password;

    public MailNewRestaurantBody(String email, String username, String restaurantName, String password) {
        this.email = email;
        this.username = username;
        this.restaurantName = restaurantName;
        this.password = password;
    }
}
