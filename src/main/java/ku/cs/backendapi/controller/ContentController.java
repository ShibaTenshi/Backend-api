package ku.cs.backendapi.controller;

import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentService service;

    @GetMapping("/customer/image/{tokenId}")
    public Respond getCustomerImage(@PathVariable String tokenId) {
        return service.getImageLinkCustomer(UUID.fromString(tokenId));
    }

    @GetMapping("/restaurant/image/{tokenId}")
    public Respond getRestaurantImage(@PathVariable String tokenId) {
        return service.getImageLinkRestaurant(UUID.fromString(tokenId));
    }
}
