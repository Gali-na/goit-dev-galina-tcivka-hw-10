package storage;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
     public static void  initDb(String connectionUrl) {
          Flyway flywayProject = Flyway.configure().dataSource(connectionUrl,null,null).load();
          flywayProject.migrate();
     }


}
