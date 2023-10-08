package ku.cs.backendapi.repository;

import ku.cs.backendapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByUsername(String username);
    Customer findByEmail(String email);
}
