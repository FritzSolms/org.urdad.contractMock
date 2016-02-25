package org.urdad.validation.beanvalidation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.validation.beanvalidation.BeanValidation;

/** Unit tests for services associated with the bean validation domain of responsibility. */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanValidationTestConfiguration.class})
public class BeanValidationTest
{

    /** Test the 'validateObject' service. Ensure that service succeeds if all pre-conditions are met. */
    @Test
    public void validateObject_preconditionsMet_validObject() throws Exception
    {
        assertTrue(beanValidation.validateObject(new BeanValidation.ValidateObjectRequest(StringContainer.class, new
            StringContainer("content"))).getValid());
    }

    /** Test the 'validateObject' service. Ensure that service succeeds if all pre-conditions are met. */
    @Test
    public void validateObject_preconditionsMet_invalidObject() throws Exception
    {
        assertFalse(beanValidation.validateObject(new BeanValidation.ValidateObjectRequest(StringContainer.class, new
            StringContainer(null))).getValid());
    }

    /**
     * Test the 'validateObject' service. Ensure that service fails if the 'request must be valid' pre-condition is
     * not met. In this scenario a null request is submitted.
     */
    @Test(expected = RequestNotValidException.class)
    public void validateObject_invalidRequest_nullRequest() throws Exception
    {
        beanValidation.validateObject(null);

        fail("A null request must not be accepted.");
    }

    /**
     * Test the 'validateObject' service. Ensure that service fails if the 'request must be valid' pre-condition is
     * not met. In this scenario a request containing a null person is submitted.
     */
    @Test(expected = RequestNotValidException.class)
    public void validateObject_invalidRequest_nullObject() throws Exception
    {
        beanValidation.validateObject(new BeanValidation.ValidateObjectRequest(StringContainer.class, null));

        fail("A null object should not be accepted.");
    }

    /** FIXME: Javadoc */
    private class StringContainer
    {

        public StringContainer(String string)
        {
            this.string = string;
        }

        @NotNull
        private String string;

    }

    // Test subject.
    @Inject
    private BeanValidation beanValidation;

}