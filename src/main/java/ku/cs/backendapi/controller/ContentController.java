package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.model.UnApprovedRestaurantTitle;
import ku.cs.backendapi.model.UnapprovedRestaurant;
import ku.cs.backendapi.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins = {"http:localhost:3000", "http://10.147.17.253:3000"})
public class ContentController {
    @Autowired
    ContentService service;

    @GetMapping("/category")
    public List<String> getAllCategory() {
        return service.getAllCategory();
    }

    @GetMapping("/unapproved/list")
    public List<UnApprovedRestaurantTitle> getAllUnapprovedRestaurantList() {
        return service.getUnapprovedRestaurantList();
    }

    @GetMapping("/unapproved")
    public UnapprovedRestaurant getUnapprovedRestaurant(@RequestParam String id) throws UserNotFoundException, AuthException {
        return service.getUnapprovedRestaurant(id);
    }
}
