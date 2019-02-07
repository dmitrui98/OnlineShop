package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.MaterialDao;
import by.dmitrui98.entity.Material;
import by.dmitrui98.entity.ProductMaterial;
import by.dmitrui98.service.dao.ImageService;
import by.dmitrui98.service.dao.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialDao materialDao;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Material> getAll() {
        List<Material> materials = materialDao.findAll();
        Collections.sort(materials);
        return materials;
    }

    @Override
    public Material getById(Long id) {
        return materialDao.getById(id);
    }

    @Override
    public void save(Material material) {
        material.setName(material.getName().toLowerCase());
        materialDao.addOrUpdate(material);
    }

    @Override
    public boolean remove(Long id) {
        Material material = materialDao.getById(id);
        return (material.getProductMaterials().size() == 0) && (materialDao.delete(id));
    }

    @Override
    public void removeCascade(Long id) {
        Material material = materialDao.getById(id);
        for (ProductMaterial productMaterial : material.getProductMaterials()) {
            imageService.removeImage(productMaterial.getProduct());
        }
        materialDao.delete(id);
    }

    @Override
    public Material getByName(String name) {
        return materialDao.getByName(name);
    }
}
