import DAO.ClientCrudService;
import DAO.ClientDAO;
import DAO.PlanetCrudService;
import DAO.PlanetDAO;
import model.Planet;
import org.hibernate.SessionFactory;
import storage.HibernateUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
        ClientDAO clientDAO = new ClientCrudService(sessionFactory);

        long idNewClient = clientDAO.create("NewClient");
        clientDAO.getById(1L);
        clientDAO.listAll();
        clientDAO.setName(idNewClient, "NewNAmeClient");
        clientDAO.deleteById(idNewClient);
        clientDAO.listAll();

        PlanetDAO planetDAO = new PlanetCrudService(sessionFactory);
        Planet newPlanet = new Planet();
        newPlanet.setId("SUN");
        newPlanet.setId("STAR");
        planetDAO.getById("VEN");
        planetDAO.create(newPlanet);
        planetDAO.getAll();
        planetDAO.getById("SUN");
        planetDAO.updateById("SUN","newNAme");

    }


}
