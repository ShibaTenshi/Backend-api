package ku.cs.backendapi.controller;

import ku.cs.backendapi.entity.Category;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.model.*;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.UserNotFoundException;
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
    public List<SearchRestaurantDTO> getRestaurantPage(@RequestParam String query, int page) {
        return service.getAllRestaurantPageSearch(page, query);
    }

    @GetMapping("/restaurantPage/category")
    public List<SearchRestaurantDTO> getRestaurantPageCategory(@RequestParam String category, int page) {
        return service.getAllRestaurantPageSearchCategory(page, category);
    }

    @GetMapping("/restaurantInfo")
    public SelectedRestaurant getRestaurantInfo(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return service.getRestaurantInfo(tokenId);
    }

    @GetMapping("/viewRestaurant")
    public RestaurantViewInfoDTO viewRestaurant(@RequestParam String name) throws UserNotFoundException, TokenException {
        return service.viewRestaurantInfoCustomer(name);
    }


}
