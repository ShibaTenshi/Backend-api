package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.RestaurantBookingDTO;
import ku.cs.backendapi.service.RestaurantBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RestaurantBookingController {
    @Autowired
    RestaurantBookingService service;

    @GetMapping("/name")
    public String getRestaurantName(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return service.getRestaurantName(tokenId);
    }

    @GetMapping("/description")
    public String getRestaurantDescription(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return service.getRestaurantDescription(tokenId);
    }

    @GetMapping("/bookings")
    public List<RestaurantBookingDTO> getAllBooking(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return service.getAllBooking(tokenId);
    }
}
