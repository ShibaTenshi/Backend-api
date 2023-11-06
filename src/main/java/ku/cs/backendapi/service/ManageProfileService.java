package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.exception.AuthException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.CustomerProfileDTO;
import ku.cs.backendapi.model.ManageProfileCustomer;
import ku.cs.backendapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ManageProfileService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerProfileDTO getUser(String tokenId) throws UserNotFoundException, TokenException {
        Customer customer = (Customer) tokenService.getUser(UUID.fromString(tokenId));

        CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();
        customerProfileDTO.setName(customer.getName());
        customerProfileDTO.setUsername(customer.getUsername());
        customerProfileDTO.setEmail(customer.getEmail());

        return customerProfileDTO;
    }

    public void changePassword(String tokenId, String oldPassword, String newPassword) throws UserNotFoundException, TokenException, AuthException {
        Customer customer = (Customer) tokenService.getUser(UUID.fromString(tokenId));

        if (oldPassword != null && newPassword != null) {
            if (BCrypt.checkpw(oldPassword, customer.getPassword())) {
                String hashedPassword = passwordEncoder.encode(newPassword);
                customer.setPassword(hashedPassword);
                customerRepository.save(customer);
            } else throw new AuthException("Password Mismatch");
        } else throw new AuthException("One or more required fields are missing");
    }

}
