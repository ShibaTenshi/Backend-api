package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.ManageOwnerProfileRequest;
import ku.cs.backendapi.model.ManageRestaurantInformationRequest;
import ku.cs.backendapi.model.RestaurantInformationDTO;
import ku.cs.backendapi.repository.CategoryRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManageRestaurantInformationService {
    @Autowired
    TokenService tokenService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public RestaurantInformationDTO getRestaurant(String tokenId) throws UserNotFoundException, TokenException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(tokenId));

        RestaurantInformationDTO restaurantInformationDTO = new RestaurantInformationDTO();
        restaurantInformationDTO.setCategory(restaurant.getCategory());
        restaurantInformationDTO.setRestaurantName(restaurant.getRestaurantName());
        restaurantInformationDTO.setDescription(restaurant.getDescription());
        restaurantInformationDTO.setOpenTime(restaurant.getOpenTime());
        restaurantInformationDTO.setCloseTime(restaurant.getCloseTime());
        restaurantInformationDTO.setLocation(restaurant.getLocation());
        restaurantInformationDTO.setMapLink(restaurant.getMapLink());
        restaurantInformationDTO.setOpenDate(restaurant.getOpenDate());

        return restaurantInformationDTO;
    }

    public void changeInformation(ManageRestaurantInformationRequest request) throws UserNotFoundException, TokenException, AuthException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(request.getTokenId()));

        if (request.getCategory() != null
                && request.getDescription() != null
                && request.getRestaurantName() != null
                && request.getOpenTime() != null
                && request.getCloseTime() != null
                && request.getLocation() != null
                && request.getMapLink() != null
                && request.getOpenDate() != null) {

            Restaurant existingRestaurant = restaurantRepository.findByRestaurantName(request.getRestaurantName());

            if (existingRestaurant == null || existingRestaurant.getId() == restaurant.getId()) {
                // If the restaurant name doesn't exist, or it exists but belongs to the same restaurant (self-update),
                // then it's not a duplicate, proceed with the below updates.
                restaurant.setCategory(categoryRepository.findByCategoryName(request.getCategory()));
                restaurant.setDescription(request.getDescription());
                restaurant.setRestaurantName(request.getRestaurantName());
                restaurant.setOpenTime(request.getOpenTime());
                restaurant.setCloseTime(request.getCloseTime());
                restaurant.setLocation(request.getLocation());
                restaurant.setMapLink(request.getMapLink());
                restaurant.setOpenDate(request.getOpenDate());

                restaurantRepository.save(restaurant);
            } else throw new AuthException("Restaurant name already existed");

        } else throw new AuthException("One or more required fields are missing");
    }
}
