package com.helpit.controllers.validators;

import com.helpit.user.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class VolunteerRegisterValidator implements Validator {
    @Override
    public boolean supports (Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate (Object obj, Errors errors) {
        User u = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","error.user.username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","error.user.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","error.user.password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"volunteer.name","error.user.volunteer.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"volunteer.surname","error.user.volunteer.surname.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.city","error.user.address.city.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.postcode","error.user.address.postcode.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.street","error.user.address.street.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.numberOfHome","error.user.address.numberOfHome.empty");

    }

    public  void validateEmailExist(User user, Errors errors) {
        if (user != null) {
            errors.rejectValue("email", "error.userEmail.exists");
        }
    }

    public  void validateUsernameExist(User user, Errors errors) {
        if (user != null) {
            errors.rejectValue("username", "error.userUsername.exists");
        }
    }
}
