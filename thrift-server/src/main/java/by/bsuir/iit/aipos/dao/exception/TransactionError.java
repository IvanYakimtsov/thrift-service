package by.bsuir.iit.aipos.dao.exception;

public class TransactionError extends RuntimeException {
    private static final long serialVersionUID = -210508611843892491L;

    public TransactionError() {
        super();
    }

    public TransactionError(String message) {
        super(message);
    }

    public TransactionError(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionError(Throwable cause) {
        super(cause);
    }
}