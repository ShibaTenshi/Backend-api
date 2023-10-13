package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.PasswordNotCorrectException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;

    @PostMapping("/login/customer")
    public String loginCustomer(@RequestBody Login login) throws UserNotFoundException, PasswordNotCorrectException {
        return service.loginCustomer(login);
    }

    @PostMapping("/login/restaurant")
    public String loginRestaurant(@RequestBody Login login) throws UserNotFoundException, PasswordNotCorrectException {
        return service.loginRestaurant(login);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String token) {
        service.removeToken(UUID.fromString(token));
    }
}
