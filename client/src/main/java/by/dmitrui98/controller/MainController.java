package by.dmitrui98.controller;

import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Администратор on 08.04.2017.
 */
@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMainPage(Model model) {

        List<Product> products = productService.getAll();
        model.addAttribute("products", products);

        return "mainPage";
    }

    @RequestMapping(value = "/pottle", method = RequestMethod.GET)
    //@ResponseBody
    public ModelAndView showPottleForm() {
        ModelAndView modelAndView = new ModelAndView("pottle");
        return modelAndView;
    }

}
