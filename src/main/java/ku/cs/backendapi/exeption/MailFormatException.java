package ku.cs.backendapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Email format error", code = HttpStatus.NOT_ACCEPTABLE)
public class MailFormatException extends Exception{
    public MailFormatException() {
    }
}
