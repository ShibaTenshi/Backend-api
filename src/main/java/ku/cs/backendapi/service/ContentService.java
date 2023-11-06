package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.common.URL;
import ku.cs.backendapi.entity.Category;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.*;
import ku.cs.backendapi.repository.CategoryRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        if (!tokenService.isAdmin(UUID.fromString(tokenId))) throw new AuthException("User Is Not Admin");

        List<Restaurant> unapproved = restaurantRepository.findAllByStatus(RestaurantStatus.UNAPPROVED);
        List<UnApprovedRestaurantTitle> unApprovedRestaurantTitles = new ArrayList<>();

        for (Restaurant restaurant : unapproved) {
            UnApprovedRestaurantTitle temp = modelMapper.map(restaurant, UnApprovedRestaurantTitle.class);
            temp.setCategory(restaurant.getCategory().getCategoryName());
            temp.setDateAdded(restaurant.getDateAdded().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
            unApprovedRestaurantTitles.add(temp);
        }

        return unApprovedRestaurantTitles;
    }

    //Admin
    public UnapprovedRestaurant getUnapprovedRestaurant(String tokenId, String id) throws UserNotFoundException, AuthException, TokenException {
        if (!tokenService.isAdmin(UUID.fromString(tokenId))) throw new AuthException("User Is Not Admin");

        Optional<Restaurant> record = restaurantRepository.findById(UUID.fromString(id));
        if (record.isEmpty()) throw new UserNotFoundException("Restaurant Not Found");
        if (record.get().getStatus() == RestaurantStatus.APPROVED)
            throw new AuthException("Restaurant Already Approved");

        UnapprovedRestaurant unapprovedRestaurant = modelMapper.map(record.get(), UnapprovedRestaurant.class);
        unapprovedRestaurant.setDateAdded(record.get().getDateAdded().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        unapprovedRestaurant.setId(record.get().getId().toString());
        return unapprovedRestaurant;
    }

    public List<SearchRestaurantDTO> getAllRestaurantPageSearch(int page, String query) {
        boolean getAll = false;
        Page<Restaurant> pages = restaurantRepository.findAll(
                PageRequest.of(page - 1, 50));

        if(query.isEmpty())  getAll = true;
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<SearchRestaurantDTO> searchRestaurantDTOS = new ArrayList<>();
        for (Restaurant r : pages.toList()) {
            if(!getAll && !r.getRestaurantName().contains(query)) continue;

            String imgUrl = restTemplate.getForObject(URL.STORAGE + "/restaurant/image/logo/" + r.getRestaurantName(), String.class);

            SearchRestaurantDTO temp = new SearchRestaurantDTO();
            temp.setCategory(r.getCategory().getCategoryName());
            temp.setRestaurantName(r.getRestaurantName());
            temp.setImage(URL.STORAGE + imgUrl);
            searchRestaurantDTOS.add(temp);
        }
        return searchRestaurantDTOS;
    }

    public List<SearchRestaurantDTO> getAllRestaurantPageSearchCategory(int page, String category) {
        boolean getAll = false;
        Page<Restaurant> pages = restaurantRepository.findAll(
                PageRequest.of(page - 1, 50));

        if(category.isEmpty())  getAll = true;
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<SearchRestaurantDTO> searchRestaurantDTOS = new ArrayList<>();
        for (Restaurant r : pages.toList()) {
            if(!getAll && !r.getCategory().getCategoryName().contains(category)) continue;

            String imgUrl = restTemplate.getForObject(URL.STORAGE + "/restaurant/image/logo/" + r.getRestaurantName(), String.class);

            SearchRestaurantDTO temp = new SearchRestaurantDTO();
            temp.setCategory(r.getCategory().getCategoryName());
            temp.setRestaurantName(r.getRestaurantName());
            temp.setImage(URL.STORAGE + imgUrl);
            searchRestaurantDTOS.add(temp);
        }
        return searchRestaurantDTOS;
    }

    //restaurant
    public SelectedRestaurant getRestaurantInfo(String tokenId) throws UserNotFoundException, TokenException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(tokenId));

        Optional<Restaurant> record = restaurantRepository.findById(restaurant.getId());
        if(record.isEmpty()) throw new UserNotFoundException("Restaurant Not Found");

        SelectedRestaurant selectedRestaurant = modelMapper.map(record.get(), SelectedRestaurant.class);
        selectedRestaurant.setCategory(record.get().getCategory().getCategoryName());
        return selectedRestaurant;
    }

    //customer
    public RestaurantViewInfoDTO viewRestaurantInfoCustomer(String name) throws UserNotFoundException, TokenException {
        Restaurant restaurant = restaurantRepository.findByRestaurantName(name);
        if(restaurant == null) throw new UserNotFoundException("Restaurant not found");

        RestaurantViewInfoDTO selectedRestaurant = modelMapper.map(restaurant, RestaurantViewInfoDTO.class);
        selectedRestaurant.setCategory(restaurant.getCategory().getCategoryName());

        RestTemplate restTemplate = new RestTemplate();

        String logo = URL.STORAGE + restTemplate.getForObject(URL.STORAGE + "/restaurant/image/logo/" + restaurant.getRestaurantName(), String.class);
        List<String> env = restTemplate.getForObject(URL.STORAGE + "/restaurant/image/env/" + restaurant.getRestaurantName(), List.class);
        List<String> menu = restTemplate.getForObject(URL.STORAGE + "/restaurant/image/menu/" + restaurant.getRestaurantName(), List.class);

        ArrayList<String> newEnv = new ArrayList<>();
        ArrayList<String> newMenu = new ArrayList<>();

        for(String s : env) newEnv.add(URL.STORAGE + s);
        for(String s : menu) newMenu.add(URL.STORAGE + s);

        selectedRestaurant.setLogoImage(logo);
        selectedRestaurant.setEnvImages(newEnv);
        selectedRestaurant.setMenuImages(newMenu);

        return selectedRestaurant;
    }
}