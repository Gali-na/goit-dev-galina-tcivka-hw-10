import lombok.Getter;
import model.Client;
import model.Planet;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilTest {

    private static final HibernateUtilTest INSTANCE;
    @Getter
    private SessionFactory sessionFactory;
    private Flyway flywayProject;

    static {
        INSTANCE = new HibernateUtilTest();
    }


    private HibernateUtilTest() {
        flywayProject = Flyway.configure().dataSource("jdbc:h2:./db_spase_travel3_test", null, null).load();
        flywayProject.migrate();
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();

    }

    public static HibernateUtilTest getInstance() {

        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}
