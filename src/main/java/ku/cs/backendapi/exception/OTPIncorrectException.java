package ku.cs.backendapi.exception;

public class OTPIncorrectException extends Exception{
    public OTPIncorrectException() {
        super("OTP incorrect");
    }
}
