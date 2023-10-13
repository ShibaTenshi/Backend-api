package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.exeption.TokenNotfoundException;
import ku.cs.backendapi.exeption.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentService {

    @Autowired
    TokenService tokenService;

    public String getImageLinkCustomer(UUID tokenId) throws UserNotFoundException, TokenNotfoundException {
        Customer customer = tokenService.getCustomer(tokenId);
        return customer.getImageLink();
    }
}