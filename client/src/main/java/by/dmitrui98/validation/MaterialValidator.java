package by.dmitrui98.validation;

import by.dmitrui98.entity.Material;
import by.dmitrui98.service.dao.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Администратор on 20.05.2017.
 */
@Component
public class MaterialValidator implements Validator {
    @Autowired
    private MaterialService materialService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Material.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Material material = (Material) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (materialService.getByName(material.getName()) != null) {
            errors.rejectValue("name", "Dublicate.materialForm.name");
        }
    }
}
