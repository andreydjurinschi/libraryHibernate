package lab02.libraryhibernate.dao;

import lab02.libraryhibernate.entities.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDao {


    private SessionFactory sessionFactory;

    public List<Author> getAuthors() {
        try(Session session = sessionFactory.openSession()){
            String hql = "select a from Author a join fetch a.books";
            return session.createQuery(hql, Author.class).list();
        }
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

    public Author updateAuthor(Author author){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(author);
        session.getTransaction().commit();
        return author;
    }

    public void deleteAuthorById(Long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Author author = session.get(Author.class, id);
        if (author != null) {
            session.delete(author);
        }
        session.getTransaction().commit();
        session.close();
    }
}
