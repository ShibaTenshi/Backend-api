package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Booking;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.RestaurantBookingDTO;
import ku.cs.backendapi.repository.BookingRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantBookingService {
    @Autowired
    TokenService tokenService;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public String getRestaurantName(String tokenId) throws UserNotFoundException, TokenException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(tokenId));
        if (restaurant != null) {
            return restaurant.getRestaurantName();
        }
        throw new UserNotFoundException("Restaurant not found");
    }

    public String getRestaurantDescription(String tokenId) throws UserNotFoundException, TokenException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(tokenId));
        if (restaurant != null) {
            return restaurant.getDescription();
        }
        throw new UserNotFoundException("Restaurant not found");
    }

    public List<RestaurantBookingDTO> getAllBooking(String tokenId) throws UserNotFoundException, TokenException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(tokenId));

        if (restaurant != null) {
            List<RestaurantBookingDTO> restaurantBookingDTOList = new ArrayList<>();
            List<Booking> bookingList = bookingRepository.findAllBookingByRestaurantId(restaurant.getId());
            for (Booking booking : bookingList) {
                RestaurantBookingDTO dto = new RestaurantBookingDTO();
                dto.setDate(formatDate(booking.getDateTime()));
                dto.setTime(formatTime(booking.getDateTime()));
                dto.setName(booking.getCustomer().getName());
                dto.setSeatNumber(booking.getIdTableType().getSeatNumber());

                restaurantBookingDTOList.add(dto);
            }

            return restaurantBookingDTOList;
        }
        throw new UserNotFoundException("Restaurant not found");
    }

    private static String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTime.format(dateFormatter);
    }

    private static String formatTime(LocalDateTime dateTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        return dateTime.format(timeFormatter);
    }
}
