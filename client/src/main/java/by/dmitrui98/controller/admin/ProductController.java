package by.dmitrui98.controller.admin;

import by.dmitrui98.editor.CategoryEditor;
import by.dmitrui98.editor.ImageEditor;
import by.dmitrui98.entity.*;
import by.dmitrui98.service.dao.*;
import by.dmitrui98.validation.ProductValidator;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller("AdminProductController")
@RequestMapping(value = "/admin/product")
public class ProductController {

    @Autowired
    private ProductValidator productValidator;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public String productPage(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin/product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String productAddPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("materials", materialService.getAll());
        model.addAttribute("materialsInProduct", new ArrayList<Material>());

        return "admin/productAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String productAdd(@ModelAttribute("product") Product productForm,
                          BindingResult bindingResult, HttpServletRequest request, Model model){

        boolean isEdit = true;
        if (productForm.getProductId() == 0)
            isEdit = false;

        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] stringMaterialIds = parameterMap.get("materialId[]");
        String[] stringPercents = parameterMap.get("percent[]");

        productService.setProductMaterias(productForm, stringMaterialIds, stringPercents);


        productValidator.validate(productForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", productForm);
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("materials", materialService.getAll());

            if (isEdit && productForm.getImage() == null) {
                String imageDirectory = request.getParameter("imageDirectory");
                long imageId = Long.parseLong(request.getParameter("imageId"));

                Image image = new Image(imageDirectory);
                image.setImageId(imageId);
                productForm.setImage(image);
            }

            if (isEdit)
                return "admin/productEdit";
            else {
                if (productForm.getImage() != null)
                    imageService.remove(productForm.getImage().getImageDirectory());
                return "admin/productAdd";
            }
        }

        Admin admin = adminService.getByName(request.getUserPrincipal().getName());
        productForm.setAdmin(admin);
        if (isEdit) {
            String imageDirectory = request.getParameter("imageDirectory");
            long imageId = Long.parseLong(request.getParameter("imageId"));

            productService.save(productForm, stringMaterialIds, stringPercents,
                    imageDirectory, imageId);
        }
        else
            productService.save(productForm, stringMaterialIds, stringPercents);


        if (isEdit)
            return "redirect:/admin/product";
        else
            return "redirect:/admin/product/add";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String productViewPage(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "admin/productView";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String productEditPage(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("materials", materialService.getAll());

        return "admin/productEdit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String productDelete(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));

        productService.remove(id);

        model.addAttribute("products", productService.getAll());
        return "admin/product";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

        binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        binder.registerCustomEditor(Image.class, new ImageEditor(imageService));
    }


}
