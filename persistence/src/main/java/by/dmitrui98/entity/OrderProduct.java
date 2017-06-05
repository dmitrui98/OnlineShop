package by.dmitrui98.entity;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "order_product")
@AssociationOverrides({
        @AssociationOverride(name = "id.order",
                joinColumns = @JoinColumn(name = "order_id")),
        @AssociationOverride(name = "id.product",
                joinColumns = @JoinColumn(name = "product_id")) })
public class OrderProduct {

    @EmbeddedId
    private OrderProductPK id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(OrderProductPK id, double price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderProductPK getId() {
        return id;
    }

    public void setId(OrderProductPK id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Transient
    public Order getOrder() {
        return getId().getOrder();
    }

    @Transient
    public Product getProduct() {
        return getId().getProduct();
    }

    public void setOrder(Order order) {
        getId().setOrder(order);
    }

    public void setProduct(Product product) {
        getId().setProduct(product);
    }
}
