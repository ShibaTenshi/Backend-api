package ku.cs.backendapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Email already exits", code = HttpStatus.NOT_ACCEPTABLE)
public class MailAlreadyRegisterException extends Exception{
    public MailAlreadyRegisterException() {
    }
}
