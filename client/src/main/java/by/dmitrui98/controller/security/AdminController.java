package by.dmitrui98.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Администратор on 16.04.2017.
 */

@Controller
public class AdminController {

    @RequestMapping(value = "/security", method = RequestMethod.GET)
    public ModelAndView test() {
        return new ModelAndView("security/index.jsp");
    }

//    @RequestMapping(value = "/protected**", method = RequestMethod.GET)
//    public ModelAndView protectedPage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security 3.2.4 Hello World Tutorial");
//        model.addObject("message", "This is protected page - Only for Admin Users!");
//        model.setViewName("protected");
//        return model;
//
//    }
//
//    @RequestMapping(value = {"/", "/helloworld**"}, method = {RequestMethod.GET})
//    public ModelAndView welcomePage() {
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Tutorial");
//        model.addObject("message", "Welcome Page !");
//        model.setViewName("helloworld");
//        return model;
//    }

}
