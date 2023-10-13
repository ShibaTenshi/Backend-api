package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exception.PasswordNotCorrectException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.Login;
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

    private String auth(User user, Login login) throws UserNotFoundException, PasswordNotCorrectException {
        if (user == null || login.getUsername() == null) throw new UserNotFoundException();

        if (login.getPassword() == null || !passwordEncoder.matches(login.getPassword(), user.getPassword()))
            throw new PasswordNotCorrectException();

        return String.valueOf(tokenService.createToken(user.getId()));
    }

    public String loginCustomer(Login login) throws UserNotFoundException, PasswordNotCorrectException {
        User customer = customerRepository.findByUsername(login.getUsername());
        return auth(customer, login);
    }

    public String loginRestaurant(Login login) throws UserNotFoundException, PasswordNotCorrectException {
        User restaurant = restaurantRepository.findByUsername(login.getUsername());
        return auth(restaurant, login);
    }

    public void removeToken(UUID tokenId) {
        tokenService.removeToken(tokenId);
    }
}