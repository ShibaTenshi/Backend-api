package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.ManageRestaurantInformationRequest;
import ku.cs.backendapi.model.RestaurantInformationDTO;
import ku.cs.backendapi.service.ManageRestaurantInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantInformationController {
    @Autowired
    ManageRestaurantInformationService manageRestaurantInformationService;

    @GetMapping("/information")
    public RestaurantInformationDTO getRestaurant(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return manageRestaurantInformationService.getRestaurant(tokenId);
    }

    @PostMapping("/information")
    public void changeInformation(@RequestBody ManageRestaurantInformationRequest request) throws UserNotFoundException, TokenException, AuthException {
        manageRestaurantInformationService.changeInformation(request);
    }
}
