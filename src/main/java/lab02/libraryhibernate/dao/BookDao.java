package lab02.libraryhibernate.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import lab02.libraryhibernate.entities.Book;
import java.util.List;

@Repository
public class BookDao {


    private final SessionFactory sessionFactory;

    public BookDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> getAllBooks() {
        try(Session session = sessionFactory.openSession()) {
            String query = "select b from Book b left join fetch b.categories";
            List<Book> books = session.createQuery(query, Book.class).getResultList();
            return books;
        }
    }

    public Book getBookById(Long id){
        try(Session session = sessionFactory.openSession()) {
            String query = "select b from Book b left join fetch b.categories where b.id = :id";
            return session.createQuery(query, Book.class).setParameter("id", id).getSingleResultOrNull();
        }
    }

    public Book save(Book book) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(book);
            session.getTransaction().commit();
            return book;
        }
    }

    private boolean existsBookById(Long id){
        try(Session session = sessionFactory.getCurrentSession()) {
            String query = "select b from Book b where b.id = :id";
            Book book = session.createQuery(query, Book.class).setParameter("id", id).getSingleResult();
            if(book != null){
                return true;
            }
            return false;
        }
    }

}
