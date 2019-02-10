package by.dmitrui98.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_")
@Getter
@Setter
@NoArgsConstructor
public class Order {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "order_id")
   private long orderId;

   @Column(name = "amount", nullable = false)
   private double amount; // сумма заказа

   @Column(name = "created_at", nullable = false)
   @CreationTimestamp
   private LocalDateTime createdAt;

   @Column(name = "updated_at", nullable = false)
   @UpdateTimestamp
   private LocalDateTime updatedAt;

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "address_id")
   private Address address;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "order_product",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id.order", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new HashSet<>();
}
