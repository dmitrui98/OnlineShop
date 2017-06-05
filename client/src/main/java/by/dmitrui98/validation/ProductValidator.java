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
        if (errors.getFieldError("name") == null)
            if (product.getName().length() < 3 || product.getName().length() > 32)
                errors.rejectValue("name", "Size.name");

        if (product.getDescription().length() > 100)
            errors.rejectValue("description", "Size.productForm.description");

        if (product.getCategory() == null)
            errors.rejectValue("category", "Empty.productForm.category");

        if (product.getPrice() < 0)
            errors.rejectValue("price", "Wrong.productForm.price");

        if (product.getProductMaterials() != null && product.getProductMaterials().size() > 0) {

            if (productHasDublicatesInMaterial(product))
                errors.rejectValue("productMaterials", "Dublicate.productForm.materials");

            if (haveWrongPercent(product))
                errors.rejectValue("productMaterials", "Wrong.productForm.wrongPercent");

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

    private boolean haveWrongPercent(Product product) {
        Iterator<ProductMaterial> iterator = product.getProductMaterials().iterator();
        while (iterator.hasNext()) {
            ProductMaterial productMaterial = iterator.next();
            if (productMaterial.getPercentMaterial() <= 0)
                return true;
        }
        return false;
    }

    private double calculatePersants(Product product) {
        Iterator<ProductMaterial> iterator = product.getProductMaterials().iterator();
        double actualPercent = 0.0;
        while (iterator.hasNext()) {
            ProductMaterial productMaterial = iterator.next();
            if (productMaterial.getPercentMaterial() > 0)
                actualPercent += productMaterial.getPercentMaterial();
        }
        return actualPercent;
    }
}
