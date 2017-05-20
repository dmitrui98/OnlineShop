package by.dmitrui98.validation;

import by.dmitrui98.entity.Product;
import by.dmitrui98.entity.ProductMaterial;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Iterator;

/**
 * Created by Администратор on 20.05.2017.
 */
@Component
public class ProductValidator implements Validator {
    private static final double expectedPercent = 100.0;

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (product.getCategory() == null)
            errors.rejectValue("category", "Empty.productForm.category");

        if (product.getProductMaterials() != null && product.getProductMaterials().size() > 0) {
            double actualPercent = 0.0;
            Iterator<ProductMaterial> iterator = product.getProductMaterials().iterator();
            while (iterator.hasNext()) {
                ProductMaterial productMaterial = iterator.next();
                actualPercent += productMaterial.getPercentMaterial();
            }
            if (expectedPercent != actualPercent)
                errors.rejectValue("productMaterials", "Wrong.productForm.amountOfPercents");
        }
    }
}
