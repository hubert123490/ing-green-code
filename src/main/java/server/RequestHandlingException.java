package server;

public class RequestHandlingException extends RuntimeException {
    public RequestHandlingException(String message, Throwable cause) {
        super(message, cause);
    }
}