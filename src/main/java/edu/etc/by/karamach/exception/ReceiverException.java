package edu.etc.by.karamach.exception;

/**
 * Exception throwable by Receiver
 *
 * @author Nickolai Karamach
 */
public class ReceiverException extends Exception{
    private static final long serialVersionUID = -8810183342689871110L;

    public ReceiverException() {
        super();
    }

    public ReceiverException(String message) {
        super(message);
    }

    public ReceiverException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReceiverException(Throwable cause) {
        super(cause);
    }
}
