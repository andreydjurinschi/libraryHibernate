package lab02.libraryhibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import lab02.libraryhibernate.entities.Book;
import java.util.List;

@Repository
public class BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Book", Book.class).list();
    }

    public Book getBookById(Long id){
        Session session = sessionFactory.openSession();
        return session.createQuery("from Book where id = :id", Book.class).setParameter("id", id).getSingleResultOrNull();
    }

    public void createBook(Book book){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

}
