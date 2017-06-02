package by.dmitrui98.validation;

import by.dmitrui98.entity.Material;
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

            if (productHasDublicatesInMaterial(product))
                errors.rejectValue("productMaterials", "Dublicate.productForm.materials");

            double actualPercent = calculatePersants(product);
            if (expectedPercent != actualPercent)
                errors.rejectValue("productMaterials", "Wrong.productForm.amountOfPercents");
        }
    }

    private boolean productHasDublicatesInMaterial(Product product) {
        Iterator<ProductMaterial> iterator = product.getProductMaterials().iterator();
        int countMatches = 0;
        while (iterator.hasNext()) {
            Material material = iterator.next().getMaterial();
            Iterator<ProductMaterial> iterator1 = product.getProductMaterials().iterator();
            while (iterator1.hasNext()) {
                Material material1 = iterator1.next().getMaterial();
                if (material.getName().equals(material1.getName()))
                    countMatches++;
                if (countMatches > 1)
                    return true;
            }
            countMatches = 0;
        }
        return false;
    }

    private double calculatePersants(Product product) {
        Iterator<ProductMaterial> iterator = product.getProductMaterials().iterator();
        double actualPercent = 0.0;
        while (iterator.hasNext()) {
            ProductMaterial productMaterial = iterator.next();
            actualPercent += productMaterial.getPercentMaterial();
        }
        return actualPercent;
    }
}
