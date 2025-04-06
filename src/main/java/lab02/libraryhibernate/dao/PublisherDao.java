package lab02.libraryhibernate.dao;

import lab02.libraryhibernate.entities.Author;
import lab02.libraryhibernate.entities.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Publisher> getAllPublishers(){
        Session session = sessionFactory.openSession();
        return session.createQuery("from Publisher", Publisher.class).list();
    }

    public Publisher getPublisher(Long id){
        Session session = sessionFactory.openSession();
        Publisher publisher = session.createQuery("from Publisher where id = :id", Publisher.class).setParameter("id", id).getSingleResultOrNull();
        return publisher;
    }

    public void createPublisher(Publisher publisher){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(publisher);
        session.getTransaction().commit();
        session.close();
    }

    public Publisher updatePublisher(Publisher publisher){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(publisher);
            session.getTransaction().commit();
            return publisher;
        }finally {
            session.close();
        }
    }
}
