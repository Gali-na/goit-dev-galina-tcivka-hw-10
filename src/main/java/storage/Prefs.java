package storage;

import java.util.ResourceBundle;

public class Prefs {
    private static ResourceBundle getResourceBundle() {

        return ResourceBundle.getBundle("hibernate");
    }

    public static final String DB_JDBS_CONECTION_URL = getResourceBundle().getString("hibernate.connection.url");

}
