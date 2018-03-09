package by.bsuir.iit.aipos.service.excpetion;

public class BodyFieldException extends Exception {

    private static final long serialVersionUID = 4658862047330989660L;

    public BodyFieldException() {
    }

    public BodyFieldException(String message) {
        super(message);
    }

    public BodyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public BodyFieldException(Throwable cause) {
        super(cause);
    }
}
