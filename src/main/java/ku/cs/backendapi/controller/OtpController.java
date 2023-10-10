package ku.cs.backendapi.controller;

import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@CrossOrigin(origins = "http://localhost:3000")
public class OtpController {

    @Autowired
    OtpService otpService;

    @GetMapping
    public Respond getNewOtp() {
        return otpService.getNewOtp();
    }

    @PostMapping
    public Respond validateOtp(@RequestParam String refer, String otpNumber) {
        return otpService.otpValidate(refer, otpNumber);
    }

    @GetMapping("/all")
    public Respond getAll() {
        return otpService.getAllOtp();
    }
}
