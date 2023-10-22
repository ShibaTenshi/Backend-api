package ku.cs.backendapi.controller;

import jakarta.security.auth.message.AuthException;
import ku.cs.backendapi.exception.MailException;
import ku.cs.backendapi.model.RegisterCustomer;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http:localhost:3000", "http://10.147.17.253:3000"})
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping("/customer")
    public String registerCustomer(@RequestBody RegisterCustomer customer) throws MailException, AuthException {
        return service.createCustomer(customer);
    }

    @PostMapping("/restaurant")
    public String registerRestaurant(@RequestBody RegisterRestaurant restaurant) throws AuthException, MailException {
        return service.createRestaurant(restaurant);
    }

    @PostMapping("/admin")
    public void registerAdmin() {
        service.addAdmin();
    }
}