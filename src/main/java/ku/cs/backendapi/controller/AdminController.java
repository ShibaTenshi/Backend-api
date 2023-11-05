package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.ApprovedDTO;
import ku.cs.backendapi.model.DeleteRestaurantDTO;
import ku.cs.backendapi.model.UnApprovedRestaurantTitle;
import ku.cs.backendapi.model.UnapprovedRestaurant;
import ku.cs.backendapi.service.AdminService;
import ku.cs.backendapi.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ContentService contentService;

    @Autowired
    AdminService adminService;

    @GetMapping("/unapproved/list")
    public List<UnApprovedRestaurantTitle> getAllUnapprovedRestaurantList(@RequestParam String tokenId) throws TokenException, AuthException {
        return contentService.getUnapprovedRestaurantList(tokenId);
    }

    @GetMapping("/unapproved")
    public UnapprovedRestaurant getUnapprovedRestaurant(@RequestParam String tokenId, String id) throws UserNotFoundException, AuthException, TokenException {
        return contentService.getUnapprovedRestaurant(tokenId, id);
    }

    @PostMapping("/discard")
    public void deleteRestaurant(@RequestBody DeleteRestaurantDTO deleteRestaurantDTO) throws UserNotFoundException, TokenException {
        adminService.deleteRestaurant(deleteRestaurantDTO);
    }

    @PostMapping("/approve")
    public void approvedRestaurant(@RequestBody ApprovedDTO approvedDTO) throws UserNotFoundException, TokenException {
        adminService.approveRestaurant(approvedDTO);
    }
}
