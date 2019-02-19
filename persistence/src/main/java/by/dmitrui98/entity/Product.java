package by.dmitrui98.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @EqualsAndHashCode.Include
    private Long productId;

    @Column(name = "count_products")
    private int countProducts;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ProductMaterial> productMaterials = new ArrayList<>();

    public void addMaterial(Material material, double materialPercent) {
        ProductMaterial productMaterial = new ProductMaterial(this, material, materialPercent);
        productMaterials.add(productMaterial);
        material.getProductMaterials().add(productMaterial);
    }

    public void removeMaterial(Material material) {
        for (Iterator<ProductMaterial> it = productMaterials.iterator(); it.hasNext(); ) {
            ProductMaterial pm = it.next();
            if (pm.getProduct().equals(this) && pm.getMaterial().equals(material)) {
                it.remove();
                pm.getMaterial().getProductMaterials().remove(pm);
                pm.setProduct(null);
                pm.setMaterial(null);
            }
        }
    }

    public void removeAllMaterial() {
        for (Iterator<ProductMaterial> it = productMaterials.iterator(); it.hasNext(); ) {
            ProductMaterial pm = it.next();
            it.remove();
            pm.getMaterial().getProductMaterials().remove(pm);
            pm.setProduct(null);
            pm.setMaterial(null);
        }
    }

    public Product(String name, double price, String description, Image image, Category category, Admin admin) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category = category;
        this.admin = admin;
    }
}
