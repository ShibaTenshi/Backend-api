package ku.cs.backendapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Username already exits", code = HttpStatus.NOT_ACCEPTABLE)
public class UsernameAlreadyRegisterException extends Exception{
    public UsernameAlreadyRegisterException() {
    }
}
