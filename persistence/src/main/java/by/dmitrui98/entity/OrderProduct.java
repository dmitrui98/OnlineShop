package by.dmitrui98.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "order_product")
@AssociationOverrides({
        @AssociationOverride(name = "id.order",
                joinColumns = @JoinColumn(name = "order_id")),
        @AssociationOverride(name = "id.product",
                joinColumns = @JoinColumn(name = "product_id")) })
@Getter
@Setter
@NoArgsConstructor
public class OrderProduct {

    @EmbeddedId
    private OrderProductPK id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

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
