package by.dmitrui98.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "order_product")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class OrderProduct {

    @EmbeddedId
    @EqualsAndHashCode.Exclude
    private OrderProductId id;

    @Column(name = "price", nullable = false)
    @EqualsAndHashCode.Exclude
    private double price;

    @Column(name = "quantity", nullable = false)
    @EqualsAndHashCode.Exclude
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;

    public OrderProduct(double price, int quantity, Product product, Order order) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }
}
