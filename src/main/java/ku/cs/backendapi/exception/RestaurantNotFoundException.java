package ku.cs.backendapi.exception;

public class RestaurantNotFoundException extends Exception {
    public RestaurantNotFoundException() {
        super("Restaurant not found");
    }
}
