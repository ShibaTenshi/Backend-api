package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.exception.TokenNotfoundException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.CustomerProfileDTO;
import ku.cs.backendapi.model.ManageProfileCustomer;
import ku.cs.backendapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public CustomerProfileDTO getUser(String tokenId) throws UserNotFoundException, TokenNotfoundException {
        Customer customer = (Customer) tokenService.getUser(UUID.fromString(tokenId));

        CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();
        customerProfileDTO.setName(customer.getName());
        customerProfileDTO.setUsername(customer.getUsername());
        customerProfileDTO.setEmail(customer.getEmail());
        customerProfileDTO.setImageLink(customer.getImageLink());

        return customerProfileDTO;
    }

    public void changeProfilePicture(String tokenId, ManageProfileCustomer manageProfileCustomer) throws UserNotFoundException, TokenNotfoundException{
        Customer customer = (Customer) tokenService.getUser(UUID.fromString(tokenId));

        if (manageProfileCustomer.getNewImageLink() != null) {
            customer.setImageLink(manageProfileCustomer.getNewImageLink());
            customerRepository.save(customer);
        }
    }

    public void changePassword(String tokenId, ManageProfileCustomer manageProfileCustomer) throws UserNotFoundException, TokenNotfoundException {
        Customer customer = (Customer) tokenService.getUser(UUID.fromString(tokenId));

        if (manageProfileCustomer.getNewPassword() != null) {
            String hashedPassword = passwordEncoder.encode(manageProfileCustomer.getNewPassword());
            customer.setPassword(hashedPassword);
            customerRepository.save(customer);
        }
    }

}
