package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TableException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.*;
import ku.cs.backendapi.service.ManageRestaurantProfileService;
import ku.cs.backendapi.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restaurant")
public class ManageRestaurantProfileController {
    @Autowired
    private ManageRestaurantProfileService manageRestaurantProfileService;
    @Autowired
    private TableService tableService;

    @GetMapping("/profile")
    public OwnerProfileDTO getOwner(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return manageRestaurantProfileService.getOwner(tokenId);
    }

    @PostMapping("/profile/changePassword")
    public void changeOwnerPassword(@RequestParam String tokenId, String oldPassword, String newPassword) throws UserNotFoundException, TokenException, AuthException {
        manageRestaurantProfileService.changeOwnerPassword(tokenId, oldPassword, newPassword);
    }

    @GetMapping("/information")
    public RestaurantInformationDTO getRestaurant(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return manageRestaurantProfileService.getRestaurant(tokenId);
    }

    @PostMapping("/information/changeInformation")
    public void changeRestaurantInformation(@RequestBody ManageRestaurantInformationRequest request) throws UserNotFoundException, TokenException, AuthException {
        manageRestaurantProfileService.changeRestaurantInformation(request);
    }

    @GetMapping("/tableType")
    public List<TableTypeRestaurantDTO> getRestaurantTable(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return tableService.getTableRestaurant(tokenId);
    }

    @PostMapping("/tableType/set")
    public void setRestaurantTable(@RequestParam String tokenId, String numSeat, String numTable) throws UserNotFoundException, TokenException, TableException {
        tableService.setTable(tokenId, numSeat, numTable);
    }
}
