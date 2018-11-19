package by.dmitrui98.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;

    @Transient
    private int countProductsInPottle;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(columnDefinition = "TEXT(500)", name = "description")
    private String description;

    @Column(name = "created_at", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "id.product", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id.product", cascade = CascadeType.ALL)
    private Set<ProductMaterial> productMaterials = new HashSet<>();

    public Product() {
    }

    public Product(int countPottleProducts, String name, double price, String description, Date createdAt, Date updatedAt, Image image, Category category, Admin admin, Set<OrderProduct> orderProducts, Set<ProductMaterial> productMaterials) {
        this.countProductsInPottle = countPottleProducts;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.image = image;
        this.category = category;
        this.admin = admin;
        this.orderProducts = orderProducts;
        this.productMaterials = productMaterials;
    }

    public Product(String name, double price, String description, Date createdAt, Date updatedAt, Image image, Category category, Admin admin) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.image = image;
        this.category = category;
        this.admin = admin;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getCountProductsInPottle() {
        return countProductsInPottle;
    }

    public void setCountProductsInPottle(int countProductsInPottle) {
        this.countProductsInPottle = countProductsInPottle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Set<ProductMaterial> getProductMaterials() {
        return productMaterials;
    }

    public void setProductMaterials(Set<ProductMaterial> productMaterials) {
        this.productMaterials = productMaterials;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Product))
            return false;

        Product product = (Product) obj;
        return product.getProductId() == getProductId();
    }
}
