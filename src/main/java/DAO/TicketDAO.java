package DAO;

import model.Client;
import model.Planet;
import model.Ticket;

import java.util.List;

public interface TicketDAO {
    public long create(Client client, Planet toPlanet, Planet fromPlanet) throws Exception;
    public Ticket getById(long id) throws Exception;

    public void deleteById(long id) throws Exception;

    public List<Ticket> listAll();

    public void updateByInformClient(long ticketId,Client client) throws Exception;

}
