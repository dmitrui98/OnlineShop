package by.dmitrui98.controller;

import by.dmitrui98.entity.Product;
import by.dmitrui98.service.PottleService;
import by.dmitrui98.service.dao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Администратор on 24.04.2017.
 */
@Controller
@RequestMapping("/pottleController")
public class PottleController {


    @Autowired
    private PottleService pottleService;

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public void putInPottle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (session.getAttribute("pottleProducts") == null) {
            session.setAttribute("pottleProducts", new ArrayList<Product>());
        }

        long productId = Long.parseLong(request.getParameter("id"));
        List<Product> pottleProducts = (List<Product>) session.getAttribute("pottleProducts");

        pottleService.putInPottle(pottleProducts, productId);

    }

    @RequestMapping("/deleteAll")
    public String deleteFromPottleAll(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        List<Product> pottleProducts = (List<Product>) session.getAttribute("pottleProducts");
        long id = Long.parseLong(request.getParameter("id"));

        pottleService.removeFromPottleAll(pottleProducts, id);
        response.setStatus(200);

        return "pottleProducts";
    }

    @RequestMapping("/delete")
    public String deleteFromPottle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        List<Product> pottleProducts = (List<Product>) session.getAttribute("pottleProducts");
        long id = Long.parseLong(request.getParameter("id"));

        pottleService.removeFromPottle(pottleProducts, id);
        response.setStatus(200);

        return "pottleProducts";
    }
}
