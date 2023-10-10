package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class RegisterCustomer {
    private String name;

    private String username;

    private String email;

    private String password;
}
