package ku.cs.backendapi.service;

import ku.cs.backendapi.common.Status;
import ku.cs.backendapi.entity.*;
import ku.cs.backendapi.exception.BookingException;
import ku.cs.backendapi.exception.TableException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.BookingRequest;
import ku.cs.backendapi.model.CustomerBooking;
import ku.cs.backendapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    RestaurantTableTypeRepository restaurantTableTypeRepository;

    public void booking(BookingRequest bookingRequest)
            throws UserNotFoundException,
            TableException, TokenException, BookingException {

        Customer customer = (Customer) tokenService.getUser(UUID.fromString(bookingRequest.getTokenId()));
        Restaurant restaurant = restaurantRepository.findByRestaurantName(bookingRequest.getRestaurantName());
        if (restaurant == null) {
            throw new UserNotFoundException("Restaurant Not Found");
        }

        RestaurantTableType restaurantTableType = restaurantTableTypeRepository.findByRestaurantAndTableType_SeatNumber(restaurant, Integer.parseInt(bookingRequest.getNumSeat()));
        if (restaurantTableType == null) {
            throw new TableException("Table Type Not Found");
        }


        String dateTime = bookingRequest.getDayTh() + "/" + bookingRequest.getMonth() + "/" + bookingRequest.getYear() + " " + bookingRequest.getTime();
        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        booking.setCustomer(customer);
        booking.setRestaurant(restaurant);
        booking.setRestaurantTableType(restaurantTableType);
        booking.setIdBooking(UUID.randomUUID());
        booking.setStatus(Status.IN_PROGRESS);

        String dateCheck = bookingRequest.getDayTh() + "-" + bookingRequest.getMonth() + "-" + bookingRequest.getYear();
        System.out.println("In " + dateCheck);
        int a = bookingRepository.query(restaurant.getId(), restaurantTableType.getId(), dateCheck);
        System.out.println("Out " + a);
        if (a >= restaurantTableType.getNumOfTable()) {
            throw new TableException("This table is full");
        }

        //check open date
        if(restaurant.getOpenDate().charAt(Integer.parseInt(bookingRequest.getDayOfWeek())) == '0') {
            throw new BookingException("Restaurant close at this day");
        }

        //check open close
        LocalTime localTimeCheck = LocalTime.of(booking.getDateTime().getHour(), booking.getDateTime().getMinute());
        LocalTime localTimeRestaurantOpen = LocalTime.parse(restaurant.getOpenTime());
        LocalTime localTimeRestaurantClose = LocalTime.parse(restaurant.getCloseTime());

        if(localTimeCheck.isBefore(localTimeRestaurantOpen) || localTimeCheck.isAfter(localTimeRestaurantClose)) throw new BookingException("Restaurant close at this time");

        bookingRepository.save(booking);
    }

    public List<CustomerBooking> getAllCustomerBookingHistory(String tokenId) throws UserNotFoundException, TokenException {
        Customer customer = (Customer) tokenService.getUser(UUID.fromString(tokenId));

        if (customer != null) {
            List<CustomerBooking> customerBookingList = new ArrayList<>();
            List<Booking> bookingList = bookingRepository.findAllByCustomer_Id(customer.getId());
            for (Booking booking : bookingList) {
                if (booking.getStatus() == Status.COMPLETED || booking.getStatus() == Status.CANCELED) {
                    CustomerBooking dto = new CustomerBooking();
                    dto.setBookingId(String.valueOf(booking.getIdBooking()));
                    dto.setRestaurantName(booking.getRestaurant().getRestaurantName());
                    dto.setDescription(booking.getRestaurant().getDescription());
                    dto.setStatus(String.valueOf(booking.getStatus()));
                    dto.setDate(formatDate(booking.getDateTime()));
                    dto.setTime(formatTime(booking.getDateTime()));

                    customerBookingList.add(dto);
                }
            }
            return customerBookingList;
        }
        throw new UserNotFoundException("User not found");
    }

    public List<CustomerBooking> getCustomerCurrentBooking(String tokenId) throws UserNotFoundException, TokenException {
        Customer customer = (Customer) tokenService.getUser(UUID.fromString(tokenId));

        if (customer != null) {
            List<CustomerBooking> customerBookingList = new ArrayList<>();
            List<Booking> bookingList = bookingRepository.findAllByCustomer_Id(customer.getId());
            for (Booking booking : bookingList) {
                if (booking.getStatus() == Status.IN_PROGRESS ) {
                    CustomerBooking dto = new CustomerBooking();
                    dto.setBookingId(String.valueOf(booking.getIdBooking()));
                    dto.setRestaurantName(booking.getIdRestaurant().getRestaurantName());
                    dto.setDescription(booking.getIdRestaurant().getDescription());
                    dto.setStatus(String.valueOf(booking.getStatus()));
                    dto.setDate(formatDate(booking.getDateTime()));
                    dto.setTime(formatTime(booking.getDateTime()));

                    customerBookingList.add(dto);
                }
            }
            return customerBookingList;
        }
        throw new UserNotFoundException("User not found");
    }

    public void cancelBooking(String bookingId) throws UserNotFoundException {
        Booking booking = bookingRepository.findByIdBooking(UUID.fromString(bookingId));

        if (booking != null) {
            booking.setStatus(Status.CANCELED);
            bookingRepository.save(booking);
        } else throw new UserNotFoundException("Booking not found");
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
