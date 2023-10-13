package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.TokenNotfoundException;
import ku.cs.backendapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "http://localhost:3000")
public class TokenController {
    @Autowired
    private TokenService service;

    @GetMapping
    public void tokenValidate(@RequestParam String token) throws TokenNotfoundException {
        service.validateToken(UUID.fromString(token));
    }

    @GetMapping("/all")
    public String getAll() {
        return service.getTokenMap();
    }
}