package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RespondCode;
import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.User;
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

    @Autowired
    private OTPService otpService;

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
        if (isCustomerEmailAvailable(customer.getEmail())) return new Respond(RespondCode.FAILED, "Email already used.");
        if (isCustomerUserNameAvailable(customer.getUsername())) return new Respond(RespondCode.FAILED, "Username already used.");

        Customer record = modelMapper.map(customer, Customer.class);

        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        record.setPassword(hashedPassword);

        otpService.getNewOtp(record);

        //customerRepository.save(record);
        return new Respond(RespondCode.OK, "OTP Sent");
    }

    public Respond createRestaurant(RegisterRestaurant restaurant) {
        if (isRestaurantEmailAvailable(restaurant.getEmail())) return new Respond(RespondCode.FAILED, "Email already used.");
        if (isRestaurantUserNameAvailable(restaurant.getUsername())) return new Respond(RespondCode.FAILED, "Username already used.");
        if (isRestaurantNameAvailable(restaurant.getRestaurantName()))
            return new Respond(RespondCode.FAILED, "Restaurant's name already used.");

        Restaurant record = modelMapper.map(restaurant, Restaurant.class);
        record.setCategory(categoryRepository.findByCategoryName(restaurant.getCategory()));

        String hashedPassword = passwordEncoder.encode(restaurant.getPassword());
        record.setPassword(hashedPassword);

        otpService.getNewOtp(record);

        //restaurantRepository.save(record);
        return new Respond(RespondCode.OK, "OTP Sent");
    }

    private void addUser(User user) {
        if(user instanceof Customer) customerRepository.save((Customer) user);
        if(user instanceof Restaurant) restaurantRepository.save((Restaurant) user);
    }
}
