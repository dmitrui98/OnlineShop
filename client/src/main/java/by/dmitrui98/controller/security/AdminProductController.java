package by.dmitrui98.controller.security;

import by.dmitrui98.editor.CategoryEditor;
import by.dmitrui98.editor.ImageEditor;
import by.dmitrui98.entity.*;
import by.dmitrui98.service.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Администратор on 29.04.2017.
 */
@Controller
@RequestMapping(value = "/security/product")
public class AdminProductController {

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
        return "/security/product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String productAddPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("materials", materialService.getAll());
        model.addAttribute("materialsInProduct", new ArrayList<Material>());

        return "/security/productAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String productAdd(@ModelAttribute("product") Product productForm,
                             HttpServletRequest request){

        boolean isEdit = true;
        if (productForm.getProductId() == 0)
            isEdit = false;


        if (isEdit) {
            String imageDirectory = request.getParameter("imageDirectory");
            long imageId = Long.parseLong(request.getParameter("imageId"));

            if (productForm.getImage() == null && imageDirectory != null) {
                Image image = new Image(imageDirectory);
                image.setImageId(imageId);
                productForm.setImage(image);
            }
        }

        Admin admin = adminService.getByName(request.getUserPrincipal().getName());
        productForm.setAdmin(admin);

        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] stringMaterialIds = parameterMap.get("materialId[]");
        String[] stringPersents = parameterMap.get("persent[]");

        int[] materialIds = new int[stringMaterialIds.length];
        double[] persents = new double[stringPersents.length];

        for(int i = 0; i < stringMaterialIds.length; i++) {
            materialIds[i] = Integer.parseInt(stringMaterialIds[i]);
            persents[i] = Double.parseDouble(stringPersents[i]);
        }

        productService.save(productForm, materialIds, persents);


        if (isEdit)
            return "redirect:/security/product";
        else
            return "redirect:/security/product/add";
    }

//    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String productViewPage(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "/security/productView";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String productEditPage(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("materials", materialService.getAll());

        return "/security/productEdit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String productDelete(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));

        productService.remove(id);

        model.addAttribute("products", productService.getAll());
        return "/security/product";
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
