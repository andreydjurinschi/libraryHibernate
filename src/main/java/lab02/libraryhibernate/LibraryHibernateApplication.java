package lab02.libraryhibernate;

import lab02.libraryhibernate.config.HibernateConfig;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryHibernateApplication.class, args);
    }

}
