package ku.cs.backendapi.model;

import lombok.Data;

@Data
public class Respond {
    private String value;

    public Respond(String value) {
        this.value = value;
    }
}
