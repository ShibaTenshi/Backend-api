package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.common.URL;
import ku.cs.backendapi.entity.Admin;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.ApprovedDTO;
import ku.cs.backendapi.model.DeleteRestaurantDTO;
import ku.cs.backendapi.model.MailDeleteRestaurantBody;
import ku.cs.backendapi.model.MailNewRestaurantBody;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void deleteRestaurant(DeleteRestaurantDTO deleteRestaurantDTO) throws UserNotFoundException, TokenException {
        Admin admin = tokenService.getAdmin(UUID.fromString(deleteRestaurantDTO.getTokenId()));
        if (admin == null) throw new UserNotFoundException("Admin not found");

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(UUID.fromString(deleteRestaurantDTO.getIdRestaurant()));
        if (optionalRestaurant.isEmpty()) throw new UserNotFoundException("Restaurant not found");
        Restaurant restaurant = optionalRestaurant.get();

        //send mail
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.MAIL + "/sendDelete", new MailDeleteRestaurantBody(restaurant.getEmail(), restaurant.getUsername(), restaurant.getRestaurantName(), deleteRestaurantDTO.getReason()), String.class);
        restTemplate.postForObject(URL.STORAGE + "/remove/{type}/{name}", null, String.class, "restaurant", restaurant.getRestaurantName());
        restaurantRepository.delete(restaurant);
    }

    public void approveRestaurant(ApprovedDTO approvedDTO) throws UserNotFoundException, TokenException {
        Admin admin = tokenService.getAdmin(UUID.fromString(approvedDTO.getTokenId()));
        if (admin == null) throw new UserNotFoundException("Admin not found");

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(UUID.fromString(approvedDTO.getId()));
        if (optionalRestaurant.isEmpty()) throw new UserNotFoundException("Restaurant not found");
        Restaurant restaurant = optionalRestaurant.get();
        //send mail

        String newPass = String.valueOf(UUID.randomUUID()).replace("-", "");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.MAIL + "/sendNew", new MailNewRestaurantBody(restaurant.getEmail(), restaurant.getUsername(), restaurant.getRestaurantName(), newPass), String.class);

        restaurant.setStatus(RestaurantStatus.APPROVED);
        restaurant.setPassword(passwordEncoder.encode(newPass));
        restaurantRepository.save(restaurant);
    }
}