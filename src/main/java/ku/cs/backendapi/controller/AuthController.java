package ku.cs.backendapi.controller;

import ku.cs.backendapi.exeption.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AuthController {
    @Autowired
    AuthService service;

    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public String login(@RequestBody Login login) throws UserNotFoundException {
        return service.login(login);
    }

    @PostMapping("/logout/{token}")
    public void logout(@PathVariable String token) {
        service.removeToken(UUID.fromString(token));
    }
}
