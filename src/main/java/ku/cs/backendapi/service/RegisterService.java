package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.exeption.MailAlreadyRegisterException;
import ku.cs.backendapi.model.Register;
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

    public void isEmailAvailable(String email) throws MailAlreadyRegisterException {
        if(repository.findByEmail(email) != null) {
            throw new MailAlreadyRegisterException();
        }
    }

    public void createCustomer(Register customer) throws MailAlreadyRegisterException {
        isEmailAvailable(customer.getEmail());

        Customer record = modelMapper.map(customer, Customer.class);

        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        record.setPassword(hashedPassword);

        repository.save(record);
    }
}
