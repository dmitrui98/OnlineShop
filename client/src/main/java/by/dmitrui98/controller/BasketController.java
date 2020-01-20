package by.dmitrui98.controller;

import by.dmitrui98.dto.BasketDto;
import by.dmitrui98.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 24.04.2017.
 */
@Controller
@RequestMapping("/pottle")
public class BasketController {


    @Autowired
    private BasketService basketService;

    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpServletRequest request) {
        System.out.println("Changes from buidos98!!!");


        HttpSession session = request.getSession();
        List<BasketDto> basket = (List<BasketDto>) session.getAttribute("basket");

        double sum = basketService.getSum(basket);
        session.setAttribute("sum", sum);

        return "basket";
    }

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public void putInPottle(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<BasketDto> basket = (List<BasketDto>) session.getAttribute("basket");
        if (basket == null) {
            basket = new ArrayList<>();
            session.setAttribute("basket", basket);
        }

        long productId = Long.parseLong(request.getParameter("id"));


        basketService.putInBasket(basket, productId);

    }

    @RequestMapping("/deleteAll")
    public String deleteFromBasketAll(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        List<BasketDto> basket = (List<BasketDto>) session.getAttribute("basket");
        long id = Long.parseLong(request.getParameter("id"));

        basketService.removeFromBasketAll(basket, id);

        double sum = basketService.getSum(basket);
        session.setAttribute("sum", sum);

        response.setStatus(200);

        return "basketProducts";
    }

    @RequestMapping("/delete")
    public String deleteFromBasketOne(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        List<BasketDto> basket = (List<BasketDto>) session.getAttribute("basket");
        long id = Long.parseLong(request.getParameter("id"));

        basketService.removeFromBasketOne(basket, id);

        double sum = basketService.getSum(basket);
        session.setAttribute("sum", sum);

        response.setStatus(200);

        return "pottleProducts";
    }

    @RequestMapping(value = "issueOrder", method = RequestMethod.GET)
    public String issueOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<BasketDto> basket = (List<BasketDto>) session.getAttribute("basket");

        return "ordering";
    }
}
