package by.dmitrui98.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Администратор on 16.04.2017.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mainAdminPage() {
        return new ModelAndView("admin/index");
    }

    @RequestMapping(value = "image", method = RequestMethod.GET)
    public String imageUpload() {

        return "admin/image";
    }




}
