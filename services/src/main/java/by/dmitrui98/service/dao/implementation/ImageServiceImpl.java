package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.ImageDao;
import by.dmitrui98.entity.Image;
import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.ImageService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 09.05.2017.
 */
@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger logger = Logger.getLogger(ImageServiceImpl.class);

    private static final String dir = "/server/file_storage/images/";
    private static final String defaultImageName = "default.jpg";
    private static final String serverDir = "/images/";


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


                image = imageDao.save(serverDir + imageName);

            } catch (IOException ex) {
                logger.error("IOException in writing image. Image folder: " + folder.getAbsolutePath() +
                        "; image name" + imageName, ex);
            } finally {
                try {
                    if (stream != null)
                        stream.close();
                } catch (IOException e) {
                    logger.error("IOException in closing BufferedOutputStream", e);
                }
            }
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
                logger.error("Image with name " + imageName + " not found");
                stream =
                        new FileInputStream(new File(folder, defaultImageName));
            }

            byte[] bytes = IOUtils.toByteArray(stream);

            return bytes;
        } catch (IOException ex) {
            logger.error("IOException in reading image. Image folder: " + folder.getAbsolutePath() +
                    "; image name" + imageName, ex);
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                logger.error("IOException in closing FileInputStream", e);
            }
        }
        return null;
    }

    @Override
    public Image getDefaultImage() {
        // TODO переместить изображение default.jpg из classpath, если оно не сущетсвует
        return new Image(serverDir + defaultImageName);
    }

    private File createFolder(String directory) {
        File folder = new File(directory);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    @Override
    public boolean remove(String serverDirectory) {
        String imageName = serverDirectory.substring(serverDir.length());
        File folder = createFolder(dir);
        File image = new File(folder, imageName);

        if (!imageName.equals(defaultImageName))
            if (image.delete())
                return true;
        return false;
    }

    @Override
    public boolean removeImage(Product product) {
        String imageDir = product.getImage().getImageDirectory();
        return remove(imageDir);
    }
}
