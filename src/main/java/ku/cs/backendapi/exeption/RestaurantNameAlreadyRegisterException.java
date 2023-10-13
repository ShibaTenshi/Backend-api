package ku.cs.backendapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Restaurant name already exits", code = HttpStatus.NOT_ACCEPTABLE)
public class RestaurantNameAlreadyRegisterException extends Exception{
    public RestaurantNameAlreadyRegisterException() {
    }
}
