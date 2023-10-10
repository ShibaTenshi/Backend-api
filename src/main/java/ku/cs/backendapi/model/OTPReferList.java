package ku.cs.backendapi.model;

import ku.cs.backendapi.common.OTPVerify;
import org.modelmapper.internal.bytebuddy.utility.RandomString;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OTPReferList {
    private Map<String, OTP> otpMap;

    public OTPReferList() {
        otpMap = new HashMap<>();
    }

    private void clearExpired() {
        for(OTP otp : otpMap.values()) {
            if(otp.getExpire().isBefore(LocalTime.now())) {
                otpMap.remove(otp.getRefer());
            }
        }
    }

    public OTP getNewOtp() {
        clearExpired();

        String refer = RandomString.make();
        while (otpMap.containsKey(refer)) refer = RandomString.make();

        String otpNumber = String.valueOf(new Random().nextInt(100000, 999999));

        OTP otp = new OTP(refer, LocalTime.now().plusMinutes(10), otpNumber);

        otpMap.put(refer, otp);
        return otp;
    }

    public OTPVerify otpValidate(String otpRefer, String requestOtp) {
        if (!otpMap.containsKey(otpRefer)) return OTPVerify.EXPIRED;
        OTP otp = otpMap.get(otpRefer);
        if (otp.getExpire().isBefore(LocalTime.now())) {
            otpMap.remove(otpRefer);
            return OTPVerify.EXPIRED;
        }
        if (otp.getOtp().equals(requestOtp)) {
            otpMap.remove(otpRefer);
            return OTPVerify.CORRECT;
        }
        return OTPVerify.INCORRECT;
    }

    public OTP[] getAllOtp() {
        return otpMap.values().toArray(new OTP[0]);
    }
}
