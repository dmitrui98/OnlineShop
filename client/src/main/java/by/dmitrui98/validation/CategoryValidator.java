package by.dmitrui98.validation;

import by.dmitrui98.entity.Category;
import by.dmitrui98.service.dao.CategoryService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Администратор on 20.05.2017.
 */
@Component
public class CategoryValidator implements Validator {

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (categoryService.getByName(category.getName()) != null) {
            errors.rejectValue("name", "Dublicate.categoryForm.name");
        }
    }
}
