package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.ManageOwnerProfileRequest;
import ku.cs.backendapi.model.OwnerProfileDTO;
import ku.cs.backendapi.service.ManageOwnerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "http://localhost:3000")
public class OwnerProfileController {
    @Autowired
    private ManageOwnerProfileService manageOwnerProfileService;

    @GetMapping("/profile")
    public OwnerProfileDTO getUser(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return manageOwnerProfileService.getUser(tokenId);
    }

    @PostMapping("/profile")
    public void changePassword(@RequestBody ManageOwnerProfileRequest request) throws UserNotFoundException, TokenException, AuthException {
        manageOwnerProfileService.changePassword(request);
    }
}
