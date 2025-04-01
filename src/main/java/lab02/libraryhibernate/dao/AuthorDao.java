package lab02.libraryhibernate.dao;

import jakarta.persistence.NoResultException;
import lab02.libraryhibernate.config.HibernateConfig;
import lab02.libraryhibernate.entities.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Author> getAuthors() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Author", Author.class).list();
    }

    public Author getAuthor(Long id) {
        Session session = sessionFactory.openSession();
            return session.createQuery("from Author where id = :id", Author.class)
                    .setParameter("id", id)
                    .getSingleResultOrNull();
    }

    public void createAuthor(Author author)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
    }
}
