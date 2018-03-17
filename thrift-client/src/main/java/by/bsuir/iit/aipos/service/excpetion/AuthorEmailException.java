package by.bsuir.iit.aipos.service.excpetion;

public class AuthorEmailException extends Exception {
    private static final long serialVersionUID = 6988219547669292356L;

    public AuthorEmailException() {
    }

    public AuthorEmailException(String message) {
        super(message);
    }

    public AuthorEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorEmailException(Throwable cause) {
        super(cause);
    }
}
