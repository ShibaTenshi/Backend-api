package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exeption.TokenNotfoundException;
import ku.cs.backendapi.exeption.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentService {

    private String host = "https://content.doksakura.com";

    @Autowired
    TokenService tokenService;

    public String getImageLinkCustomer(UUID tokenId) throws UserNotFoundException, TokenNotfoundException {
        User user = tokenService.getUser(tokenId);
        if(user.getImageLink() == null) return host + "/shibaqueue/userImage/profile.png";
        return user.getImageLink();
    }
}