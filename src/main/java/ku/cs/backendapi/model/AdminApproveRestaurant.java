package ku.cs.backendapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminApproveRestaurant extends UnapprovedRestaurant{
    private String tokenId;
}