package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.TokenNotfoundException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins = "http://localhost:3000")
public class ContentController {
    @Autowired
    ContentService service;

    @GetMapping("/image")
    public String getUserImage(@RequestParam String token) throws UserNotFoundException, TokenNotfoundException {
        return service.getImageLinkCustomer(UUID.fromString(token));
    }
}
