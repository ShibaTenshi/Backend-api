package ku.cs.backendapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnapprovedRestaurant extends RegisterRestaurant{
    private String id;
    private String dateAdded;
}