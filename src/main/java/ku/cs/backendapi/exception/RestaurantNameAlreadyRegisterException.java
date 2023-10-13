package ku.cs.backendapi.exception;

public class RestaurantNameAlreadyRegisterException extends Exception{
    public RestaurantNameAlreadyRegisterException() {
        super("Restaurant name already exits");
    }
}
