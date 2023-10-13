package ku.cs.backendapi.exeption;

public class OTPIncorrectException extends Exception{
    public OTPIncorrectException() {
        super("OTP incorrect");
    }
}
