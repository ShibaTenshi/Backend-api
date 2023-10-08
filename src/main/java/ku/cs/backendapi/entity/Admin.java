package ku.cs.backendapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Data;

import java.util.List;

@Data
@Entity
public class Admin {

    @Id
    private String username;

    @OneToMany
    private List<Restaurant> restaurant;

    private String password;
}
