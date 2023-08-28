package storage;
import model.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static storage.Prefs.DB_JDBS_CONECTION_URL;

public class HibernateUtil {

    private static final HibernateUtil INSTANCE;
    @Getter
    private  SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }


    private HibernateUtil() {

        DatabaseInitService.initDb(DB_JDBS_CONECTION_URL);
        sessionFactory = new Configuration()
                   // .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                    .buildSessionFactory();

    }

    public static HibernateUtil getInstance() {

        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}
