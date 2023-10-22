package ku.cs.backendapi.controller;

import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.model.UnApprovedRestaurantTitle;
import ku.cs.backendapi.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins = "http://localhost:3000")
public class ContentController {
    @Autowired
    ContentService service;

    @GetMapping("/category")
    public List<String> getAllCategory() {
        return service.getAllCategory();
    }

    @GetMapping("/unapproved")
    public List<UnApprovedRestaurantTitle> getAllUnapprovedRestaurant() {
        return service.getUnapprovedRestaurant();
    }
}
