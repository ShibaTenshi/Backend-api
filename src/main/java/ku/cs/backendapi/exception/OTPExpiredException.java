package ku.cs.backendapi.exception;
public class OTPExpiredException extends Exception{
    public OTPExpiredException() {
        super("OTP expired");
    }
}
