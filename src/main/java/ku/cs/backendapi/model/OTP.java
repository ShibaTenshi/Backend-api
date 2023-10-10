package ku.cs.backendapi.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class OTP {
    private String refer;
    private LocalTime expire;
    private String otp;

    public OTP(String refer, LocalTime expire, String otp) {
        this.refer = refer;
        this.expire = expire;
        this.otp = otp;
    }
}
