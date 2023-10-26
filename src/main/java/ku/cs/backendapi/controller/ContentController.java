package ku.cs.backendapi.controller;

import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.model.UnApprovedRestaurantTitle;
import ku.cs.backendapi.model.UnapprovedRestaurant;
import ku.cs.backendapi.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/content")
@CrossOrigin
public class ContentController {
    @Autowired
    ContentService service;

    @GetMapping("/category")
    public List<String> getAllCategory() {
        return service.getAllCategory();
    }

    public Optional<Restaurant> getRestaurantInfo(@RequestParam String tokenId, String id) throws UserNotFoundException{
        return service.getRestaurantInfo(tokenId, id);
    }
}
