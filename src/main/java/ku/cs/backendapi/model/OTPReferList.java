package ku.cs.backendapi.model;

import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exception.OTPException;
import ku.cs.backendapi.exception.UserNotFoundException;

import java.time.LocalTime;
import java.util.*;

public class OTPReferList {
    private List<OTP> otpList;

    private static int referCount = 1000;

    public OTPReferList() {
        otpList = new ArrayList<>();
    }

    public void clearExpired() {
        LocalTime now = LocalTime.now();
        otpList.removeIf(otp -> otp.getExpire().isBefore(now));
    }

    public OTP getNewOtp(User user) {
        clearExpired();

        if (referCount >= 100000) referCount = 1000;
        String refer = String.valueOf(referCount++);

        String otpNumber = String.valueOf(new Random().nextInt(100000, 999999));

        OTP otp = new OTP(refer, LocalTime.now().plusMinutes(10), otpNumber, user);

        otpList.add(otp);
        return otp;
    }

    public boolean otpValidate(String otpRefer, String requestOtp) throws OTPException {
        Optional<OTP> optionalOTP = otpList.stream().filter(otp -> otp.getRefer().equals(otpRefer)).findFirst();
        if (optionalOTP.isPresent()) {
            if (optionalOTP.get().getOtp().equals(requestOtp)) {
                return true;
            }
            throw new OTPException("OTP Incorrect");
        }
        throw new OTPException("OTP Expired");
    }

    public OTP[] getAllOtp() {
        return otpList.toArray(new OTP[0]);
    }

    public User getUser(String refer) throws UserNotFoundException {
        Optional<OTP> optionalOTP = otpList.stream().filter(otp -> otp.getRefer().equals(refer)).findFirst();
        if(optionalOTP.isPresent()) {
            otpList.remove(optionalOTP.get());
            return optionalOTP.get().getUser();
        }
        throw new UserNotFoundException("User Not Found");
    }
}