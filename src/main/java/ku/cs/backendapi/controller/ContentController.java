package ku.cs.backendapi.controller;

import ku.cs.backendapi.entity.Category;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.model.SearchRestaurantDTO;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.model.SelectedRestaurant;
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

    @GetMapping("/restaurantPage")
    public List<SearchRestaurantDTO> getCategory(@RequestParam String query, int page) {
        return service.getAllRestaurantPageSearch(page, query);
    }

    @GetMapping("/restaurantInfo")
    public SelectedRestaurant getRestaurantInfo(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return service.getRestaurantInfo(tokenId);
    }
}
