package org.urdad.validation.beanvalidation;

import java.util.Set;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.stereotype.Service;
import org.urdad.services.validation.RequestNotValidException;

/** The fulfilment of the service contracts that are associated with bean validation. */
@Service
public class BeanValidationBean implements BeanValidation.BeanValidationLocal, BeanValidation.BeanValidationRemote
{

    /** Validates the specified object using the bean validation framework. */
    @Override
    public BeanValidation.ValidateObjectResponse validateObject(BeanValidation.ValidateObjectRequest
        validateObjectRequest) throws RequestNotValidException
    {
        // Check pre-condition: Request must be valid.
        if (validateObjectRequest == null)
        {
            throw new RequestNotValidException("A ValidateObjectRequest must be specified.");
        }
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(validateObjectRequest);
        if (!constraintViolations.isEmpty())
        {
            throw new RequestNotValidException(buildMessage(constraintViolations));
        }
        if (validateObjectRequest.getObject() == null)
        {
            throw new RequestNotValidException("A " + validateObjectRequest.getObjectType().getSimpleName() +
                " must be specified.");
        }

        // Use the Bean Validation framework for validation.
        constraintViolations = validator.validate(validateObjectRequest.getObject());

        // Create service response.
        ValidateObjectResponse validateObjectResponse = new ValidateObjectResponse();

        if (!constraintViolations.isEmpty())
        {
            validateObjectResponse.setValid(false);
            validateObjectResponse.setMessage(buildMessage(constraintViolations));
        }
        else
        {
            validateObjectResponse.setValid(true);
        }

        return validateObjectResponse;
    }

    /** Convenience method: construct a message given a set of constraint violations. */
    private String buildMessage(Set<ConstraintViolation<Object>> constraintViolations)
    {
        StringBuilder message = new StringBuilder();

        int count = 0;
        for (ConstraintViolation<?> constraintViolation : constraintViolations)
        {
            count++;

            if (count > 1)
            {
                message.append(" ");
            }

            message.append(constraintViolation.getMessage());
        }

        return message.toString();
    }

    @Inject
    private Validator validator;

}