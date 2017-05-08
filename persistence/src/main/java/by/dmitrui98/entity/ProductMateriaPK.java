package by.dmitrui98.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Администратор on 03.05.2017.
 */
@Embeddable
public class ProductMateriaPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Materia materia;

    public ProductMateriaPK() {
    }

    public ProductMateriaPK(Product product, Materia materia) {
        this.product = product;
        this.materia = materia;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
