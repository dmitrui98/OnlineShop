package by.dmitrui98.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Администратор on 08.04.2017.
 */
@Controller
@RequestMapping(value = "/")
public class MainFormController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showMainPage() {
        ModelAndView modelAndView = new ModelAndView("mainPage");
        return modelAndView;
    }

    @RequestMapping(value = "/comeIn", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showComeInForm() {
        ModelAndView modelAndView = new ModelAndView("comeIn");
        return modelAndView;
    }

    @RequestMapping(value = "/pottle", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showPottleForm() {
        ModelAndView modelAndView = new ModelAndView("pottle");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("registration");
        return modelAndView;
    }




}
