package by.dmitrui98.controller;

import by.dmitrui98.service.dao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Администратор on 09.05.2017.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String infoPage(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        model.addAttribute("product", productService.getById(id));

        return "productView";
    }

    @RequestMapping(value = "/infoPottle", method = RequestMethod.GET)
    public String infoPottlePage(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        model.addAttribute("product", productService.getById(id));

        return "productViewPottle";
    }
}
