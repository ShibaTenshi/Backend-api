package ku.cs.backendapi.repository;

import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.entity.Restaurant;
import org.hibernate.query.spi.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Restaurant findByRestaurantName(String restaurantName);
    Restaurant findByUsername(String userName);
    Restaurant findByEmail(String email);
    List<Restaurant> findAllByStatus(RestaurantStatus status);
    List<Restaurant> findAllByStatusAndRestaurantNameContainingIgnoreCase(RestaurantStatus status, String name);
}
