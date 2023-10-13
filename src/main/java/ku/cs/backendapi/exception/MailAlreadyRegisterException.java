package ku.cs.backendapi.exception;

public class MailAlreadyRegisterException extends Exception{
    public MailAlreadyRegisterException() {
        super("Mail already exits");
    }
}
