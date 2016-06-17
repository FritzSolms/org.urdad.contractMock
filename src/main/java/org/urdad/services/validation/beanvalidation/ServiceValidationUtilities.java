package org.urdad.services.validation.beanvalidation;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.validation.beanvalidation.BeanValidation;

/**
 * FIXME: Javadoc
 */
@Service
public class ServiceValidationUtilities
{

    /** FIXME: Javadoc */
    public <T extends Request> void validateRequest(Class<T> requestType, T request) throws RequestNotValidException
    {
        BeanValidation.ValidateObjectResponse validateObjectResponse 
                = beanValidation.validateObject(new BeanValidation
            .ValidateObjectRequest<>(requestType, request));

        if (!validateObjectResponse.getValid())
        {
            throw new RequestNotValidException(validateObjectResponse.getMessage());
        }
    }

    /** FIXME: Javadoc */
    public <T extends Response> void validateResponse(Class<T> responseType, T response) throws RequestNotValidException
    {
        BeanValidation.ValidateObjectResponse validateObjectResponse = beanValidation.validateObject(new BeanValidation
            .ValidateObjectRequest<>(responseType, response));

        if (!validateObjectResponse.getValid())
        {
            throw new RequestNotValidException(validateObjectResponse.getMessage());
        }
    }

    @Inject
    private BeanValidation.BeanValidationLocal beanValidation;

}