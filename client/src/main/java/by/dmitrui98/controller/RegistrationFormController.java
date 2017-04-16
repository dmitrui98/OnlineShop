package by.dmitrui98.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Администратор on 14.04.2017.
 */
@Controller
public class RegistrationFormController {

    @RequestMapping(value = "/account/registration")
    public void registrationAction(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("registrationAction");
        System.out.println(request.getRequestURI());
    }
}
