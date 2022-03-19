package guru.springframework.spring5webapp.bootstrapp;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Plamen Uzunov
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("Ciela");
        publisher.setAddress("ul. G.S. Rakovski 41");
        publisher.setCity("Sofia");
        publisher.setState("Sofia");
        publisher.setZip("1000");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        saveAuthorAndBook(eric, ddd, publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Developemnt without EJB", "23453452342");
        saveAuthorAndBook(rod, noEJB, publisher);

        System.out.println("Started in Bootstrap!");
        System.out.println("Number of books: "+ bookRepository.count());
        System.out.println("Number of publishers: "+ publisherRepository.count());
        System.out.println("Publisher Number of books: "+ publisher.getBooks().size());
    }

    private void saveAuthorAndBook(Author author, Book book, Publisher publisher) {
        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(author);
        bookRepository.save(book);
        publisherRepository.save(publisher);
    }
}
