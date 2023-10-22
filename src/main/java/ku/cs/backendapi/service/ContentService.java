package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.entity.Category;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.repository.CategoryRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {

    @Autowired
    TokenService tokenService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<String> getAllCategory() {
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            categoryNames.add(category.getCategoryName());
        }
        return categoryNames;
    }

    public List<RegisterRestaurant> getUnapprovedRestaurant() {
        List<Restaurant> unapproved = restaurantRepository.findAllByStatus(RestaurantStatus.UNAPPROVED);
        List<RegisterRestaurant> registerRestaurants = new ArrayList<>();

        for(Restaurant restaurant : unapproved) {
            registerRestaurants.add(modelMapper.map(restaurant, RegisterRestaurant.class));
        }

        return registerRestaurants;
    }
}