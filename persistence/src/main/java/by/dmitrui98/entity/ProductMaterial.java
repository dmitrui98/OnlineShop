package by.dmitrui98.entity;

import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.*;

/**
 * Created by Администратор on 03.05.2017.
 */
@Entity
@Table(name = "product_material")
@AssociationOverrides({
        @AssociationOverride(name = "id.product",
                joinColumns = @JoinColumn(name = "product_id")),
        @AssociationOverride(name = "id.material",
                joinColumns = @JoinColumn(name = "material_id")) })
public class ProductMaterial {

    @EmbeddedId
    private ProductMaterialPK id = new ProductMaterialPK();

    @Column(name = "persent_material", columnDefinition = "DOUBLE(4,2) DEFAULT 0.00")
    private double persentMaterial;

    public ProductMaterial() {
    }

    public ProductMaterial(ProductMaterialPK id, double persentMaterial) {
        this.id = id;
        this.persentMaterial = persentMaterial;
    }

    public ProductMaterialPK getId() {
        return id;
    }

    public void setId(ProductMaterialPK id) {
        this.id = id;
    }

    public double getPersentMaterial() {
        return persentMaterial;
    }

    public void setPersentMaterial(double persentMaterial) {
        this.persentMaterial = persentMaterial;
    }

    @Transient
    public Product getProduct() {
        return getId().getProduct();
    }

    @Transient
    public Material getMaterial() {
        return getId().getMaterial();
    }

    public void setProduct(Product product) {
        getId().setProduct(product);
    }

    public void setMaterial(Material material) {
        getId().setMaterial(material);
    }
}
