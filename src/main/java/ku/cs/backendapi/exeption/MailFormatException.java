package ku.cs.backendapi.exeption;
public class MailFormatException extends Exception{
    public MailFormatException() {
        super("Mail format error");
    }
}
