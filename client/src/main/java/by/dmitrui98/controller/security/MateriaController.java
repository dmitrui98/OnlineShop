package by.dmitrui98.controller.security;

import by.dmitrui98.entity.Materia;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/security/materia")
public class MateriaController {

    @RequestMapping(method = RequestMethod.GET)
    public String editMateriaPage(Model model) {
        model.addAttribute("materia", new Materia());

        return "/security/materia";
    }
}
