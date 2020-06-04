package com.helpit.controller.validators;

import com.helpit.user.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class FoundationRegisterValidator implements Validator {
    @Override
    public boolean supports (Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate ( Object obj, Errors errors) {
        User u = (User) obj;

        ValidationUtils.rejectIfEmpty(errors,"username","error.user.username.empty");
        ValidationUtils.rejectIfEmpty(errors,"email","error.user.email.empty");
        ValidationUtils.rejectIfEmpty(errors,"password","error.user.password.empty");
        ValidationUtils.rejectIfEmpty(errors,"foundation.name","error.user.foundation.name.empty");
        ValidationUtils.rejectIfEmpty(errors,"foundation.ownerName","error.user.foundation.ownerName.empty");
        ValidationUtils.rejectIfEmpty(errors,"foundation.ownerSurname","error.user.foundation.ownerSurname.empty");
        ValidationUtils.rejectIfEmpty(errors,"address.city","error.user.address.city.empty");
        ValidationUtils.rejectIfEmpty(errors,"address.postcode","error.user.address.postcode.empty");
        ValidationUtils.rejectIfEmpty(errors,"address.street","error.user.address.street.empty");
        ValidationUtils.rejectIfEmpty(errors,"address.numberOfHome","error.user.address.numberOfHome.empty");

    }

    public  void validateEmailExist(User user, Errors errors) {
        if (user != null) {
            errors.rejectValue("email", "error.user.email.exists");
        }
    }

    public  void validateUsernameExist(User user, Errors errors) {
        if (user != null) {
            errors.rejectValue("username", "error.user.username.exists");
        }
    }
}
