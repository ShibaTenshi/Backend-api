package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class ManageProfileCustomer {
    private String newImageLink;
    private String originalPassword;
    private String newPassword;
}
