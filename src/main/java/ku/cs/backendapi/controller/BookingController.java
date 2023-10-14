package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.RestaurantNotFoundException;
import ku.cs.backendapi.exception.TableTypeNotFoundException;
import ku.cs.backendapi.exception.TokenNotfoundException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.BookingRequest;
import ku.cs.backendapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/restaurant/{restaurantId}")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public void booking(@RequestParam String token,
                        @PathVariable UUID restaurantId,
                        BookingRequest bookingRequest)
            throws UserNotFoundException, TokenNotfoundException, RestaurantNotFoundException, TableTypeNotFoundException {
        bookingService.booking(UUID.fromString(token), restaurantId, bookingRequest);
    }
}
