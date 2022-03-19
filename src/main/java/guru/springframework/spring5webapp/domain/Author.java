package guru.springframework.spring5webapp.domain;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Plamen Uzunov
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString()
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String firstName;
    private String lastName;

    @ToString.Exclude
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

}
