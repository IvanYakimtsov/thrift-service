package by.bsuir.iit.aipos.service.excpetion;

public class NameFieldException extends Exception {

    private static final long serialVersionUID = 5555809070573580284L;

    public NameFieldException() {
    }

    public NameFieldException(String message) {
        super(message);
    }

    public NameFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NameFieldException(Throwable cause) {
        super(cause);
    }
}
