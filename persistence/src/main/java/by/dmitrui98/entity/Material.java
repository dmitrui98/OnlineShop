package by.dmitrui98.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int materialId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "created_at", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private Admin admin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.material", cascade=CascadeType.ALL)
    private Set<ProductMaterial> productMaterials = new HashSet<>();

    public Material() {
    }

    public Material(String name, Date createdAt, Date updatedAt, Admin admin, Set<ProductMaterial> productMaterials) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.admin = admin;
        this.productMaterials = productMaterials;
    }

    public Material(String name, Admin admin, Date createdAt, Date updatedAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.admin = admin;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Set<ProductMaterial> getProductMaterials() {
        return productMaterials;
    }

    public void setProductMaterials(Set<ProductMaterial> productMaterials) {
        this.productMaterials = productMaterials;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        Material material = (Material) obj;

        return material.getMaterialId() == this.getMaterialId();
    }
}
