package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class Register {
    private String name;

    private String username;

    private String email;

    private String password;
}
