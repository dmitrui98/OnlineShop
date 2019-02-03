package by.dmitrui98.controller.admin;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryValidator categoryValidator;

    @GetMapping
    public String editCategoryPage(Model model) {

        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);

        return "admin/category";
    }

    @PostMapping
    public String addOrUpdate(@ModelAttribute("category") Category category, BindingResult bindingResult, HttpServletRequest request) {

        boolean isEdit = true;
        if (category.getCategoryId() == 0)
            isEdit = false;

        categoryValidator.validate(category, bindingResult);
        if (bindingResult.hasErrors()) {
            if (isEdit)
                return "admin/categoryEdit";
            else
                return "admin/categoryAdd";
        }

        String adminName = request.getUserPrincipal().getName();
        Admin admin = adminService.getByName(adminName);

        category.setAdmin(admin);
        categoryService.save(category);

        if (isEdit)
            return "redirect:/admin/category";
        else
            return "redirect:/admin/category/add";
    }

    @GetMapping(value = "/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categoryAdd";
    }

    @GetMapping(value = "/edit")
    public String editCategory(HttpServletRequest request, HttpServletResponse response, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("category", categoryService.getById(id));
        return "admin/categoryEdit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        if (categoryService.remove(id))
            return "redirect:/admin/category";
        else {
            response.setStatus(304); // не изменялось
            return "admin/category";
        }
    }

    @RequestMapping(value = "/deleteCascade", method = RequestMethod.POST)
    public String deleteCascade(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.removeCascade(id);

        return "redirect:/admin/category";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


}
