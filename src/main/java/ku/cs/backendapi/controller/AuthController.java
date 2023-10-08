package ku.cs.backendapi.controller;

import ku.cs.backendapi.exeption.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AuthController {
    @Autowired
    AuthService service;

    @PostMapping("/login/customer")
    public Respond loginCustomer(@RequestBody Login login){
        return service.loginCustomer(login);
    }

    @PostMapping("/login/restaurant")
    public Respond loginRestaurant(@RequestBody Login login){
        return service.loginRestaurant(login);
    }

    @PostMapping("/logout/{token}")
    public void logout(@PathVariable String token) {
        service.removeToken(UUID.fromString(token));
    }
}
