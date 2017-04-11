package by.dmitrui98.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Администратор on 09.04.2017.
 */
@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private int materiaId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_materia",
        joinColumns = @JoinColumn(name = "materia_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSet;

    @Column(name = "name")
    private String name;

    public Materia() {
    }

    public Materia(String name) {
        this.name = name;
    }

    public Materia(Set<Product> productSet, String name) {
        this.productSet = productSet;
        this.name = name;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
