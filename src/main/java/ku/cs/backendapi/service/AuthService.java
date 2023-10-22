package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.entity.Admin;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.repository.AdminRepository;
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
    @Autowired
    AdminRepository adminRepository;

    private String auth(User user, Login login) throws UserNotFoundException, AuthException {
        if (user == null || login.getUsername() == null) throw new UserNotFoundException("User Not Found");

        if (login.getPassword() == null || !passwordEncoder.matches(login.getPassword(), user.getPassword()))
            throw new AuthException("Password Incorrect");

        if(user instanceof Restaurant) {
            if(((Restaurant) user).getStatus() == RestaurantStatus.UNAPPROVED) throw new AuthException("Unapproved Restaurant");
        }

        return String.valueOf(tokenService.createToken(user.getId()));
    }

    public String loginCustomer(Login login) throws UserNotFoundException, AuthException {
        User customer = customerRepository.findByUsername(login.getUsername());
        return auth(customer, login);
    }

    public String loginRestaurant(Login login) throws UserNotFoundException, AuthException {
        User restaurant = restaurantRepository.findByUsername(login.getUsername());
        return auth(restaurant, login);
    }

    public String loginAdmin(Login login) throws UserNotFoundException, AuthException {
        Admin admin = adminRepository.findByUsername(login.getUsername());
        if (admin == null || login.getUsername() == null) throw new UserNotFoundException("User Not Found");

        if (login.getPassword() == null || !passwordEncoder.matches(login.getPassword(), admin.getPassword()))
            throw new AuthException("Password Incorrect");

        return String.valueOf(tokenService.createToken(admin.getId()));
    }

    public void removeToken(UUID tokenId) {
        tokenService.removeToken(tokenId);
    }
}