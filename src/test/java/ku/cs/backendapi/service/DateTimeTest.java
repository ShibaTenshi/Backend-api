package ku.cs.backendapi.service;

import ku.cs.backendapi.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
@Service
public class DateTimeTest {
    @Autowired
    BookingRepository bookingRepository;

    @Test
    public void testDate() {
    }
}
