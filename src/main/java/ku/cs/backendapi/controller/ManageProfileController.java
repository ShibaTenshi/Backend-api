package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.TokenNotfoundException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.CustomerProfileDTO;
import ku.cs.backendapi.model.ManageProfileCustomer;
import ku.cs.backendapi.service.ManageProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class ManageProfileController {
    @Autowired
    private ManageProfileService manageProfileService;

    @GetMapping
    public CustomerProfileDTO getUser(@RequestParam String tokenId) throws UserNotFoundException, TokenNotfoundException {
        return manageProfileService.getUser(tokenId);
    }

    @PostMapping("/customerManageProfile")
    public void changePassword(@RequestParam String tokenId,
                                      @RequestBody ManageProfileCustomer manageProfileCustomer) throws UserNotFoundException, TokenNotfoundException {
        manageProfileService.changePassword(tokenId, manageProfileCustomer);
    }

    @PostMapping("/customerManageProfile")
    public void changeProfilePicture(@RequestParam String tokenId,
                               @RequestBody ManageProfileCustomer manageProfileCustomer) throws UserNotFoundException, TokenNotfoundException {
        manageProfileService.changeProfilePicture(tokenId, manageProfileCustomer);
    }

}
