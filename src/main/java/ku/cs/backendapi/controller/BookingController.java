package ku.cs.backendapi.controller;

import ku.cs.backendapi.exception.BookingException;
import ku.cs.backendapi.exception.TableException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.BookingRequest;
import ku.cs.backendapi.service.BookingService;
import ku.cs.backendapi.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private TableService tableService;

    @PostMapping()
    public void booking(@RequestBody BookingRequest request) throws UserNotFoundException, TableException, TokenException, BookingException {
        bookingService.booking(request);
    }

    @GetMapping("/allTable")
    public List<String> getAllTable(@RequestParam String name) throws UserNotFoundException, TokenException {
        return tableService.getAllTableTypeRestaurant(name);
    }
}