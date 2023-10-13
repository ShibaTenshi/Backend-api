package ku.cs.backendapi.exeption;

public class UsernameAlreadyRegisterException extends Exception{
    public UsernameAlreadyRegisterException() {
        super("Username already exits");
    }
}
