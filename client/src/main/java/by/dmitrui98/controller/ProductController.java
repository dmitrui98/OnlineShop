package by.dmitrui98.controller;

import by.dmitrui98.entity.Product;
import by.dmitrui98.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 24.04.2017.
 */
@Controller
@RequestMapping("/productController")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/put")
    public String putInPottle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (session.getAttribute("pottleProducts") == null) {
            session.setAttribute("pottleProducts", new ArrayList<Product>());
        }

        long id = Long.parseLong(request.getParameter("index")) + 1;

        Product product = (Product) productService.getById(id);
        List<Product> pottleProducts = (List<Product>) session.getAttribute("pottleProducts");

        if (pottleProducts.contains(product)) {
            int i = pottleProducts.indexOf(product);
            Product p = pottleProducts.get(i);
            p.setCountPottleProducts(p.getCountPottleProducts() + 1);
        } else {
            pottleProducts.add(product);
            product.setCountPottleProducts(product.getCountPottleProducts() + 1);
        }

        return "redirect:/";

    }

    @RequestMapping("/delete")
    public String deleteFromPottle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Product> pottleProducts = (List<Product>) session.getAttribute("pottleProducts");

        long index = Long.parseLong(request.getParameter("index"));
        pottleProducts.remove((int) index);

        return "redirect:/pottle";
    }
}
