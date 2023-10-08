package ku.cs.backendapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class TokenNotfoundException extends Exception{
    public TokenNotfoundException() {
        super();
    }
}
