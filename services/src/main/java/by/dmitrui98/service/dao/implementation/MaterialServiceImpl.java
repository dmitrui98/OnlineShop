package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.MaterialDao;
import by.dmitrui98.entity.Material;
import by.dmitrui98.service.dao.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialDao materialDao;

    @Override
    public List<Material> getAll() {
        return materialDao.findAll();
    }

    @Override
    public Material getById(Integer id) {
        return materialDao.getById(id);
    }

    @Override
    public void save(Material material) {
        if (material.getMaterialId() == 0) {
            material.setCreatedAt(new Date());
            material.setUpdatedAt(new Date());
        } else
            material.setUpdatedAt(new Date());
        materialDao.addOrUpdate(material);
    }

    @Override
    public void remove(Integer id) {
        Material material = materialDao.getById(id);

        if (material.getProductMaterials().size() == 0)
            materialDao.delete(id);
    }

    @Override
    public void removeCascade(Integer id) {
        materialDao.delete(id);
    }
}
