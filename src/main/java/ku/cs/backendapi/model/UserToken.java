package ku.cs.backendapi.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserToken {
    private UUID uuid;
    private LocalDateTime ExpireDateTime;

    public UserToken(UUID uuid, LocalDateTime expireDateTime) {
        this.uuid = uuid;
        ExpireDateTime = expireDateTime;
    }
}
