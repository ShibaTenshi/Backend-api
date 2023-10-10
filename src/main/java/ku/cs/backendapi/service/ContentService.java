package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RespondCode;
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
            return new Respond(RespondCode.OK, customer.getImageLink());
        } catch (Exception e) {
            return new Respond(RespondCode.FAILED);
        }
    }

    public Respond getImageLinkRestaurant(UUID tokenId) {
        try {
            Restaurant restaurant = tokenService.getRestaurant(tokenId);
            return new Respond(RespondCode.OK, restaurant.getImageLink());
        } catch (Exception e) {
            return new Respond(RespondCode.FAILED);
        }
    }
}
