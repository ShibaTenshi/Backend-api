package ku.cs.backendapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Token not found", code = HttpStatus.NOT_ACCEPTABLE)
public class TokenNotfoundException extends Exception{
    public TokenNotfoundException() {
        super();
    }
}
