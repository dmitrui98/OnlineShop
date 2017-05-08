package by.dmitrui98.editor;

import by.dmitrui98.entity.Category;
import by.dmitrui98.service.dao.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

/**
 * Created by Администратор on 05.05.2017.
 */
public class CategoryEditor extends PropertyEditorSupport {

    private CategoryService categoryService;

    public CategoryEditor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @Override
//    public String getAsText() {
//        Object o = getValue();
//
//        if (o != null) {
//            return String.valueOf(( (Category) o).getCategoryId());
//        } else
//            return null;
//    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && text.trim().length() > 0) {
            int id = Integer.parseInt(text);
            Category category = categoryService.getById(id);
            if (category != null)
                setValue(category);
            else
                throw new IllegalArgumentException("No activity with ID " + text);
        } else
            setValue(null);
    }
}
