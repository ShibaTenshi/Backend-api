package ku.cs.backendapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class OtpServiceTest {
    @Autowired
    OTPService service;

    @Test
    public void getApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://content-shibaqueue.doksakura.com/remove/{type}/{name}", null, String.class, "restaurant", "BarBQPlaza");

    }
}
