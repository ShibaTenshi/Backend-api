package ku.cs.backendapi.model;

import jakarta.persistence.*;
import ku.cs.backendapi.common.RestaurantStatus;
import ku.cs.backendapi.entity.Admin;
import ku.cs.backendapi.entity.Booking;
import ku.cs.backendapi.entity.Category;
import ku.cs.backendapi.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestaurantViewInfoDTO extends SelectedRestaurant{
    private String logoImage;
    private List<String> envImages;
    private List<String> menuImages;
}