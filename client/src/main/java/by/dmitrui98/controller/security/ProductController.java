package by.dmitrui98.controller.security;

import by.dmitrui98.editor.CategoryEditor;
import by.dmitrui98.editor.ImageEditor;
import by.dmitrui98.entity.*;
import by.dmitrui98.service.ImageService;
import by.dmitrui98.service.dao.AdminService;
import by.dmitrui98.service.dao.CategoryService;
import by.dmitrui98.service.dao.MateriaService;
import by.dmitrui98.service.dao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/security/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MateriaService materiaService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public String productPage(Model model) {
        model.addAttribute("products", productService.getAll());
        return "/security/product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String productAddPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("materias", materiaService.getAll());
        model.addAttribute("materiasInProduct", new ArrayList<Materia>());

        return "/security/productAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String productAdd(@ModelAttribute("product") Product productForm,
                             HttpServletRequest request){//, @RequestParam("image") MultipartFile image) {
        Admin admin = adminService.getByName(request.getUserPrincipal().getName());
        productForm.setAdmin(admin);

        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] stringMateriaIds = parameterMap.get("materiaId[]");
        String[] stringPersants = parameterMap.get("persant[]");

        int[] materiaIds = new int[stringMateriaIds.length];
        double[] persants = new double[stringPersants.length];

        for(int i = 0; i < stringMateriaIds.length; i++) {
            materiaIds[i] = Integer.parseInt(stringMateriaIds[i]);
            persants[i] = Double.parseDouble(stringPersants[i]);
        }

        productService.save(productForm, materiaIds, persants);

        return "redirect:/security/product/add";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String productViewPage(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        File file = new File(product.getImage().getImageDirectory());
        System.out.println(file);
        return "/security/productView";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String productEditPage(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        model.addAttribute("product", productService.getById(id));

        return "/security/productEdit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String productDelete(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));

        productService.remove(id);

        return "/security/product";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        binder.registerCustomEditor(Image.class, new ImageEditor(imageService));
    }
}
