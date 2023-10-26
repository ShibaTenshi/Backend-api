package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class ManageOwnerProfileRequest {
    private String tokenId;
    private String oldPassword;
    private String newPassword;
}
