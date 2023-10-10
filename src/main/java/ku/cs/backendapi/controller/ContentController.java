package ku.cs.backendapi.controller;

import ku.cs.backendapi.model.Respond;
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
    public Respond getCustomerImage(@RequestParam String token) {
        return service.getImageLinkCustomer(UUID.fromString(token));
    }

    @GetMapping("/restaurant/image")
    public Respond getRestaurantImage(@RequestParam String token) {
        return service.getImageLinkRestaurant(UUID.fromString(token));
    }
}
