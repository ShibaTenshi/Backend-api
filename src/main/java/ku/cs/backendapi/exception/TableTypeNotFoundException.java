package ku.cs.backendapi.exception;

public class TableTypeNotFoundException extends Exception {
    public TableTypeNotFoundException() {
        super("Table type not found");
    }
}
