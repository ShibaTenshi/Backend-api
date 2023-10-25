package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Admin;
import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exception.TableException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.TokenList;
import ku.cs.backendapi.repository.AdminRepository;
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

    @Autowired
    AdminRepository adminRepository;

    private final TokenList tokenList = new TokenList();

    public UUID createToken(UUID userId) {
        return tokenList.addToken(userId);
    }

    public boolean validateToken(UUID tokenId) throws TokenException {
        if (tokenList.isTokenContain(tokenId)) {
            return true;
        }
        throw new TokenException("Token Not Found");
    }

    public User getUser(UUID tokenId) throws UserNotFoundException, TokenException {
        Optional<Customer> customer = customerRepository.findById(tokenList.getUserId(tokenId));
        Optional<Restaurant> restaurant = restaurantRepository.findById(tokenList.getUserId(tokenId));
        if(customer.isPresent()) {
            return customer.get();
        }
        if(restaurant.isPresent()) {
            return restaurant.get();
        }
        throw new UserNotFoundException("User Not Found");
    }

    public Admin getAdmin(UUID tokenId) throws UserNotFoundException, TokenException {
        Optional<Admin> admin = adminRepository.findById(tokenList.getUserId(tokenId));
        if(admin.isPresent()) {
            return admin.get();
        }
        throw new UserNotFoundException("Admin Not Found");
    }

    public boolean isAdmin(UUID tokenId) throws TokenException {
        Optional<Admin> admin = adminRepository.findById(tokenList.getUserId(tokenId));
        return admin.isPresent();
    }

    public void removeToken(UUID tokenId) {
        tokenList.removeToken(tokenId);
    }

    public String getTokenMap() {
        return tokenList.getMap();
    }
}
