package ku.cs.backendapi.exception;

public class PasswordNotCorrectException extends Exception{
    public PasswordNotCorrectException() {
        super("Password incorrect");
    }
}
