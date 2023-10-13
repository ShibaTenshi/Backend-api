package ku.cs.backendapi.service;

import ku.cs.backendapi.common.OTPVerify;
import ku.cs.backendapi.common.RespondCode;
import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exeption.UserNotFoundException;
import ku.cs.backendapi.model.MailBody;
import ku.cs.backendapi.model.OTP;
import ku.cs.backendapi.model.OTPReferList;
import ku.cs.backendapi.model.Respond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OTPService {

    private OTPReferList otpReferList = new OTPReferList();

    private Respond getMethod(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Respond.class);
    }

    private Respond postMethod(String uri, Object object) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, object, Respond.class);
    }

    public void getNewOtp(User user) {
        OTP otp = otpReferList.getNewOtp(user);
        postMethod("http://localhost:5042/sendotp", new MailBody(user.getEmail(), user.getUsername(), otp.getOtp()));
    }

    public Respond requestNewOtp(String oldRefer) {
        return null;
    }

    public User getUser(String refer) throws UserNotFoundException {
        return otpReferList.getUser(refer);
    }

    public Respond otpValidate(String refer, String otpNumber) {
        OTPVerify code = otpReferList.otpValidate(refer, otpNumber);
        if (code == OTPVerify.EXPIRED || code == OTPVerify.INCORRECT) return new Respond(RespondCode.FAILED, code);
        return new Respond(RespondCode.OK, code);
    }

    public Respond getAllOtp() {
        return new Respond(RespondCode.OK, otpReferList.getAllOtp());
    }

    public Respond clearExpired() {
        otpReferList.clearExpired();
        return new Respond(RespondCode.OK);
    }

}