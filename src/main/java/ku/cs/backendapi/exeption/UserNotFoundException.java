package ku.cs.backendapi.exeption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "User not found", code = HttpStatus.NOT_ACCEPTABLE)
public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
    }
}
