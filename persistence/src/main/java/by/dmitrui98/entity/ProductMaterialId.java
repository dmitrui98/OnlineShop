package by.dmitrui98.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaterialId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "material_id")
    private Long materialId;

}
