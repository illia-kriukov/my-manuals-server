package se.lnu.agile.mymanuals.error;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by ilyakruikov on 11/14/16.
 */
public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }

    public static ValidationError fromException(Exception e) {
        ValidationError error = new ValidationError("Validation failed.");
        error.addValidationError(e.getMessage());
        return error;
    }

}