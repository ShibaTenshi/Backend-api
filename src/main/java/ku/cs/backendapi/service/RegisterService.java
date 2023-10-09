package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.exeption.MailAlreadyRegisterException;
import ku.cs.backendapi.model.Register;
import ku.cs.backendapi.model.Respond;
import ku.cs.backendapi.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    private boolean isEmailAvailable(String email) {
        return repository.findByEmail(email) != null;
    }

    private boolean isUserNameAvailable(String userName) {
        return repository.findByEmail(userName) != null;
    }

    public Respond createCustomer(Register customer) {
        if (isEmailAvailable(customer.getEmail())) return new Respond("Email Exited");
        if (isUserNameAvailable(customer.getUsername())) return new Respond("Username Exited");

        Customer record = modelMapper.map(customer, Customer.class);

        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        record.setPassword(hashedPassword);

        repository.save(record);
        return new Respond("OK");
    }
}
