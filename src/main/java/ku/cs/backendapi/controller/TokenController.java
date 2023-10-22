package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/token")
@CrossOrigin
public class TokenController {
    @Autowired
    private TokenService service;

    @GetMapping
    public void tokenValidate(@RequestParam String token) throws TokenException {
        service.validateToken(UUID.fromString(token));
    }
}