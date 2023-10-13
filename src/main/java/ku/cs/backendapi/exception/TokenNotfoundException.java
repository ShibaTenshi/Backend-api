package ku.cs.backendapi.exception;

public class TokenNotfoundException extends Exception{
    public TokenNotfoundException() {
        super("Token not found");
    }
}
