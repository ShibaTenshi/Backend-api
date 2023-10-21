package ku.cs.backendapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Admin{

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    @OneToMany
    private List<Restaurant> restaurant;

    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin() {

    }
}
