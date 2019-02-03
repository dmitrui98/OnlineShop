package by.dmitrui98.controller.rest;

import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Администратор on 16.04.2017.
 */
//@RestController
public class ProductRestController {
    @Autowired
    private BaseService<Product, Long> productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getProducts() {
        List<Product> list = productService.getAll();
        return list;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") long userId) {
        return (Product) productService.getById((Long) userId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public void saveProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void removeProduct(@PathVariable("id") Long productId) {
        productService.remove(productId);
    }
}
