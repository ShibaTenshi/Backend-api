package ku.cs.backendapi.exeption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "OTP Expired", code = HttpStatus.NOT_ACCEPTABLE)
public class OTPExpiredException extends Exception{
    public OTPExpiredException() {
    }
}
