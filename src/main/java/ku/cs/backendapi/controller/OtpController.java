package ku.cs.backendapi.controller;

import ku.cs.backendapi.model.Respond;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
@CrossOrigin(origins = "http://localhost:3000")
public class OtpController {

    @PostMapping("/{otp}")
    public Respond verify() {
        return null;
    }
}
