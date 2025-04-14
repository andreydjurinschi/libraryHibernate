package lab02.libraryhibernate.config;

import lab02.libraryhibernate.dao.AuthorDao;
import lab02.libraryhibernate.dao.BookDao;
import lab02.libraryhibernate.dao.CategoryDao;
import lab02.libraryhibernate.dao.PublisherDao;
import lab02.libraryhibernate.entities.Author;
import lab02.libraryhibernate.entities.Book;
import lab02.libraryhibernate.entities.Category;
import lab02.libraryhibernate.entities.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final SessionFactory sessionFactory;

    public DataLoader(BookDao bookDao, AuthorDao authorDao, PublisherDao publisherDao, CategoryDao categoryDao, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        BookDao bookDao = new BookDao(sessionFactory);
        if(bookDao.getAllBooks().isEmpty()){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Author author = new Author("Sergei Bobbin");
            Author author1 = new Author("Ilia Golovkin");
            session.save(author1);
            session.save(author);
            Category category = new Category("Science");
            Category category1 = new Category("History");
            session.save(category);
            session.save(category1);
            Publisher publisher = new Publisher("ACT");
            Publisher publisher1 = new Publisher("Librarius");
            session.save(publisher);
            session.save(publisher1);
            List<Category> categories = Arrays.asList(category, category1);
            Book book = new Book("How to play minecraft", author, publisher, categories);
            Book book1 = new Book("How to date with a girl", author1, publisher1, categories);
            session.save(book);
            session.save(book1);
            session.getTransaction().commit();
            session.close();
        }
    }
}
