package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.model.Respond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentService {

    @Autowired
    TokenService tokenService;

    public Respond getImageLinkCustomer(UUID tokenId) {
        try {
            Customer customer = tokenService.getCustomer(tokenId);
            return new Respond(200, customer.getImageLink());
        } catch (Exception e) {
            return new Respond(404);
        }
    }

    public Respond getImageLinkRestaurant(UUID tokenId) {
        try {
            Restaurant restaurant = tokenService.getRestaurant(tokenId);
            return new Respond(200, restaurant.getImageLink());
        } catch (Exception e) {
            return new Respond(404);
        }
    }
}
