package by.dmitrui98.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "product_material")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProductMaterial {

    @EmbeddedId
    @EqualsAndHashCode.Exclude
    private ProductMaterialId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("materialId")
    private Material material;

    @Column(name = "percent_material")
    @EqualsAndHashCode.Exclude
    private double materialPercent;

    public ProductMaterial(Product product, Material material, double materialPercent) {
        this.product = product;
        this.material = material;
        this.materialPercent = materialPercent;
        this.id = new ProductMaterialId(product.getProductId(), material.getMaterialId());
    }
}
