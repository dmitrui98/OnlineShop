package by.dmitrui98.validation;

import by.dmitrui98.entity.User;
import by.dmitrui98.service.AdminService;
import by.dmitrui98.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Администратор on 18.04.2017.
 */
@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (errors.getFieldError("login") == null) {
            if (user.getLogin().length() < 4 || user.getLogin().length() > 32) {
                errors.rejectValue("login", "Size.userForm.login");
            }
        }

        // проверить дубликаты с таблицы admin!!!
        if ( (userService.getByName(user.getLogin()) != null) || (adminService.getByName(user.getLogin()) != null)) {
            errors.rejectValue("login", "Duplicate.userForm.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (errors.getFieldError("password") == null) {
            if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.userForm.password");
            }
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (errors.getFieldError("email") == null) {
            if (user.getEmail().length() < 4 || user.getEmail().length() > 32) {
                errors.rejectValue("email", "Size.userForm.email");
            }
        }
    }
}
