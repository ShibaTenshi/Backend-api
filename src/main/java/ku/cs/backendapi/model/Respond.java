package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class Respond {
    private String status;

    public Respond(String status) {
        this.status = status;
    }
}
