package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.exeption.UserNotFoundException;
import ku.cs.backendapi.model.Login;
import ku.cs.backendapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    TokenService tokenService;

    @Autowired
    CustomerRepository repository;

    public String login(Login login) throws UserNotFoundException {
        Customer record = repository.findByUsername(login.getUsername());
        if (record == null) throw new UserNotFoundException();
        return tokenService.createToken(record.getId()).toString();
    }

    public void removeToken(UUID tokenID) {
        tokenService.removeToken(tokenID);
    }
}
