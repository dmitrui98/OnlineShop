package by.dmitrui98.controller.security;

import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.Material;
import by.dmitrui98.service.dao.AdminService;
import by.dmitrui98.service.dao.MaterialService;
import by.dmitrui98.validation.MaterialValidator;
import org.apache.log4j.Logger;
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
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/security/material")
public class MaterialController {
    private static final Logger logger = Logger.getLogger(MaterialController.class);
    @Autowired
    private MaterialService materialService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private MaterialValidator materialValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String editMaterialPage(Model model) {

        model.addAttribute("materials", materialService.getAll());

        return "/security/material";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategory(Model model) {
        model.addAttribute("material", new Material());

        return "/security/materialAdd";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCategory(HttpServletRequest request, HttpServletResponse response, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("material", materialService.getById(id));

        return "/security/materialEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("material") Material material, BindingResult bindingResult, HttpServletRequest request) {
        try {
            String name = new String (material.getName().getBytes("ISO-8859-1"), "UTF-8").toLowerCase();
            material.setName(name);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException (/security/material)", e);
        }

        boolean isEdit = true;
        if (material.getMaterialId() == 0)
            isEdit = false;

        materialValidator.validate(material, bindingResult);
        if (bindingResult.hasErrors()) {
            if (isEdit)
                return "/security/materialEdit";
            else
                return "/security/materialAdd";
        }

        String adminName = request.getUserPrincipal().getName();
        Admin admin = adminService.getByName(adminName);

        material.setAdmin(admin);
        materialService.save(material);

        if (isEdit)
            return "redirect:/security/material";
        else
            return "redirect:/security/material/add";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        if (materialService.remove(id))
            return "redirect:/security/material";
        else {
            response.setStatus(304); // not modified
            return "security/material";
        }
    }

    @RequestMapping(value = "/deleteCascade", method = RequestMethod.POST)
    public String deleteCascade(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        materialService.removeCascade(id);

        return "redirect:/security/material";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


}
