package by.dmitrui98.controller.json;

import by.dmitrui98.controller.MainController;
import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Администратор on 19.11.2018.
 */
@RestController
public class MainRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/countPages", method = RequestMethod.GET)

    public Map<String, Integer> countProductPages(@RequestParam(name = "countElements", defaultValue = "10")
                                                 int countElementsPerPage) {
        long productCount = productService.getCount();

        int countPages = (int) productCount / countElementsPerPage;
        if (productCount % countElementsPerPage != 0) {
            countPages += 1;
        }
        Map<String, Integer> map = new HashMap<>(2);
        map.put("countPages", countPages);
        map.put("maxPages", MainController.maxPages);
        return map;
    }
}
