package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Customer;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exeption.OTPExpiredException;
import ku.cs.backendapi.exeption.OTPIncorrectException;
import ku.cs.backendapi.exeption.UserNotFoundException;
import ku.cs.backendapi.model.MailBody;
import ku.cs.backendapi.model.OTP;
import ku.cs.backendapi.model.OTPReferList;
import ku.cs.backendapi.repository.CustomerRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OTPService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    private final OTPReferList otpReferList = new OTPReferList();

    private String getMethod(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    private String postMethod(String uri, MailBody object) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, object, String.class);
    }

    public String getNewOtpRegister(User user) {
        OTP otp = otpReferList.getNewOtp(user);
        postMethod("http://localhost:5042/sendotp", new MailBody(user.getEmail(), user.getUsername(), otp.getOtp()));
        return otp.getRefer();
    }

    public String requestNewOtp(String oldRefer) throws UserNotFoundException {
        User user = otpReferList.getUser(oldRefer);
        OTP otp = otpReferList.getNewOtp(user);
        postMethod("http://localhost:5042/sendotp", new MailBody(user.getEmail(), user.getUsername(), otp.getOtp()));
        return otp.getRefer();
    }

    public void otpValidate(String refer, String otpNumber) throws OTPExpiredException, OTPIncorrectException, UserNotFoundException {
        if(otpReferList.otpValidate(refer, otpNumber)) {
            addUser(otpReferList.getUser(refer));
        }
    }

    public OTP[] getAllOtp() {
        return otpReferList.getAllOtp();
    }

    private void addUser(User user) {
        if(user instanceof Customer) customerRepository.save((Customer) user);
        if(user instanceof Restaurant) restaurantRepository.save((Restaurant) user);
    }

}