package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.CustomerProfileDTO;
import ku.cs.backendapi.model.ManageProfileCustomer;
import ku.cs.backendapi.service.ManageProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/customer")
public class ManageProfileController {
    @Autowired
    private ManageProfileService manageProfileService;

    @GetMapping("/profile")
    public CustomerProfileDTO getUser(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return manageProfileService.getUser(tokenId);
    }

    @PostMapping("/profile/changePassword")
    public void changePassword(@RequestParam String tokenId, String oldPassword, String newPassword) throws UserNotFoundException, TokenException, AuthException {
        manageProfileService.changePassword(tokenId, oldPassword, newPassword);
    }
}
