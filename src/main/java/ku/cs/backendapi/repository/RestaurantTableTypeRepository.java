package ku.cs.backendapi.repository;

import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.RestaurantTableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantTableTypeRepository extends JpaRepository<RestaurantTableType, UUID> {

    List<RestaurantTableType> findByRestaurant(Restaurant restaurant);

    RestaurantTableType findByRestaurantAndTableType_SeatNumber(Restaurant restaurant, int seat);

}
