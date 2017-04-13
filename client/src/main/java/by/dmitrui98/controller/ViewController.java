package by.dmitrui98.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Администратор on 08.04.2017.
 */
//@Controller
public class ViewController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showMainPage() {
        return new ModelAndView("mainPage");
    }


}
