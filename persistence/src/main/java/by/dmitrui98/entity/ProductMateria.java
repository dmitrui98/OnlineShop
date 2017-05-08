package by.dmitrui98.entity;

import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.*;

/**
 * Created by Администратор on 03.05.2017.
 */
@Entity
@Table(name = "product_materia")
@AssociationOverrides({
        @AssociationOverride(name = "id.product",
                joinColumns = @JoinColumn(name = "product_id")),
        @AssociationOverride(name = "id.materia",
                joinColumns = @JoinColumn(name = "materia_id")) })
public class ProductMateria {

    @EmbeddedId
    private ProductMateriaPK id = new ProductMateriaPK();

    @Column(name = "persant_materia", columnDefinition = "DOUBLE(4,2) DEFAULT 0.00")
    private double persantMateria;

    public ProductMateria() {
    }

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

    @Transient
    public Product getProduct() {
        return getId().getProduct();
    }

    @Transient
    public Materia getMateria() {
        return getId().getMateria();
    }

    public void setProduct(Product product) {
        getId().setProduct(product);
    }

    public void setMateria(Materia materia) {
        getId().setMateria(materia);
    }
}
