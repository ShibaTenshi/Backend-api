package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exeption.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.repository.CustomerRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    TokenService tokenService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    private Respond auth(User user, Login login) {
        if (user == null || login.getUsername() == null) return new Respond("User not found");

        if(login.getPassword() == null || !passwordEncoder.matches(login.getPassword(), user.getPassword())) return new Respond("Password not correct");

        String token = String.valueOf(tokenService.createToken(user.getId()));
        return new Respond("OK-" + token);
    }

    public Respond loginCustomer(Login login) {
        User customer = customerRepository.findByUsername(login.getUsername());
        return auth(customer, login);
    }

    public Respond loginRestaurant(Login login) {
        User restaurant = restaurantRepository.findByUsername(login.getUsername());
        return auth(restaurant, login);
    }

    public void removeToken(UUID tokenID) {
        tokenService.removeToken(tokenID);
    }
}
