package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class CustomerProfileDTO {
    private String name;
    private String username;
    private String email;
    private String imageLink;
}
