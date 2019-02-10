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
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category implements Comparable<Category> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Category(String name, Admin admin) {
        this.name = name;
        this.admin = admin;
    }

    @Override
    public int compareTo(Category o) {
        return getName().compareTo(o.getName());
    }
}
