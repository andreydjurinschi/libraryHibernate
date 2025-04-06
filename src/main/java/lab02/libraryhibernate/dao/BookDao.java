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
            String hql = "select b from Book b left join fetch b.categories";
            List<Book> books = session.createQuery(hql, Book.class).getResultList();
            return books;
        }
    }

    public Book getBookById(Long id){
        try(Session session = sessionFactory.openSession()) {
            String hql = "select b from Book b left join fetch b.categories where b.id = :id";
            return session.createQuery(hql, Book.class).setParameter("id", id).getSingleResultOrNull();
        }

    }

    public void createBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

}
