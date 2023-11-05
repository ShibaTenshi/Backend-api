package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class MailBody {
    private String email;
    private String username;
    private String otp;
    private String refer;

    public MailBody(String email, String username, String otp, String refer) {
        this.email = email;
        this.username = username;
        this.otp = otp;
        this.refer = refer;
    }
}
