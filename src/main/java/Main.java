import DAO.TicketCrudService;
import model.Client;
import org.hibernate.SessionFactory;
import storage.HibernateUtil;

public class Main {
    public static void main(String[] args) throws Exception {
       SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
//        ClientDAO clientDAO = new ClientCrudService(sessionFactory);
//
//        long idNewClient = clientDAO.create("NewClient");
//        clientDAO.getById(1L);
//        clientDAO.listAll();
//        clientDAO.setName(idNewClient, "NewNAmeClient");
//        clientDAO.deleteById(idNewClient);
//        clientDAO.listAll();
//
//        PlanetDAO planetDAO = new PlanetCrudService(sessionFactory);
//        Planet newPlanet = new Planet();
//        newPlanet.setId("SUN2");
//        newPlanet.setName("START");
//         planetDAO.create(newPlanet);


//        try (Session session = sessionFactory.openSession()){
//            Transaction transaction = session.beginTransaction();
//            Client clientTicket = new Client();
//            clientTicket.setName("ClientTicetNewV9");
//
//
//            Planet toPlanet = new Planet();
//            toPlanet.setId("TYYYY1");
//            toPlanet.setName("STARTPlanetNewV9");
//
//            Planet fromPlanet = new Planet();
//            fromPlanet.setId("THHH");
//            fromPlanet.setName("FromPlanetNewV9");
//            session.persist(toPlanet);
//            session.persist(fromPlanet);
//            session.persist(clientTicket);
//            Ticket ticket = new Ticket();
//           // ticket.setId(999);
//            ticket.setToPlanet(toPlanet);
//            ticket.setFromPlanet(fromPlanet);
//            ticket.setClient(clientTicket);
//            session.persist(ticket);
//            transaction.commit();




//            Client clientTicket = new Client();
//            clientTicket.setId(50);
//            clientTicket.setName("John");
//
//            Planet toPlanet = new Planet();
//            toPlanet.setId("VEN");
//            toPlanet.setName("Venus");
//
//             Planet fromPlanet = new Planet();
//            fromPlanet.setId("EAR");
//            fromPlanet.setName("Earth");
//
//
            TicketCrudService ticketCrudService = new TicketCrudService(sessionFactory);
          //  ticketCrudService.create(clientTicket,toPlanet,fromPlanet);
       // System.out.println(ticketCrudService.getById(1));

      //  System.out.println(ticketCrudService.listAll());

         Client clientFoeUpdateTicket= new Client();
         clientFoeUpdateTicket.setId(2);
         clientFoeUpdateTicket.setName("Robert");

        ticketCrudService.updateByInformClient(1,clientFoeUpdateTicket);







//
    }


}
