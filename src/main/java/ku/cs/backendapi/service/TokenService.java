package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exeption.TokenNotfoundException;
import ku.cs.backendapi.exeption.UserNotFoundException;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.model.TokenList;
import ku.cs.backendapi.repository.CustomerRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    private final TokenList tokenList = new TokenList();

    public UUID createToken(UUID userId) {
        UUID tokenId = UUID.randomUUID();
        tokenList.addToken(tokenId, userId);
        return tokenId;
    }

    public Respond validateToken(UUID tokenId) {
        if (tokenList.isTokenContain(tokenId)) {
            return new Respond("OK");
        }
        return new Respond("Failed");
    }

    public Customer getCustomer(UUID tokenId) throws TokenNotfoundException, UserNotFoundException {
        Optional<Customer> customer = customerRepository.findById(tokenList.getUserId(tokenId));
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new UserNotFoundException();
    }

    public Restaurant getRestaurant(UUID tokenId) throws TokenNotfoundException, UserNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(tokenList.getUserId(tokenId));
        if (restaurant.isPresent()) {
            return restaurant.get();
        }
        throw new UserNotFoundException();
    }

    public void removeToken(UUID tokenId) {
        tokenList.removeToken(tokenId);
    }

    public String getTokenMap() {
        return tokenList.getMap();
    }
}
