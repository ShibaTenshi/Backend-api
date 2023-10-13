package ku.cs.backendapi.exeption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "OTP Incorrect", code = HttpStatus.NOT_ACCEPTABLE)
public class OTPIncorrectException extends Exception{
    public OTPIncorrectException() {
    }
}
