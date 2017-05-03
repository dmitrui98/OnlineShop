package by.dmitrui98.entity;

import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Администратор on 03.05.2017.
 */
@Entity
@Table(name = "product_materia")
public class ProductMateria {

    @EmbeddedId
    private ProductMateriaPK id;

    @Column(name = "persant_materia", columnDefinition = "DOUBLE(4,2) DEFAULT 0.00")
    private double persantMateria;

    public ProductMateria(ProductMateriaPK id, double persantMateria) {
        this.id = id;
        this.persantMateria = persantMateria;
    }

    public ProductMateriaPK getId() {
        return id;
    }

    public void setId(ProductMateriaPK id) {
        this.id = id;
    }

    public double getPersantMateria() {
        return persantMateria;
    }

    public void setPersantMateria(double persantMateria) {
        this.persantMateria = persantMateria;
    }
}
