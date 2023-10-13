package ku.cs.backendapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Password not correct", code = HttpStatus.NOT_ACCEPTABLE)
public class PasswordNotCorrectException extends Exception{
    public PasswordNotCorrectException() {
    }
}
