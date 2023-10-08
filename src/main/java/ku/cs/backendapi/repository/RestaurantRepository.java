package ku.cs.backendapi.repository;

import ku.cs.backendapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Restaurant findByRestaurantName(String restaurantName);
    Restaurant findByUsername(String userName);
}
