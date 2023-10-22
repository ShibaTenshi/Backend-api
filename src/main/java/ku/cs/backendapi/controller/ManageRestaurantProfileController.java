package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.ManageOwnerProfileRequest;
import ku.cs.backendapi.model.ManageRestaurantInformationRequest;
import ku.cs.backendapi.model.OwnerProfileDTO;
import ku.cs.backendapi.model.RestaurantInformationDTO;
import ku.cs.backendapi.service.ManageRestaurantProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "http://localhost:3000")
public class ManageRestaurantProfileController {
    @Autowired
    private ManageRestaurantProfileService manageRestaurantProfileService;

    @GetMapping("/profile")
    public OwnerProfileDTO getOwner(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return manageRestaurantProfileService.getOwner(tokenId);
    }

    @PostMapping("/profile")
    public void changeOwnerPassword(@RequestBody ManageOwnerProfileRequest request) throws UserNotFoundException, TokenException, AuthException {
        manageRestaurantProfileService.changeOwnerPassword(request);
    }

    @GetMapping("/information")
    public RestaurantInformationDTO getRestaurant(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return manageRestaurantProfileService.getRestaurant(tokenId);
    }

    @PostMapping("/information")
    public void changeRestaurantInformation(@RequestBody ManageRestaurantInformationRequest request) throws UserNotFoundException, TokenException, AuthException {
        manageRestaurantProfileService.changeRestaurantInformation(request);
    }
}
