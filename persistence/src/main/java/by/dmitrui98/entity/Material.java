package by.dmitrui98.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "material")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Material implements Comparable<Material> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    @EqualsAndHashCode.Include
    private Long materialId;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "material", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<ProductMaterial> productMaterials = new ArrayList<>();

    public Material(String name, Admin admin) {
        this.name = name;
        this.admin = admin;
    }

    public void removeAllProductCascade(Session session) {
        for (Iterator<ProductMaterial> it = productMaterials.iterator(); it.hasNext(); ) {
            ProductMaterial pm = it.next();
            it.remove();
            pm.getProduct().getProductMaterials().remove(pm);
            Product product = pm.getProduct();
            product.removeAllMaterial();
            product.getCategory().getProducts().remove(product);
            session.delete(product);
            pm.setProduct(null);
            pm.setMaterial(null);
        }
    }

    @Override
    public int compareTo(Material o) {
        return getName().compareTo(o.getName());
    }
}
