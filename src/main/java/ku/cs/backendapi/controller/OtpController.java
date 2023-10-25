package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.OTPException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.OTP;
import ku.cs.backendapi.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@CrossOrigin
public class OtpController {

    @Autowired
    OTPService otpService;

    @GetMapping
    public String getNewOtp(@RequestParam String oldRefer) throws UserNotFoundException {
        return otpService.requestNewOtp(oldRefer);
    }

    @PostMapping
    public void validateOtp(@RequestParam String refer, String otpNumber) throws OTPException, UserNotFoundException {
        otpService.otpValidate(refer, otpNumber);
    }

    @GetMapping("/all")
    public OTP[] getAll() {
        return otpService.getAllOtp();
    }
}