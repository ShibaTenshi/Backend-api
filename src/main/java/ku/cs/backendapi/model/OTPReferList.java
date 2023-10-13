package ku.cs.backendapi.model;

import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exeption.OTPExpiredException;
import ku.cs.backendapi.exeption.OTPIncorrectException;
import ku.cs.backendapi.exeption.UserNotFoundException;

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

        if (referCount >= 10000) referCount = 1000;
        String refer = String.valueOf(referCount++);

        String otpNumber = String.valueOf(new Random().nextInt(100000, 999999));

        OTP otp = new OTP(refer, LocalTime.now().plusMinutes(10), otpNumber, user);

        otpList.add(otp);
        return otp;
    }

    public boolean otpValidate(String otpRefer, String requestOtp) throws OTPIncorrectException, OTPExpiredException {
        Optional<OTP> optionalOTP = otpList.stream().filter(otp -> otp.getRefer().equals(otpRefer)).findFirst();
        if (optionalOTP.isPresent()) {
            if (optionalOTP.get().getOtp().equals(requestOtp)) {
                return true;
            }
            throw new OTPIncorrectException();
        }
        throw new OTPExpiredException();
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
        throw new UserNotFoundException();
    }
}