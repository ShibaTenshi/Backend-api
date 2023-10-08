package ku.cs.backendapi.repository;

import ku.cs.backendapi.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository<UUID> extends JpaRepository<Booking, UUID> {
}
