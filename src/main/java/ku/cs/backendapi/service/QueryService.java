package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Respond getRestaurant(String name) {
        Restaurant restaurant = restaurantRepository.findByRestaurantName(name);
        if (restaurant == null) return new Respond(404, "Restaurant not found");
        return new Respond(200, restaurantRepository.findByRestaurantName(name));
    }
}
