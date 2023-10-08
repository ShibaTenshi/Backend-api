package ku.cs.backendapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Customer implements User{

    @Id
    @GeneratedValue
    private UUID idCustomer;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookingList;

    private String customerImage;
    private String username;
    private String password;
    private String email;
    private String name;

    @Override
    public UUID getId() {
        return idCustomer;
    }
}
