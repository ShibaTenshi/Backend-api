package ku.cs.backendapi.controller;

import ku.cs.backendapi.exeption.MailAlreadyRegisterException;
import ku.cs.backendapi.exeption.MailFormatException;
import ku.cs.backendapi.exeption.RestaurantNameAlreadyRegisterException;
import ku.cs.backendapi.exeption.UsernameAlreadyRegisterException;
import ku.cs.backendapi.model.RegisterCustomer;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping("/customer")
    public String registerCustomer(@RequestBody RegisterCustomer customer) throws MailAlreadyRegisterException, UsernameAlreadyRegisterException, MailFormatException {
        return service.createCustomer(customer);
    }

    @PostMapping("/restaurant")
    public String registerRestaurant(@RequestBody RegisterRestaurant restaurant) throws MailAlreadyRegisterException, UsernameAlreadyRegisterException, RestaurantNameAlreadyRegisterException, MailFormatException {
        return service.createRestaurant(restaurant);
    }
}