package ku.cs.backendapi.controller;

import ku.cs.backendapi.exeption.TokenNotfoundException;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "http://localhost:3000")
public class TokenController {
    @Autowired
    private TokenService service;

    @GetMapping
    public Respond tokenValidate(@RequestParam String token){
        return service.validateToken(UUID.fromString(token));
    }

    @GetMapping("/all")
    public Respond getAll() {
        return service.getTokenMap();
    }
}