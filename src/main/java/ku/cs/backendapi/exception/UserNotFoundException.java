package ku.cs.backendapi.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User not found");
    }
}
