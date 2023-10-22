package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;

    @PostMapping("/login/customer")
    public String loginCustomer(@RequestBody Login login) throws UserNotFoundException, AuthException {
        return service.loginCustomer(login);
    }

    @PostMapping("/login/restaurant")
    public String loginRestaurant(@RequestBody Login login) throws UserNotFoundException, AuthException {
        return service.loginRestaurant(login);
    }

    @PostMapping("/login/admin")
    public String loginAdmin(@RequestBody Login login) throws UserNotFoundException, AuthException {
        return service.loginAdmin(login);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String token) {
        service.removeToken(UUID.fromString(token));
    }
}
