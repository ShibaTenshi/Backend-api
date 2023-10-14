package ku.cs.backendapi.service;

import ku.cs.backendapi.common.Status;
import ku.cs.backendapi.entity.Booking;
import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.TableType;
import ku.cs.backendapi.exception.RestaurantNotFoundException;
import ku.cs.backendapi.exception.TableTypeNotFoundException;
import ku.cs.backendapi.exception.TokenNotfoundException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.BookingRequest;
import ku.cs.backendapi.repository.BookingRepository;
import ku.cs.backendapi.repository.CustomerRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import ku.cs.backendapi.repository.TableTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
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

    public void booking(UUID tokenId, UUID restaurantId, BookingRequest bookingRequest)
            throws UserNotFoundException,
            TokenNotfoundException,
            RestaurantNotFoundException,
            TableTypeNotFoundException {

        Customer customer = (Customer) tokenService.getUser(tokenId);

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        Restaurant restaurant = restaurantOptional.get();

        Optional<TableType> tableTypeOptional = tableTypeRepository.findById(bookingRequest.getIdTableType());
        if (tableTypeOptional.isEmpty()) {
            throw new TableTypeNotFoundException();
        }
        TableType tableType = tableTypeOptional.get();

        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.now());
        booking.setCustomer(customer);
        booking.setIdRestaurant(restaurant);
        booking.setIdTableType(tableType);
        booking.setStatus(Status.IN_PROGRESS);

        bookingRepository.save(booking);
    }
}
