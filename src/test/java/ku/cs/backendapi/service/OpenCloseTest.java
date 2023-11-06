package ku.cs.backendapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
@Service
public class OpenCloseTest {
    @Test
    public void testHour() {
        LocalTime localTime = LocalTime.of(24, 0);
        System.out.println(localTime);
    }
}
