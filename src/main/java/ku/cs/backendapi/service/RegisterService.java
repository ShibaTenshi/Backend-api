package ku.cs.backendapi.service;

import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.MailAlreadyRegisterException;
import ku.cs.backendapi.exception.MailFormatException;
import ku.cs.backendapi.exception.RestaurantNameAlreadyRegisterException;
import ku.cs.backendapi.exception.UsernameAlreadyRegisterException;
import ku.cs.backendapi.model.RegisterCustomer;
import ku.cs.backendapi.model.RegisterRestaurant;
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

    private boolean checkMailFormat(String mail) {
        return mail.endsWith("gmail.com") || mail.endsWith("ku.th");
    }

    public String createCustomer(RegisterCustomer customer) throws MailAlreadyRegisterException, UsernameAlreadyRegisterException, MailFormatException {
        if (isCustomerEmailAvailable(customer.getEmail())) throw new MailAlreadyRegisterException();
        if (isCustomerUserNameAvailable(customer.getUsername())) throw new UsernameAlreadyRegisterException();
        if (!checkMailFormat(customer.getEmail())) throw new MailFormatException();

        Customer record = modelMapper.map(customer, Customer.class);

        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        record.setPassword(hashedPassword);

        return otpService.getNewOtpRegister(record);
    }

    public String createRestaurant(RegisterRestaurant restaurant) throws MailAlreadyRegisterException, UsernameAlreadyRegisterException, RestaurantNameAlreadyRegisterException, MailFormatException {
        if (isRestaurantEmailAvailable(restaurant.getEmail())) throw new MailAlreadyRegisterException();
        if (isRestaurantUserNameAvailable(restaurant.getUsername())) throw new UsernameAlreadyRegisterException();
        if (isRestaurantNameAvailable(restaurant.getRestaurantName())) throw new RestaurantNameAlreadyRegisterException();
        if (!checkMailFormat(restaurant.getEmail())) throw new MailFormatException();

        Restaurant record = modelMapper.map(restaurant, Restaurant.class);
        record.setStatus(RestaurantStatus.UNAPPROVED);
        record.setCategory(categoryRepository.findByCategoryName(restaurant.getCategory()));

        return otpService.getNewOtpRegister(record);
    }
}