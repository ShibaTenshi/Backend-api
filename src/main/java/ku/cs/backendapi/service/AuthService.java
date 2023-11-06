package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.common.URL;
import ku.cs.backendapi.entity.*;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RestaurantTableTypeRepository restaurantTableTypeRepository;

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

    public void deleteUser(String tokenId) throws UserNotFoundException, TokenException {
        User user = tokenService.getUser(UUID.fromString(tokenId));
        RestTemplate restTemplate = new RestTemplate();

        if(user instanceof Customer) {
            bookingRepository.deleteAll(bookingRepository.findAllByCustomer_Id(user.getId()));
            customerRepository.delete((Customer) user);
            restTemplate.postForObject(URL.STORAGE + "/remove/customer/{name}", null, String.class, user.getUsername());
        }
        if(user instanceof Restaurant) {
            bookingRepository.deleteAll(bookingRepository.findAllByRestaurant((Restaurant) user));
            restaurantTableTypeRepository.deleteAll(restaurantTableTypeRepository.findByRestaurant((Restaurant) user));
            restaurantRepository.delete((Restaurant) user);
            restTemplate.postForObject(URL.STORAGE + "/remove/restaurant/{name}", null, String.class, ((Restaurant) user).getRestaurantName());
        }
    }
}