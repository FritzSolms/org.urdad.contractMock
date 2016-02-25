package org.urdad.services.validation;

import org.urdad.services.messaging.PreconditionViolation;

/** Thrown to indicate that the 'request must be valid' pre-condition has been violated. */
public class RequestNotValidException extends PreconditionViolation
{

	/**
     * Constructs a new <code>RequestNotValidException</code> exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public RequestNotValidException(String message)
    {
        super(message);
    }

    /**
     * Constructs a new <code>RequestNotValidException</code> exception with the specified detail message
     * and cause.
     *
     * @param message the detail message.
     * @param cause the cause.
     */
    public RequestNotValidException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructs a new <code>RequestNotValidException</code> exception with the specified cause.
     *
     * @param cause the cause.
     */
    public RequestNotValidException(Throwable cause)
    {
        super(cause);
    }

}