package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.model.RegisterCustomer;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.repository.CategoryRepository;
import ku.cs.backendapi.repository.CustomerRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    private boolean isCustomerEmailAvailable(String email) {
        return customerRepository.findByEmail(email) != null;
    }
    private boolean isRestaurantEmailAvailable(String email) {
        return restaurantRepository.findByEmail(email) != null;
    }

    private boolean isCustomerUserNameAvailable(String userName) {
        return customerRepository.findByUsername(userName) != null;
    }

    private boolean isRestaurantUserNameAvailable(String userName) {
        return restaurantRepository.findByUsername(userName) != null;
    }

    private boolean isRestaurantNameAvailable(String restaurantName) {
        return restaurantRepository.findByRestaurantName(restaurantName) != null;
    }

    public Respond createCustomer(RegisterCustomer customer) {
        if (isCustomerEmailAvailable(customer.getEmail())) return new Respond("Email already used.");
        if (isCustomerUserNameAvailable(customer.getUsername())) return new Respond("Username already used.");

        Customer record = modelMapper.map(customer, Customer.class);

        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        record.setPassword(hashedPassword);

        customerRepository.save(record);
        return new Respond("Customer Created");
    }

    public Respond createRestaurant(RegisterRestaurant restaurant) {
        if (isRestaurantEmailAvailable(restaurant.getEmail())) return new Respond("Email already used.");
        if (isRestaurantUserNameAvailable(restaurant.getUsername())) return new Respond("Username already used.");
        if (isRestaurantNameAvailable(restaurant.getRestaurantName()))
            return new Respond("Restaurant's name already used.");

        Restaurant record = modelMapper.map(restaurant, Restaurant.class);
        record.setCategory(categoryRepository.findByCategoryName(restaurant.getCategory()));

        String hashedPassword = passwordEncoder.encode(restaurant.getPassword());
        record.setPassword(hashedPassword);

        restaurantRepository.save(record);
        return new Respond("Restaurant Created");
    }
}
