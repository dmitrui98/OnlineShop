package by.dmitrui98.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Администратор on 16.04.2017.
 */

@Controller
@RequestMapping(value = "/security")
public class AdminController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test() {
        return new ModelAndView("security/index.jsp");
    }
}
