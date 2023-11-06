package ku.cs.backendapi.repository;

import ku.cs.backendapi.common.Status;
import ku.cs.backendapi.entity.Booking;
import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.RestaurantTableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAllByRestaurant(Restaurant restaurant);

    List<Booking> findAllByRestaurantAndStatus(Restaurant restaurant, Status status);

    List<Booking> findAllByCustomer_Id(UUID id);

    List<Booking> findAllByCustomerAndStatus(Customer customer, Status status);

    Booking findByIdBooking(UUID idBooking);

    List<Booking> findByRestaurantAndRestaurantTableTypeAndStatus(Restaurant restaurant, RestaurantTableType restaurantTableType, Status status);

    @Query("select count(a) from Booking a where a.restaurant.id = :idRestaurant and a.restaurantTableType.id = :idTableType and function('date_format',a.dateTime,'%d-%m-%Y') = :date")
    int query(UUID idRestaurant, UUID idTableType, String date);

    void deleteAllByCustomer_Id(UUID customer);
    void deleteAllByRestaurant_Id(UUID restaurant);
}

