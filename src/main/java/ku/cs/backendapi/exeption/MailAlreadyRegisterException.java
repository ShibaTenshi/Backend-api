package ku.cs.backendapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class MailAlreadyRegisterException extends Exception{
    public MailAlreadyRegisterException() {
    }
}
