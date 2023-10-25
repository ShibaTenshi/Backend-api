package ku.cs.backendapi.repository;

import ku.cs.backendapi.entity.Booking;
import ku.cs.backendapi.model.RestaurantBookingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAllBookingByRestaurantId(UUID id);
}
