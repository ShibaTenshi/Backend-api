package ku.cs.backendapi.model;

import ku.cs.backendapi.entity.User;
import lombok.Data;

import java.time.LocalTime;

@Data
public class OTP {
    private String refer;
    private LocalTime expire;
    private String otp;
    private User user;

    public OTP(String refer, LocalTime expire, String otp, User user) {
        this.refer = refer;
        this.expire = expire;
        this.otp = otp;
        this.user = user;
    }
}