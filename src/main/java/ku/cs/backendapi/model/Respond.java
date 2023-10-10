package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class Respond {
    private int status;
    private Object value;

    public Respond(int status) {
        this.status = status;
    }

    public Respond(int status, Object value) {
        this.status = status;
        this.value = value;
    }
}
