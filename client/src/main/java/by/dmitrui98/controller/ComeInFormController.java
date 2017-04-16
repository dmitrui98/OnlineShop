package by.dmitrui98.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Администратор on 14.04.2017.
 */
@Controller
public class ComeInFormController{

    @RequestMapping(value = "/comeIn", method = RequestMethod.POST)
    public ModelAndView comeInAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("comeInAction");

        response.setContentType ("text/html; charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String login2 = "testValue";
        if (login != null) {
            request.getSession().setAttribute("login2", login);
        }
        login2 = (String) request.getSession().getAttribute("login2");

        System.out.println("Пользователь пытается войти:");
        System.out.println("login: " + login + " password" + password);



//        request.getRequestDispatcher("/comeIn").forward(request, response);

        request.setAttribute("login", login);
        request.setAttribute("login2", login2);
        response.setStatus(200);
        return new ModelAndView("comeIn");
        //response.getWriter().println("<html><body><p>" + login + "</p></body></html>");
    }
}
