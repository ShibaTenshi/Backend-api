package ku.cs.backendapi.service;

import ku.cs.backendapi.common.Status;
import ku.cs.backendapi.entity.Booking;
import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.TableType;
import ku.cs.backendapi.exception.TableException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.BookingRequest;
import ku.cs.backendapi.repository.BookingRepository;
import ku.cs.backendapi.repository.CustomerRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import ku.cs.backendapi.repository.TableTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    TokenService tokenService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    TableTypeRepository tableTypeRepository;

    @Autowired
    BookingRepository bookingRepository;

    public void booking(BookingRequest bookingRequest)
            throws UserNotFoundException,
            TableException, TokenException {

        Customer customer = (Customer) tokenService.getUser(UUID.fromString(bookingRequest.getTokenId()));
        Restaurant restaurant = restaurantRepository.findByRestaurantName(bookingRequest.getRestaurantName());
        if (restaurant == null) {
            throw new UserNotFoundException("Restaurant Not Found");
        }

        TableType tableType = tableTypeRepository.findBySeatNumber(bookingRequest.getSeatNumber());
        if (tableType == null) {
            throw new TableException("Table Type Not Found");
        }

        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.parse(bookingRequest.getDateTime(), DateTimeFormatter.ISO_DATE_TIME));
        booking.setCustomer(customer);
        booking.setIdRestaurant(restaurant);
        booking.setIdTableType(tableType);
        booking.setStatus(Status.IN_PROGRESS);

        bookingRepository.save(booking);
    }
}
