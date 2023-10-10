package ku.cs.backendapi.model;

import ku.cs.backendapi.common.RespondCode;
import lombok.Data;

@Data
public class Respond {
    private RespondCode status;
    private Object value;

    public Respond(RespondCode status) {
        this.status = status;
        value = "";
    }

    public Respond(RespondCode status, Object value) {
        this.status = status;
        this.value = value;
    }
}
