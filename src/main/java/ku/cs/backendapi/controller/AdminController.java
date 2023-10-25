package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.UnApprovedRestaurantTitle;
import ku.cs.backendapi.model.UnapprovedRestaurant;
import ku.cs.backendapi.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ContentService service;

    @GetMapping("/unapproved/list")
    public List<UnApprovedRestaurantTitle> getAllUnapprovedRestaurantList(@RequestParam String tokenId) throws TokenException, AuthException {
        return service.getUnapprovedRestaurantList(tokenId);
    }

    @GetMapping("/unapproved")
    public UnapprovedRestaurant getUnapprovedRestaurant(@RequestParam String tokenId, String id) throws UserNotFoundException, AuthException, TokenException {
        return service.getUnapprovedRestaurant(tokenId, id);
    }
}