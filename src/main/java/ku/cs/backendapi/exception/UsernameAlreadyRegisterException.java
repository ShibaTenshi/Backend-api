package ku.cs.backendapi.exception;

public class UsernameAlreadyRegisterException extends Exception{
    public UsernameAlreadyRegisterException() {
        super("Username already exits");
    }
}
