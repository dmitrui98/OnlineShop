package by.dmitrui98.service;

import by.dmitrui98.dao.implementation.ImageDao;
import by.dmitrui98.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Администратор on 08.05.2017.
 */
@Service
public class ImageService {

    private static long countImages = 1;
    private static final String dir = "/server/file_storage/images/";
    private static final String serverDir = "/images/";

    @Autowired
    ImageDao imageDao;

    public Image save(byte[] bytes) {

        Image image = null;
        File folder = new File(dir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String imageName = format.format(new Date()) + ".jpg";

        try {
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(folder, imageName)));
            stream.write(bytes);
            stream.close();

            image = imageDao.save(serverDir+imageName);

            countImages++;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return image;
    }
}
