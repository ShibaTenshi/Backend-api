package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.entity.Category;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.model.SelectedRestaurant;
import ku.cs.backendapi.model.UnApprovedRestaurantTitle;
import ku.cs.backendapi.model.UnapprovedRestaurant;
import ku.cs.backendapi.repository.CategoryRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    //Admin
    public List<UnApprovedRestaurantTitle> getUnapprovedRestaurantList(String tokenId) throws AuthException, TokenException {
        if(!tokenService.isAdmin(UUID.fromString(tokenId))) throw new AuthException("User Is Not Admin");

        List<Restaurant> unapproved = restaurantRepository.findAllByStatus(RestaurantStatus.UNAPPROVED);
        List<UnApprovedRestaurantTitle> unApprovedRestaurantTitles = new ArrayList<>();

        for(Restaurant restaurant : unapproved) {
            UnApprovedRestaurantTitle temp = modelMapper.map(restaurant, UnApprovedRestaurantTitle.class);
            temp.setCategory(restaurant.getCategory().getCategoryName());
            temp.setDateAdded(restaurant.getDateAdded().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
            unApprovedRestaurantTitles.add(temp);
        }

        return unApprovedRestaurantTitles;
    }

    //Admin
    public UnapprovedRestaurant getUnapprovedRestaurant(String tokenId, String id) throws UserNotFoundException, AuthException, TokenException {
        if(!tokenService.isAdmin(UUID.fromString(tokenId))) throw new AuthException("User Is Not Admin");

        Optional<Restaurant> record = restaurantRepository.findById(UUID.fromString(id));
        if(record.isEmpty()) throw new UserNotFoundException("Restaurant Not Found");
        if(record.get().getStatus() == RestaurantStatus.APPROVED) throw new AuthException("Restaurant Already Approved");

        UnapprovedRestaurant unapprovedRestaurant = modelMapper.map(record.get(), UnapprovedRestaurant.class);
        unapprovedRestaurant.setDateAdded(record.get().getDateAdded().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        return unapprovedRestaurant;
    }
    public SelectedRestaurant getRestaurantInfo(String tokenId, String id) throws UserNotFoundException{

        Optional<Restaurant> record = restaurantRepository.findById(UUID.fromString(id));
        if(record.isEmpty()) throw new UserNotFoundException("Restaurant Not Found");

        SelectedRestaurant selectedRestaurant = modelMapper.map(record.get(), SelectedRestaurant.class);

        return selectedRestaurant;
    }
}