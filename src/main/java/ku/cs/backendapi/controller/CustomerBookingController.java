package ku.cs.backendapi.controller;

import ku.cs.backendapi.common.BookingFind;
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

    @GetMapping("/history")
    public List<CustomerBooking> getAllCustomerBookingHistory(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return service.getCustomerCurrentBooking(tokenId, BookingFind.HISTORY);
    }

    @GetMapping("/inProgress")
    public List<CustomerBooking> getCustomerCurrentBooking(@RequestParam String tokenId) throws UserNotFoundException, TokenException {
        return service.getCustomerCurrentBooking(tokenId, BookingFind.IN_PROGRESS);
    }

    @PostMapping("/cancel")
    public void cancelBooking(@RequestParam String tokenId, String bookingId) throws UserNotFoundException, TokenException {
        service.cancelBooking(tokenId, bookingId);
    }
}
