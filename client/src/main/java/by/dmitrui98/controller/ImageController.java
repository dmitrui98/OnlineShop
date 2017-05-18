package by.dmitrui98.controller;

import by.dmitrui98.service.dao.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Администратор on 16.05.2017.
 */
@Controller
@RequestMapping(value = "/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @ResponseBody
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public byte[] getPhoto(@PathVariable("name") String imageName ) {

        return imageService.read(imageName);
    }


}
