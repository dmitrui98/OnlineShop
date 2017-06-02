package by.dmitrui98.controller.security;

import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.Category;
import by.dmitrui98.service.dao.AdminService;
import by.dmitrui98.service.dao.CategoryService;
import by.dmitrui98.validation.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/security/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryValidator categoryValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String editCategoryPage(Model model) {

        model.addAttribute("categories", categoryService.getAll());

        return "/security/category";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategory(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("category", new Category());

        return "/security/categoryAdd";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editCategory(HttpServletRequest request, HttpServletResponse response, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("category", categoryService.getById(id));

        return "/security/categoryEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("category") Category category, BindingResult bindingResult, HttpServletRequest request) {
        try {
            category.setName(new String (category.getName().getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        boolean isEdit = true;
        if (category.getCategoryId() == 0)
            isEdit = false;

        categoryValidator.validate(category, bindingResult);
        if (bindingResult.hasErrors()) {
            if (isEdit)
                return "/security/categoryEdit";
            else
                return "/security/categoryAdd";
        }

        String adminName = request.getUserPrincipal().getName();
        Admin admin = adminService.getByName(adminName);

        category.setAdmin(admin);
        categoryService.save(category);

        if (isEdit)
            return "redirect:/security/category";
        else
            return "redirect:/security/category/add";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        if (categoryService.remove(id))
            return "redirect:/security/category";
        else {
            response.setStatus(304); // не изменялось
            return "security/category";
        }
    }

    @RequestMapping(value = "/deleteCascade", method = RequestMethod.POST)
    public String deleteCascade(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.removeCascade(id);

        return "redirect:/security/category";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


}
