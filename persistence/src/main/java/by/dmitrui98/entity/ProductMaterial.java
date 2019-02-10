package by.dmitrui98.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "product_material")
@AssociationOverrides({
        @AssociationOverride(name = "id.product",
                joinColumns = @JoinColumn(name = "product_id")),
        @AssociationOverride(name = "id.material",
                joinColumns = @JoinColumn(name = "material_id")) })
@Getter
@Setter
@NoArgsConstructor
public class ProductMaterial {

    @EmbeddedId
    private ProductMaterialPK id = new ProductMaterialPK();

    @Column(name = "percent_material", columnDefinition = "DOUBLE(6,2) DEFAULT 0.00")
    private double percentMaterial;

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
