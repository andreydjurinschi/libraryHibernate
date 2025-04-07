package lab02.libraryhibernate.dao;

import lab02.libraryhibernate.entities.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDao {


    private final SessionFactory sessionFactory;

    public AuthorDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Author> getAuthors() {
        try(Session session = sessionFactory.openSession()){
            String query = "select a from Author a join fetch a.books";
            return session.createQuery(query, Author.class).list();
        }
    }

    public Author getAuthor(Long id) {
        try(Session session = sessionFactory.openSession()){
            String query = "select a from Author a join fetch a.books where a.id = :id";
            return session.createQuery(query, Author.class).setParameter("id", id).uniqueResult();
        }
    }

    public void createAuthor(Author author)
    {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(author);
            session.getTransaction().commit();
        }
    }

    public Author updateAuthor(Author author){

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(author);
            session.getTransaction().commit();
            return author;
        }
    }

    public void deleteAuthorById(Long id){
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Author author = session.get(Author.class, id);
            session.remove(author);
            session.getTransaction().commit();
        }
    }
}
