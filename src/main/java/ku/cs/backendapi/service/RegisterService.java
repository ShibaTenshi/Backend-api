package ku.cs.backendapi.service;

import jakarta.security.auth.message.AuthException;
import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.entity.Admin;
import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exception.MailException;
import ku.cs.backendapi.model.RegisterCustomer;
import ku.cs.backendapi.model.RegisterRestaurant;
import ku.cs.backendapi.repository.AdminRepository;
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
    private AdminRepository adminRepository;

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

    public String createCustomer(RegisterCustomer customer) throws MailException, AuthException {
        if (isCustomerEmailAvailable(customer.getEmail())) throw new AuthException("Mail Already Exited");
        if (isCustomerUserNameAvailable(customer.getUsername())) throw new AuthException("Username Already Exited");
        if (!checkMailFormat(customer.getEmail())) throw new MailException("Mail Format Incorrect");

        Customer record = modelMapper.map(customer, Customer.class);

        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        record.setPassword(hashedPassword);

        return otpService.getNewOtpRegister(record);
    }

    public String createRestaurant(RegisterRestaurant restaurant) throws MailException, AuthException {
        if (isRestaurantEmailAvailable(restaurant.getEmail())) throw new AuthException("Mail Already Exited");
        if (isRestaurantUserNameAvailable(restaurant.getUsername())) throw new AuthException("Username Already Exited");
        if (isRestaurantNameAvailable(restaurant.getRestaurantName())) throw new AuthException("Restaurant Name Already Exited");
        if (!checkMailFormat(restaurant.getEmail())) throw new MailException("Mail Format Incorrect");

        Restaurant record = modelMapper.map(restaurant, Restaurant.class);
        record.setStatus(RestaurantStatus.UNAPPROVED);
        record.setCategory(categoryRepository.findByCategoryName(restaurant.getCategory()));

        return otpService.getNewOtpRegister(record);
    }

    public void addAdmin() {
        Admin admin = adminRepository.findByUsername("admin");
        if(admin == null) {
            adminRepository.save(new Admin("admin", passwordEncoder.encode("admin")));
        }
    }
}