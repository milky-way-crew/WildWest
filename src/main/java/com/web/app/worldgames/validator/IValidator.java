package com.web.app.worldgames.validator;

import org.springframework.validation.Errors;

public interface IValidator {
    public void validate(Object object, Errors errors);
    public boolean supports(Class<?> vclass);
}
