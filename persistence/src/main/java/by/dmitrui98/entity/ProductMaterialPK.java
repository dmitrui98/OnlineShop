package by.dmitrui98.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Администратор on 03.05.2017.
 */
@Embeddable
public class ProductMaterialPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id")
    private Material material;

    public ProductMaterialPK() {
    }

    public ProductMaterialPK(Product product, Material material) {
        this.product = product;
        this.material = material;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
