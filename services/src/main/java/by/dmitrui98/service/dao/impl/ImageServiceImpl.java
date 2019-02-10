package by.dmitrui98.service.dao.impl;

import by.dmitrui98.dao.ImageDao;
import by.dmitrui98.entity.Image;
import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.ImageService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Администратор on 09.05.2017.
 */
@Service
@Log4j
public class ImageServiceImpl implements ImageService {

    private static final String dir = "server/file_storage/images/";
    private static final String DEFAULT_IMAGE_NAME = "default.jpg";
    private static final String SERVER_DIR = "/images/";
    private static String contextPath = null;


    @Autowired
    private ImageDao imageDao;

    public Image write(byte[] bytes) {
        Image image = null;
        if (bytes.length != 0) {
            File folder = createFolder(dir);

            SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
            String imageName = format.format(new Date()) + ".jpg";

            BufferedOutputStream stream = null;
            try {
                stream =
                        new BufferedOutputStream(new FileOutputStream(new File(folder, imageName)));
                stream.write(bytes);

                String contextPath = getContextPath();
                image = imageDao.save(contextPath + SERVER_DIR + imageName);

            } catch (IOException ex) {
                log.error("IOException in writing image. Image folder: " + folder.getAbsolutePath() +
                        "; image name" + imageName, ex);
            } finally {
                try {
                    if (stream != null)
                        stream.close();
                } catch (IOException e) {
                    log.error("IOException in closing BufferedOutputStream", e);
                }
            }
        } else {
            image = getDefaultImage();
        }

        return image;
    }

    @Override
    public byte[] read(String imageName) {
        File folder = createFolder(dir);
        File image = new File(folder, imageName + ".jpg");

        FileInputStream stream = null;
        try{

            if (image.exists()) {
                stream =
                        new FileInputStream(image);
            } else {
                log.error("Image with name " + imageName + " not found");
                stream =
                        new FileInputStream(new File(folder, DEFAULT_IMAGE_NAME));
            }

            return IOUtils.toByteArray(stream);
        } catch (IOException ex) {
            log.error("IOException in reading image. Image folder: " + folder.getAbsolutePath() +
                    "; image name" + imageName, ex);
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                log.error("IOException in closing FileInputStream", e);
            }
        }
        return null;
    }


    /**
     * получает изображение по умолчанию из базы. Если такого нет, создает transient объект
     *
     * @return default image
     */
    private Image getDefaultImage() {
        String contextPath = getContextPath();
        // TODO переместить изображение default.jpg из classpath, если оно не сущетсвует
        String defaultImagePath = contextPath + SERVER_DIR + DEFAULT_IMAGE_NAME;
        Image image = this.getImageByPath(defaultImagePath);
        return image == null ? new Image(defaultImagePath) : image;
    }

    private File createFolder(String directory) {
        File folder = new File(directory);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    @Override
    public boolean remove(String imagePath) {
        String contextPath = getContextPath();
        String imageName = imagePath.substring(SERVER_DIR.length() + contextPath.length());
        File folder = createFolder(dir);
        File image = new File(folder, imageName);

        boolean deleted = false;
        if (!imageName.equals(DEFAULT_IMAGE_NAME)) {
            deleted = image.delete();
            if (deleted) {
                log.info("Изображение: " + imagePath + " удалено успешно");
            } else {
                log.error("Изображение: " + imagePath + " не удалено!!!!");
            }
        }
        return deleted;
    }

    @Override
    public boolean removeImage(Product product) {
        String imageDir = product.getImage().getImageDirectory();
        return remove(imageDir);
    }

    @Override
    public Image getImageByPath(String defaultImagePath) {
        return imageDao.getByPath(defaultImagePath);
    }

    /**
     * Достает contextPath из request или возвращает текущее значение
     *
     * @return contextPath
     */
    private String getContextPath() {
        if (contextPath == null) {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (sra != null) {
                HttpServletRequest req = sra.getRequest();
                contextPath = req.getContextPath();
            } else {
                log.error("Не удалось получить contextPath из request при сохранении изображения");
                contextPath = "";
            }
        }
        return contextPath;
    }
}
