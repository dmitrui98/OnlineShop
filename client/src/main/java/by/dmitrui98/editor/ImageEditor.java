package by.dmitrui98.editor;

import by.dmitrui98.entity.Image;
import by.dmitrui98.service.dao.ImageService;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

/**
 * Created by Администратор on 08.05.2017.
 */
public class ImageEditor extends PropertyEditorSupport {

    private ImageService imageService;

    public ImageEditor(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof MultipartFile) {
            MultipartFile multipartFile = (MultipartFile) value;

            Image image = null;
            try {
                image = imageService.write(multipartFile.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            super.setValue(image);
        } else
            super.setValue(value);
    }
}
