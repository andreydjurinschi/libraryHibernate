package lab02.libraryhibernate.config;


import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.hibernate.cfg.Configuration;


@org.springframework.context.annotation.Configuration
public class HibernateConfig {
@Bean
    public SessionFactory createSessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
}
