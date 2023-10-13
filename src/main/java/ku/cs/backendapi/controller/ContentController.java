package ku.cs.backendapi.controller;

import ku.cs.backendapi.exeption.TokenNotfoundException;
import ku.cs.backendapi.exeption.UserNotFoundException;
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

    @GetMapping("/customer/image")
    public String getCustomerImage(@RequestParam String token) throws UserNotFoundException, TokenNotfoundException {
        return service.getImageLinkCustomer(UUID.fromString(token));
    }
}
