package by.dmitrui98.controller.security;

import by.dmitrui98.entity.Category;
import by.dmitrui98.service.dao.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/security/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String editCategoryPage(HttpServletRequest request, Model model) {

        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAll());

        return "/security/category";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editCategory(@PathVariable("id") int id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("categories", categoryService.getAll());

        return "/security/category";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addOrUpdate(@ModelAttribute("category") Category category, Model model) {
        try {
            category.setName(new String (category.getName().getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(category);
        categoryService.save(category);

        return "redirect:/security/category";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String addOrUpdate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        categoryService.remove(id);

        return "/security/category";
    }


}
