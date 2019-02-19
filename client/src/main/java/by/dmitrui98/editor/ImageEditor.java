package by.dmitrui98.editor;

import by.dmitrui98.entity.Image;
import by.dmitrui98.service.dao.ImageService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

/**
 * Created by Администратор on 08.05.2017.
 */
@Log4j
@Component
public class ImageEditor extends PropertyEditorSupport {
    @Autowired
    private ImageService imageService;

    @Override
    public void setValue(Object value) {
        if (value instanceof MultipartFile) {
            MultipartFile multipartFile = (MultipartFile) value;

            Image image = null;
            try {
                image = imageService.write(multipartFile.getBytes());
            } catch (IOException ex) {
                log.error("Error in writing multipartFile", ex);
            }

            super.setValue(image);
        } else
            super.setValue(value);
    }
}
