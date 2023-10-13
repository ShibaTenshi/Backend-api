package ku.cs.backendapi.exeption;

public class PasswordNotCorrectException extends Exception{
    public PasswordNotCorrectException() {
        super("Password incorrect");
    }
}
