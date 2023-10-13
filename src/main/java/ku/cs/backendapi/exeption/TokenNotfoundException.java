package ku.cs.backendapi.exeption;

public class TokenNotfoundException extends Exception{
    public TokenNotfoundException() {
        super("Token not found");
    }
}
