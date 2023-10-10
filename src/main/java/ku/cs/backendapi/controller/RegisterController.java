package ku.cs.backendapi.controller;

import ku.cs.backendapi.model.RegisterCustomer;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping("/register/customer")
    public Respond registerCustomer(@RequestBody RegisterCustomer customer){
        return service.createCustomer(customer);
    }

    @PostMapping("/register/restaurant")
    public Respond registerRestaurant(@RequestBody RegisterRestaurant restaurant){
        return service.createRestaurant(restaurant);
    }
}
