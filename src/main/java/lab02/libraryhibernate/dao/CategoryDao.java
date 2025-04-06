package lab02.libraryhibernate.dao;

import lab02.libraryhibernate.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Category> getAllCategories() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Category", Category.class).list();
    }

    public Category getCategory(Long id) {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Category where id = :id", Category.class).setParameter("id", id).getSingleResultOrNull();
    }

    public void createCategory(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();
    }
}
