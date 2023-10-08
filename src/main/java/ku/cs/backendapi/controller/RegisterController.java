package ku.cs.backendapi.controller;

import ku.cs.backendapi.exeption.MailAlreadyRegisterException;
import ku.cs.backendapi.model.Register;
import ku.cs.backendapi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.OK)
    public void registerCustomer(@RequestBody Register customer) throws MailAlreadyRegisterException {
        service.createCustomer(customer);
    }
}
