package by.bsuir.iit.aipos.service.excpetion;

public class ConnectionException extends Exception {

    private static final long serialVersionUID = -3705919961556486941L;

    public ConnectionException() {
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
