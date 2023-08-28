package DAO;

import exceptions.ArgumentException;
import model.Client;
import model.Planet;
import model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TicketCrudService implements TicketDAO {
    private final SessionFactory sessionFactory;
    private ClientDAO clientDAO;

    public TicketCrudService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.clientDAO = new ClientCrudService(sessionFactory);

    }

    private void clientVerification(Client client) throws ArgumentException {
        if (client == null) {
            throw new ArgumentException("Argument cannot be null");
        }
        if (client.getName() == null) {
            throw new ArgumentException("Clime's name  cannot be null");
        }

    }

    private void idVerification(long id) throws ArgumentException {
        if (id <= 0l) {
            throw new ArgumentException("Argument cannot be null");
        }


    }

    private void planetVerification(Planet planet) throws ArgumentException {
        if (planet == null) {
            throw new ArgumentException("Argument cannot be null");
        }
    }


    private void checkClientPresencesInBatabase(Client client, Session session) throws ArgumentException {
        clientVerification(client);
        if (client.getId() != 0) {
            Client clientFromBD = session.get(Client.class, client.getId());
            if (clientFromBD == null) {
                throw new ArgumentException("Client with this id is not exist");
            }
        }
        if (client.getId() == 0) {
            session.persist(client);
        }
    }

    private void checkPresencePlanetINDatabase(Planet planet, Session session) throws ArgumentException {
        planetVerification(planet);

        Planet planetFromBD = session.get(Planet.class, planet.getId());
        if (planetFromBD == null) {
            throw new ArgumentException("Planet with this id is not exist");
        }
        if (!planetFromBD.getName().equals(planet.getName())) {
            throw new ArgumentException("The transmitted planet name does not match the name of the planet in the database");
        }

    }

    @Override
    public long create(Client client, Planet toPlanet, Planet fromPlanet) throws Exception {
        long id = 0L;
        Ticket ticket = new Ticket();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            checkClientPresencesInBatabase(client, session);
            checkPresencePlanetINDatabase(toPlanet, session);
            checkPresencePlanetINDatabase(fromPlanet, session);
            ticket.setFromPlanet(fromPlanet);
            ticket.setToPlanet(toPlanet);
            ticket.setClient(client);
            session.persist(ticket);
            id = ticket.getId();
            transaction.commit();
        }
        return id;
    }

    @Override
    public Ticket getById(long id) throws Exception {
        idVerification(id);
        Ticket ticketFromBD;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            ticketFromBD = session.get(Ticket.class, id);
            if (ticketFromBD == null) {
                throw new ArgumentException("Ticket with thid id id not exist");
            }
            transaction.commit();
        }
        return ticketFromBD;
    }

    @Override
    public void deleteById(long id) throws Exception {
        idVerification(id);

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticketFromBD = session.get(Ticket.class, id);
            if (ticketFromBD == null) {
                throw new ArgumentException("Ticket with this id id not exist");
            }
            session.remove(ticketFromBD);
            transaction.commit();
        }
    }


    @Override
    public List<Ticket> listAll() {
        List<Ticket> listAllTicket = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            listAllTicket = session.createQuery("from Ticket", Ticket.class).getResultList();
            transaction.commit();
        }
        return listAllTicket;
    }

    @Override
    public void updateByInformClient(long ticketId, Client client) throws Exception {
        idVerification(ticketId);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticketFromBD = session.get(Ticket.class, ticketId);
            if (ticketFromBD == null) {
                throw new ArgumentException("Ticket with this id id not exist");
            }
            checkClientPresencesInBatabase(client, session);
            ticketFromBD.setClient(client);
            session.merge(ticketFromBD);
            transaction.commit();
        }

    }

}