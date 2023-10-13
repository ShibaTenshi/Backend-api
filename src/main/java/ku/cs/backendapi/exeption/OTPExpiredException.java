package ku.cs.backendapi.exeption;
public class OTPExpiredException extends Exception{
    public OTPExpiredException() {
        super("OTP expired");
    }
}
