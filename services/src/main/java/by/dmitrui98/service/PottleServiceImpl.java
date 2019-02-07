package by.dmitrui98.service;

import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Service
public class PottleServiceImpl implements PottleService<Product> {

    @Autowired
    private ProductService productService;

    @Override
    public double getAmount(List<Product> pottleProducts) {
        double amount = 0;
        if (pottleProducts != null) {
            Iterator<Product> iterator = pottleProducts.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                amount += product.getCountProductsInBasket() * product.getPrice();
            }
        }
        return amount;
    }

    public void putInPottle(List<Product> pottleProducts, long productId) {

        Product puttingProduct = productService.getById(productId);

        Product pottleProduct = getProduct(pottleProducts.iterator(), puttingProduct);
        if (pottleProduct != null)
            pottleProduct.setCountProductsInBasket(pottleProduct.getCountProductsInBasket() + 1);
        else {
            pottleProducts.add(puttingProduct);
            puttingProduct.setCountProductsInBasket(puttingProduct.getCountProductsInBasket() + 1);
        }
    }

    public void removeFromPottleAll(List<Product> pottleProducts, long removedProductId) {
        Iterator<Product> pottleProductsIterator = pottleProducts.iterator();

        while (pottleProductsIterator.hasNext()) {
            Product product = pottleProductsIterator.next();
            if (product.getProductId() == removedProductId) {
                pottleProductsIterator.remove();
                break;
            }
        }
    }

    public void removeFromPottle(List<Product> pottleProducts, long removedProductId) {
        Iterator<Product> pottleProductsIterator = pottleProducts.iterator();

        while (pottleProductsIterator.hasNext()) {
            Product product = pottleProductsIterator.next();
            if (product.getProductId() == removedProductId) {

                product.setCountProductsInBasket(product.getCountProductsInBasket() - 1);
                if (product.getCountProductsInBasket() == 0)
                    pottleProductsIterator.remove();

                break;
            }
        }
    }

    private Product getProduct(Iterator<Product> iterator, Product p) {
        Product product;
        while (iterator.hasNext()) {
            product = iterator.next();
            if (product.equals(p)) {
                return product;
            }
        }
        return null;
    }
}
