package by.dmitrui98.controller.admin;

import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.Material;
import by.dmitrui98.service.dao.AdminService;
import by.dmitrui98.service.dao.MaterialService;
import by.dmitrui98.validation.MaterialValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/admin/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private MaterialValidator materialValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String editMaterialPage(Model model) {

        model.addAttribute("materials", materialService.getAll());

        return "admin/material";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategory(Model model) {
        model.addAttribute("material", new Material());

        return "admin/materialAdd";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCategory(HttpServletRequest request, HttpServletResponse response, Model model) {
        Long id = Long.parseLong(request.getParameter("id"));
        model.addAttribute("material", materialService.getById(id));

        return "admin/materialEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("material") Material material, BindingResult bindingResult, HttpServletRequest request) {

        boolean isEdit = true;
        if (material.getMaterialId() == 0)
            isEdit = false;

        materialValidator.validate(material, bindingResult);
        if (bindingResult.hasErrors()) {
            if (isEdit)
                return "admin/materialEdit";
            else
                return "admin/materialAdd";
        }

        String adminName = request.getUserPrincipal().getName();
        Admin admin = adminService.getByName(adminName);

        material.setAdmin(admin);
        materialService.save(material);

        if (isEdit)
            return "redirect:/admin/material";
        else
            return "redirect:/admin/material/add";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));

        if (materialService.remove(id))
            return "redirect:/admin/material";
        else {
            response.setStatus(304); // not modified
            return "admin/material";
        }
    }

    @RequestMapping(value = "/deleteCascade", method = RequestMethod.POST)
    public String deleteCascade(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        materialService.removeCascade(id);

        return "redirect:/admin/material";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


}
