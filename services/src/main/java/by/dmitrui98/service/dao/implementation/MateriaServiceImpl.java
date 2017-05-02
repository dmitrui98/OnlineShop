package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.MateriaDao;
import by.dmitrui98.entity.Materia;
import by.dmitrui98.service.dao.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 29.04.2017.
 */
@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    MateriaDao materiaDao;

    @Override
    public List<Materia> getAll() {
        return materiaDao.findAll();
    }

    @Override
    public Materia getById(Integer id) {
        return materiaDao.getById(id);
    }

    @Override
    public void save(Materia materia) {
        if (materia.getMateriaId() == 0) {
            materia.setCreatedAt(new Date());
            materia.setUpdatedAt(new Date());
        } else
            materia.setUpdatedAt(new Date());
        materiaDao.addOrUpdate(materia);
    }

    @Override
    public void remove(Integer id) {
        materiaDao.delete(id);
    }
}
