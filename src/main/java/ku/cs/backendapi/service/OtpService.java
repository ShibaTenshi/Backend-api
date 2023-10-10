package ku.cs.backendapi.service;

import ku.cs.backendapi.common.OTPVerify;
import ku.cs.backendapi.common.RespondCode;
import ku.cs.backendapi.model.OTP;
import ku.cs.backendapi.model.OTPReferList;
import ku.cs.backendapi.model.Respond;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OtpService {
    private OTPReferList otpReferList = new OTPReferList();

    private Respond getMethod(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Respond.class);
    }

    private Respond postMethod(String uri, Object object) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, object, Respond.class);
    }

    public Respond getNewOtp() {
        OTP otp = otpReferList.getNewOtp();
        return new Respond(RespondCode.OK, otp);
    }

    public Respond otpValidate(String refer, String otpNumber) {
        OTPVerify code = otpReferList.otpValidate(refer, otpNumber);
        if(code == OTPVerify.EXPIRED || code == OTPVerify.INCORRECT) return new Respond(RespondCode.FAILED, code);
        return new Respond(RespondCode.OK, code);
    }

    public Respond getAllOtp() {
        return new Respond(RespondCode.OK, otpReferList.getAllOtp());
    }

}