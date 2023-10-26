package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.CustomerBooking;
import ku.cs.backendapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myBooking")
@CrossOrigin
public class CustomerBookingController {
    @Autowired
    BookingService service;

    @GetMapping
    public List<CustomerBooking> getAllCustomerBookingHistory(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return service.getAllCustomerBookingHistory(tokenId);
    }

    @PostMapping("/cancel")
    public void cancelBooking(@RequestParam String bookingId) throws UserNotFoundException {
        service.cancelBooking(bookingId);
    }
}
