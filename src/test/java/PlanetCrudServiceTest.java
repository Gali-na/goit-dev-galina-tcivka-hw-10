import DAO.PlanetCrudService;
import DAO.PlanetDAO;
import exceptions.ArgumentException;
import model.Planet;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCrudServiceTest {
    private SessionFactory sessionFactory = HibernateUtilTest.getInstance().getSessionFactory();
    private PlanetDAO planetDAO;

    @BeforeEach
    private void planetDAO() {

        planetDAO = new PlanetCrudService(sessionFactory);
    }

    @Test
    void create_PlanetNull_ThrowException() {
        assertThrows(ArgumentException.class, () -> planetDAO.create(null));
    }

    @Test
    void create_PlanetValid_PositiveResult() throws ArgumentException {
        Planet planet = new Planet();
        planet.setId("NNNN");
        planet.setName("NewPlanN");
        List<Planet> planetsBeforeCreating = planetDAO.getAll();
        planetDAO.create(planet);
        List<Planet> planetsAfterCreating = planetDAO.getAll();
        assertEquals(planetsBeforeCreating.size() + 1, planetsAfterCreating.size());
    }


    @Test
    void getAll() throws ArgumentException {
        Planet planet = new Planet();
        planet.setId("ALLN");
        planet.setName("NewPlanNN");
        List<Planet> planetsBeforeCreating = planetDAO.getAll();
        planetDAO.create(planet);
        List<Planet> planetsAfterCreating = planetDAO.getAll();
        assertEquals(planetsBeforeCreating.size() + 1, planetsAfterCreating.size());

    }


    @Test
    void getById_SetNullArgument_ThrowException() {
        assertThrows(ArgumentException.class, () -> planetDAO.getById(null));
    }

    @Test
    void getById_SetIdNotValid_ThrowException() {
        assertThrows(ArgumentException.class, () -> planetDAO.getById("jjj"));
    }

    @Test
    void getById_SetIdValid_GetPlanet() throws ArgumentException {
        List<Planet> planets = planetDAO.getAll();
        assertNotNull(planetDAO.getById(planets.get(1).getId()));
    }


    @Test
    void deleteById_SetNullArgument_ThrowException() {
        assertThrows(ArgumentException.class, () -> planetDAO.deleteById(null));
    }

    @Test
    void deleteById_SetIdNotValid_ThrowException() {
        assertThrows(ArgumentException.class, () -> planetDAO.deleteById("jjj"));
    }


    @Test
    void deleteById_IdExisitInBd_PositiveResult() throws ArgumentException {
        Planet planet = new Planet();
        planet.setId("DELN");
        planet.setName("DeleteN");
        planetDAO.create(planet);
        int countPlanetsBeforDelet = planetDAO.getAll().size();
        planetDAO.deleteById("DELN");
        int countAfterBeforDelet =planetDAO.getAll().size();
        assertEquals(countPlanetsBeforDelet, countAfterBeforDelet + 1);
    }

    @Test
    void updateById_IdExisitInBd_PositiveResult() throws ArgumentException {
        Planet planet = new Planet();
        planet.setId("NEW2N");
        planet.setName("NEWname1N");
        planetDAO.create(planet);
        planetDAO.updateById("NEW2N","NEWname2N");
        assertEquals(planetDAO.getById("NEW2N").getName(),"NEWname2N");
    }
    @Test
    void updateById_SetNullArgument_ThrowException() {
        assertThrows(ArgumentException.class, () -> planetDAO.getById(null));
    }
    @Test
    void updateById_SetIdNotValid_ThrowException() {
        assertThrows(ArgumentException.class, () -> planetDAO.deleteById("jjj"));
    }
}