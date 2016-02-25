package org.urdad.services.messaging;

/**
 * Introduces the concept of a pre-condition violation which is explicitly represented as a checked exception on
 * each service definition.
 */
public abstract class PreconditionViolation extends Exception
{

	public PreconditionViolation(){}
	
    /**
     * Constructs a new <code>PreconditionViolation</code> exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public PreconditionViolation(String message)
    {
        super(message);
    }

    /**
     * Constructs a new <code>PreconditionViolation</code> exception with the specified detail message
     * and cause.
     *
     * @param message the detail message.
     * @param cause the cause.
     */
    public PreconditionViolation(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructs a new <code>PreconditionViolation</code> exception with the specified cause.
     *
     * @param cause the cause.
     */
    public PreconditionViolation(Throwable cause)
    {
        super(cause);
    }

}