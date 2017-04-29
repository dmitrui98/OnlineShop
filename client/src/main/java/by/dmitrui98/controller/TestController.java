package by.dmitrui98.controller;

import by.dmitrui98.service.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Администратор on 14.04.2017.
 */
@Controller
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/test")
    public String registrationAction(Model model, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("1", "cooka");
        response.addCookie(cookie);
        cookie.setMaxAge(3600*24);

        HttpSession session = request.getSession();
        session.setAttribute("users", userService.getAll());
        return "test";
    }
}
