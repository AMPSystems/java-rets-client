package us.ampre.rets.client.exceptions;

/**
 * Unchecked exception for wrapping checked exceptions encountered during RETS operations.
 * <p>
 * This exception is typically used to propagate underlying {@link Throwable} causes
 * that cannot be thrown directly due to interface constraints (e.g., in {@code Iterator} methods).
 * </p>
 */
public class RetsRuntimeException extends RuntimeException {

    /**
     * Constructs a new {@code RetsRuntimeException} with the specified cause.
     *
     * @param cause the underlying cause of this exception
     */
    public RetsRuntimeException(Throwable cause) {
        super(cause);
    }
}