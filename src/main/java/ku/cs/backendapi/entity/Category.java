package ku.cs.backendapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Category {
    @Id
    private int idCategory;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Restaurant> restaurantList;
}
