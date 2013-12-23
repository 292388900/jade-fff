package com.recruiters.web.validator;

import com.recruiters.web.form.ApplicantForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Validator for "Applicant Form" if adding applicant
 */
@Component
public class AddApplicantValidator implements Validator {

    public AddApplicantValidator() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public boolean supports(final Class clazz) {
        return ApplicantForm.class.equals(clazz);
    }

    public void validate(final Object object, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.applicantForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.applicantForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.applicantForm.description");
        ValidationUtils.rejectIfEmpty(errors, "vacancyId", "NotEmpty.applicationForm.vacancyId");
    }
}