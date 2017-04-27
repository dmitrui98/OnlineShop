package by.dmitrui98.validation;

import by.dmitrui98.entity.User;
import by.dmitrui98.service.AdminService;
import by.dmitrui98.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (errors.getFieldError("email") == null) {

            if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
                errors.rejectValue("email", "Size.userForm.email");
            }

            String email = user.getEmail();
            Pattern p = Pattern.compile(".+@.+\\...+");
            Matcher m = p.matcher(email);

            if (!m.matches()) {
                errors.rejectValue("email", "Wrong.userForm.email");
            }

            if ((userService.getByEmail(user.getEmail()) != null) || (adminService.getByEmail(user.getEmail()) != null)) {
                errors.rejectValue("email", "Duplicate.userForm.email");
            }
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

        Pattern p = Pattern.compile("\\+?[0-9]+");
        Matcher m = p.matcher(user.getPhone());
        if ((!m.matches()) && (user.getPhone().length() > 0)) {
            errors.rejectValue("phone", "Wrong.userForm.phone");
        }
    }
}
