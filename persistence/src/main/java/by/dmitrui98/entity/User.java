package by.dmitrui98.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Transient
    private String confirmPassword;

    @Column(name = "login", nullable = false, length = 100, unique = true)
    private String login;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "fname", length = 50)
    private String fname;

    @Column(name = "lname", length = 50)
    private String lname;

    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "discont")
    private double discont;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public User(String login, String email, String password, String fname) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.fname = fname;
    }
}
