package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Admin;
import ku.cs.backendapi.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@SpringBootTest
@Service
public class AddAdmin {
    @Autowired private AdminRepository adminRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Test
    public void addAdmin() {
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID());
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));

        adminRepository.save(admin);
    }
}
