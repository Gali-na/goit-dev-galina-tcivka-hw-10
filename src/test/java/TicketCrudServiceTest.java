import DAO.ClientCrudService;
import DAO.ClientDAO;
import DAO.TicketDAO;
import DAO.TicketCrudService;
import exceptions.ArgumentException;
import model.Client;
import model.Planet;
import model.Ticket;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TicketCrudServiceTest {

    private SessionFactory sessionFactory = HibernateUtilTest.getInstance().getSessionFactory();
    private TicketDAO daoTicket;

    @BeforeEach
    private void planetDAO() {

        daoTicket = new TicketCrudService(sessionFactory);
    }

    @Test
    void create_SetClientNull_ThrowException() throws ArgumentException {
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");

        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");

        assertThrows(ArgumentException.class, () -> daoTicket.create(null, planetTo, planetFrom));
    }

    @Test
    void create_SetClientNameNull_ThrowException() throws ArgumentException {
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");

        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");

        assertThrows(ArgumentException.class, () -> daoTicket.create(new Client(), planetTo, planetFrom));
    }

    @Test
    void create_SetFromPlanetNull_ThrowException() throws ArgumentException {
        Planet planetFrom = null;
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Client clientTicket = new Client();
        clientTicket.setName("John");
        assertThrows(ArgumentException.class, () -> daoTicket.create(clientTicket, planetTo, planetFrom));
    }

    @Test
    void create_SetToPlanetNull_ThrowException() throws ArgumentException {
        Planet planetTo = null;
        Planet planetFrom = new Planet();
        planetFrom.setId("MAR");
        planetFrom.setName("Mars");
        Client clientTicket = new Client();
        clientTicket.setName("John");
        assertThrows(ArgumentException.class, () -> daoTicket.create(clientTicket, planetTo, planetFrom));
    }

    @Test
    void create_SetClientNotExistInDBSetValidPlanetToPlanetFrom_ThrowException() throws ArgumentException {
        ClientDAO clientDAO = new ClientCrudService(sessionFactory);
        List<Client> initialClients = clientDAO.listAll();
        int initialNumberClients = initialClients.size();
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");
        Client clientTicket = new Client();
        clientTicket.setId(initialNumberClients + 10);
        clientTicket.setName("John");
        assertThrows(ArgumentException.class, () -> daoTicket.create(clientTicket, planetTo, planetFrom));
    }

    @Test
    void create_SetClientIdExistInDBSetValidPlanetToPlanetFrom_PositiveResult() throws Exception {
        ClientDAO clientDAO = new ClientCrudService(sessionFactory);
        List<Client> initialClients = clientDAO.listAll();
        int initialNumberClients = initialClients.size();
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");
        Client clientTicket = new Client();
        clientTicket.setId(initialClients.get(initialNumberClients - 1).getId());
        clientTicket.setName(initialClients.get(initialNumberClients - 1).getName());
        assertNotEquals(0L, daoTicket.create(clientTicket, planetTo, planetFrom));
    }


    @Test
    void create_SetClientIdZeroValidPlanetToPlanetFrom_CreateNewClient() throws Exception {
        ClientDAO clientDAO = new ClientCrudService(sessionFactory);
        List<Client> clientsBeforCreateNew = clientDAO.listAll();
        int countClientsBeforeCreateNew = clientsBeforCreateNew.size();

        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");
        Client clientTicket = new Client();
        clientTicket.setId(0);
        clientTicket.setName("Jana");
        daoTicket.create(clientTicket, planetTo, planetFrom);
        List<Client> clientsAfterCreateNew = clientDAO.listAll();
        int countClientsAfterCreateNew = clientsAfterCreateNew.size();
        assertEquals(countClientsBeforeCreateNew + 1, countClientsAfterCreateNew);
    }


    @Test
    void create_SetToPlanetIdNotExistInBD_ThrowException() throws ArgumentException {
        Planet planetTo = new Planet();
        planetTo.setId("MA");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("MAR");
        planetFrom.setName("Mars");
        Client clientTicket = new Client();
        clientTicket.setName("John");
        assertThrows(ArgumentException.class, () -> daoTicket.create(clientTicket, planetTo, planetFrom));
    }

    @Test
    void create_SetToPlanetNameNotExistInBD_ThrowException() throws ArgumentException {
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Marss");
        Planet planetFrom = new Planet();
        planetFrom.setId("MAR");
        planetFrom.setName("Mars");
        Client clientTicket = new Client();
        clientTicket.setName("John");
        assertThrows(ArgumentException.class, () -> daoTicket.create(clientTicket, planetTo, planetFrom));
    }


    @Test
    void create_SetFromPlanetIdNotExistInBD_ThrowException() throws ArgumentException {
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("MA");
        planetFrom.setName("Mars");
        Client clientTicket = new Client();
        clientTicket.setName("John");
        assertThrows(ArgumentException.class, () -> daoTicket.create(clientTicket, planetTo, planetFrom));
    }

    @Test
    void create_SetFromPlanetNameNotExistInBD_ThrowException() throws ArgumentException {
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("MAR");
        planetFrom.setName("Marss");
        Client clientTicket = new Client();
        clientTicket.setName("John");
        assertThrows(ArgumentException.class, () -> daoTicket.create(clientTicket, planetTo, planetFrom));
    }

    @Test
    void getById_SetIdZerro_ThrowException() {
        assertThrows(ArgumentException.class, () -> daoTicket.getById(0));
    }

    @Test
    void getById_SetLessZerro_ThrowException() {
        assertThrows(ArgumentException.class, () -> daoTicket.getById(0));
    }

    @Test
    void getById_TicketIdNotEcxistInBD_ThrowException() {
         List<Ticket> tickets = daoTicket.listAll();
       int sizeOfListTickets=tickets.size();
        assertThrows(ArgumentException.class, () -> daoTicket.getById(tickets.get(sizeOfListTickets-1).getId()+10));
    }

    @Test
    void getById_TicketIdEcxistInBD_GetTicket() throws Exception {
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");
        Client clientTicket = new Client();
        clientTicket.setName("Tota");
        long idTicket = daoTicket.create(clientTicket, planetTo, planetFrom);
        assertNotNull(daoTicket.getById(idTicket));
    }
    @Test
    void deleteById_SetTicketIdEcxistInBD_DeleteTicket() throws Exception {
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");
        Client clientTicket = new Client();
        clientTicket.setName("Tota");
        long idTicket = daoTicket.create(clientTicket, planetTo, planetFrom);
        daoTicket.deleteById(idTicket);
         List<Long> idTickets = daoTicket.listAll().stream().map(ticket -> ticket.getId()).collect(Collectors.toList());
         assertEquals(true, idTickets.contains(idTicket));
    }


    @Test
    void deletById_SetIdZerro_ThrowException() {
        assertThrows(ArgumentException.class, () -> daoTicket.deleteById(0));
    }

    @Test
    void deletById_SetLessZerro_ThrowException() {
        assertThrows(ArgumentException.class, () -> daoTicket.deleteById(-1));
    }

    @Test
    void deletById_TicketIdNotEcxistInBD_ThrowException() {
        List<Ticket> tickets = daoTicket.listAll();
        int sizeOfListTickets=tickets.size();
        assertThrows(ArgumentException.class, () ->daoTicket.deleteById(tickets.get(sizeOfListTickets-1).getId()+10));
    }

    @Test
    void listAll_SetNewTicket_GetAll() throws Exception {
        List<Ticket> ticketsBeforAdding = daoTicket.listAll();
        int sizeOfListTickets=ticketsBeforAdding.size();
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");
        Client clientTicket = new Client();
        clientTicket.setName("Tota");
        daoTicket.create(clientTicket,planetTo,planetFrom);
        List<Ticket> ticketsAfterAdding = daoTicket.listAll();
        int sizeOfListTicketsAfterAdding=ticketsAfterAdding.size();
        assertEquals(sizeOfListTickets+1,sizeOfListTicketsAfterAdding);

    }

    @Test
    void updateByInformClient_StIdTicketLessZero_ThrowException() throws ArgumentException {
        Client clientTicket = new Client();
        clientTicket.setName("Tota");
        assertThrows(ArgumentException.class, () -> daoTicket.updateByInformClient(-1,clientTicket));
    }


    @Test
    void updateByInformClient_StIdTicketZero_ThrowException() throws ArgumentException {
        Client clientTicket = new Client();
        clientTicket.setName("Tota");
        assertThrows(ArgumentException.class, () -> daoTicket.updateByInformClient(0,clientTicket));
    }

    @Test
    void updateByInformClient_SetTicketIdEcxistInBD_DeleteTicket() throws Exception {

        Client clientTicket = new Client();
        clientTicket.setName("Tota");

        List<Long> idTickets = daoTicket.listAll().stream().map(ticket -> ticket.getId()).collect(Collectors.toList());
        int countidTickets = idTickets.size();
        assertThrows(ArgumentException.class, () -> daoTicket.updateByInformClient((idTickets.get(countidTickets - 1) + 1), clientTicket));

    }


    @Test
    void updateByInformClient_SetClientNull_ThrowException() throws Exception {
        Client clientTicket = null;
        List<Long> idTickets = daoTicket.listAll().stream().map(ticket -> ticket.getId()).collect(Collectors.toList());
        int countidTickets = idTickets.size();
        assertThrows(ArgumentException.class, () -> daoTicket.updateByInformClient((idTickets.get(countidTickets - 1)), clientTicket));

    }

    @Test
    void updateByInformClient_SetClientIdNotExcistInBD_ThrowException() throws Exception {
        ClientDAO clientDAO = new ClientCrudService(sessionFactory);
        List<Client> initialClients = clientDAO.listAll();
        int initialNumberClients = initialClients.size();

        Client newClient = new Client();
        newClient.setName("Anton");
        newClient.setId(initialClients.get(initialNumberClients-1).getId()+1);
        List<Long> idTickets = daoTicket.listAll().stream().map(ticket -> ticket.getId()).collect(Collectors.toList());
        int countidTickets = idTickets.size();
        assertThrows(ArgumentException.class, () -> daoTicket.updateByInformClient((idTickets.get(countidTickets - 1)), newClient));
    }
    @Test
    void updateByInformClient_SetClientValidTicketValid_ThrowException() throws Exception {
        Planet planetTo = new Planet();
        planetTo.setId("MAR");
        planetTo.setName("Mars");
        Planet planetFrom = new Planet();
        planetFrom.setId("EAR");
        planetFrom.setName("Earth");
        Client clientTicket = new Client();
        clientTicket.setName("oldClient");
        long idNewTitcket = daoTicket.create(clientTicket, planetTo, planetFrom);
        Client newClient = new Client();
        newClient.setName("newClient");
        daoTicket.updateByInformClient(idNewTitcket, newClient);
        assertEquals("newClient", daoTicket.getById(idNewTitcket).getClient().getName());
    }
}