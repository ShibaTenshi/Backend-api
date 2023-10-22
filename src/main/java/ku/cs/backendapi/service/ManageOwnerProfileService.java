package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.ManageOwnerProfileRequest;
import ku.cs.backendapi.model.OwnerProfileDTO;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManageOwnerProfileService {
    @Autowired
    TokenService tokenService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public OwnerProfileDTO getUser(String tokenId) throws UserNotFoundException, TokenException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(tokenId));

        OwnerProfileDTO ownerProfileDTO = new OwnerProfileDTO();
        ownerProfileDTO.setOwnerName(restaurant.getOwnerName());
        ownerProfileDTO.setUsername(restaurant.getUsername());
        ownerProfileDTO.setEmail(restaurant.getEmail());
        ownerProfileDTO.setRestaurantName(restaurant.getRestaurantName());

        return ownerProfileDTO;
    }

    public void changePassword(ManageOwnerProfileRequest request) throws UserNotFoundException, TokenException, AuthException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(request.getTokenId()));

        if (request.getOldPassword() != null && request.getNewPassword() != null) {
            if (BCrypt.checkpw(request.getOldPassword(), restaurant.getPassword())) {
                String hashedPassword = passwordEncoder.encode(request.getNewPassword());
                restaurant.setPassword(hashedPassword);
                restaurantRepository.save(restaurant);
            } else throw new AuthException("Password Mismatch");
        } else throw new AuthException("One or more required fields are missing");
    }
}
