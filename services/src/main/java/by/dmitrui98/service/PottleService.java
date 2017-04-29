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
public class PottleService {

    @Autowired
    private ProductService productService;

    public void putInPottle(List<Product> pottleProducts, long productIndex) {

        Product puttingProduct = getProduct(productService.getAll().iterator(), productIndex);

        Product pottleProduct = getProduct(pottleProducts.iterator(), puttingProduct);
        if (pottleProduct != null)
            pottleProduct.setCountPottleProducts(pottleProduct.getCountPottleProducts() + 1);
        else {
            pottleProducts.add(puttingProduct);
            puttingProduct.setCountPottleProducts(puttingProduct.getCountPottleProducts() + 1);
        }
    }

    public void removeFromPottle(List<Product> pottleProducts, long removedProductIndex) {
        Iterator<Product> pottleProductsIterator = pottleProducts.iterator();

        long i = -1;
        while (pottleProductsIterator.hasNext()) {
            Product product = pottleProductsIterator.next();
            i++;
            if (i == removedProductIndex) {
                pottleProductsIterator.remove();
                break;
            }
        }
    }


    private Product getProduct(Iterator<Product> iterator, long index) {
        Product product = null;
        long i = 0;
        while (iterator.hasNext() && i <= index) {
            product = iterator.next();
            i++;
        }
        return product;
    }

    private Product getProduct(Iterator<Product> iterator, Product p) {
        Product product = null;
        while (iterator.hasNext()) {
            product = iterator.next();
            if (product.equals(p)) {
                return product;
            }
        }
        return null;
    }
}
