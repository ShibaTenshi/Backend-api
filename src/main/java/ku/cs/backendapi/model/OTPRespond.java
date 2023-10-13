package ku.cs.backendapi.model;

import ku.cs.backendapi.entity.User;
import lombok.Data;

import java.time.LocalTime;

@Data
public class OTPRespond {
    private String refer;
    private LocalTime expire;
    private String otp;

    public OTPRespond(String refer, LocalTime expire, String otp) {
        this.refer = refer;
        this.expire = expire;
        this.otp = otp;
    }
}