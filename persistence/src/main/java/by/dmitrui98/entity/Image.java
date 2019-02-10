package by.dmitrui98.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long imageId;

    @Column(name = "image_directory", nullable = false)
    private String imageDirectory;

    public Image(String imageDirectory) {
        this.imageDirectory = imageDirectory;
    }
}
