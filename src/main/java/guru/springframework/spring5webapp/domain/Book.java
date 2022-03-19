package guru.springframework.spring5webapp.domain;

import lombok.*;
import org.hibernate.annotations.Generated;

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
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;
    private String isbn;

    @ToString.Exclude
    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

}
