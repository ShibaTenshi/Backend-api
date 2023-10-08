package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exeption.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.repository.CustomerRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    TokenService tokenService;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public Respond loginCustomer(Login login){
        Customer customer = customerRepository.findByUsername(login.getUsername());
        if (customer == null) return new Respond("User not found");
        String token = tokenService.createToken(customer.getId()).toString();
        return new Respond("OK-" + token);
    }

    public Respond loginRestaurant(Login login){
        Restaurant restaurant = restaurantRepository.findByUsername(login.getUsername());
        if (restaurant == null) return new Respond("User not found");
        String token = tokenService.createToken(restaurant.getId()).toString();
        return new Respond("OK-" + token);
    }

    public void removeToken(UUID tokenID) {
        tokenService.removeToken(tokenID);
    }
}
