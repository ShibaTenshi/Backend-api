package ku.cs.backendapi.entity;

import java.util.UUID;

public interface User {
    String getUsername();
    String getPassword();
    UUID getId();
    String getEmail();
}
