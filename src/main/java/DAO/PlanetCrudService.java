package DAO;

import exceptions.ArgumentException;
import model.Planet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlanetCrudService implements PlanetDAO {

    private final SessionFactory sessionFactory;

    public PlanetCrudService(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public String create(Planet planet) throws ArgumentException {
        String id;
        if(planet==null){
            throw new ArgumentException();
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            id = planet.getId();
            transaction.commit();
        }

        return id;
    }

    @Override
    public List<Planet> getAll() {
        List<Planet> listAllPlanet = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            listAllPlanet = session.createQuery("from Planet", Planet.class).getResultList();
        }
        return listAllPlanet;
    }

    @Override
    public Planet getById(String id) throws ArgumentException {
        validateId(id);
        Planet planet;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            planet = session.get(Planet.class, id);
            transaction.commit();
        }
        if (planet == null) {
            throw new ArgumentException();
        }
        return planet;
    }

    @Override
    public void deleteById(String id) throws ArgumentException {
        validateId(id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.remove(planet);
            } else {
                throw new ArgumentException("The specified id does not exist in the database");
            }
            transaction.commit();
        }

    }

    @Override
    public void updateById(String id, String name) throws ArgumentException {
        validateId(id);
        validateName(name);

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                planet.setName(name);
            } else {
                throw new ArgumentException("The specified id does not exist in the database");
            }
            transaction.commit();
        }
    }


    private void validateName(String name) throws ArgumentException {
        if (name == null) {
            throw new ArgumentException("Argument cannot be null");
        }
        if (name.length() > 200 || name.length() < 2) {
            throw new ArgumentException("The name is incorrect, the number of characters in" +
                    " the name must not be less than 2 or more than 1000");
        }
    }

    private void validateId(String id) throws ArgumentException {
        if (id == null) {
            throw new ArgumentException("Argument cannot be null");
        }
        if (id.length() > 50 || id.length() < 2) {
            throw new ArgumentException("The id is incorrect, the number of characters in" +
                    " the id must not be less than 2 or more than 50");
        }

        Pattern pattern = Pattern.compile("^[A-Z0-9]+");
        Matcher matcher = pattern.matcher(id);

        if (!matcher.matches()) {
            throw new ArgumentException("planet identifier is row that is composed exclusively " +
                    "of Latin letters " +
                    "at the upper register and numbers. For example, MARS, VEN");
        }

    }
}
