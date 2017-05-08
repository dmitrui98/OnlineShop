package by.dmitrui98.controller.security;

import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.Materia;
import by.dmitrui98.service.dao.AdminService;
import by.dmitrui98.service.dao.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/security/materia")
public class MateriaController {
    @Autowired
    MateriaService materiaService;

    @Autowired
    AdminService adminService;

    @RequestMapping(method = RequestMethod.GET)
    public String editMateriaPage(Model model) {

        model.addAttribute("materias", materiaService.getAll());

        return "/security/materia";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategory(Model model) {
        model.addAttribute("materia", new Materia());

        return "/security/materiaAdd";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCategory(HttpServletRequest request, HttpServletResponse response, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("materia", materiaService.getById(id));

        return "/security/materiaEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("materia") Materia materia, Model model, HttpServletRequest request) {
        String adminName = request.getUserPrincipal().getName();
        Admin admin = adminService.getByName(adminName);

        try {
            materia.setName(new String (materia.getName().getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        boolean isEdit = true;
        if (materia.getMateriaId() == 0)
            isEdit = false;

        materia.setAdmin(admin);
        materiaService.save(materia);

        if (isEdit)
            return "redirect:/security/materia";
        else
            return "redirect:/security/materia/add";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        materiaService.remove(id);

        return "redirect:/security/materia";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


}
