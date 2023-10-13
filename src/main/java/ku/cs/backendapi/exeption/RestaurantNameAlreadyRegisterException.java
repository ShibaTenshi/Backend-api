package ku.cs.backendapi.exeption;

public class RestaurantNameAlreadyRegisterException extends Exception{
    public RestaurantNameAlreadyRegisterException() {
        super("Restaurant name already exits");
    }
}
