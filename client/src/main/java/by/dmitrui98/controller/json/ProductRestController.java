package by.dmitrui98.controller.json;

import by.dmitrui98.entity.Product;
import by.dmitrui98.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Администратор on 16.04.2017.
 */
@RestController
public class ProductRestController {
    @Autowired
    private BaseService<Product, Long> productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getUsers() {
        List<Product> list = productService.getAll();
        return list;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getUser(@PathVariable("id") long userId) {
        return (Product) productService.getById((Long) userId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public void saveUser(@RequestBody Product product) {
        productService.save(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("id") Long productId) {
        productService.remove(productId);
    }
}
