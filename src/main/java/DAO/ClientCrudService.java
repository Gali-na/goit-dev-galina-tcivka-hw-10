package DAO;

import exceptions.ArgumentException;
import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class ClientCrudService implements ClientDAO {
    private  final SessionFactory sessionFactory;

    public ClientCrudService(SessionFactory sessionFactory) {

       this.sessionFactory =sessionFactory;
    }

    public List<Client> listAll() {

        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
             return session.createQuery("from Client", Client.class).list();
        }
    }
/*  try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
    }*/
    public void deleteById(long id) throws ArgumentException {
        Client client;
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class, id);
            if (client!=null){
                session.remove(client);
            }else {
                throw new ArgumentException("The specified id does not exist in the database");
            }
            transaction.commit();
        }


    }

    public void setName(long id, String name) throws ArgumentException {
        nameValidation(name);
        Client client;
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class, id);
            if (client!=null){
                client.setName(name);
            }
        else {
                throw new ArgumentException("The specified id does not exist in the database");
            }
            transaction.commit();
        }
    }



    public long create(String name) throws ArgumentException {
        long id;
        nameValidation(name);
        Client client=new Client();
        client.setName(name);
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            id= client.getId();
            transaction.commit();
        }
        return id;
    }



    public String getById(long id) throws ArgumentException {
        Client client;
        try (Session session = sessionFactory.openSession()){
             Transaction transaction = session.beginTransaction();
             client = session.get(Client.class, id);
            transaction.commit();
        }
        if(client==null){
            throw new ArgumentException("The specified id does not exist in the database");
        }
        return client.getName();
    }


    private void nameValidation(String name) throws ArgumentException {
        if (name == null) {
            throw new ArgumentException("Argument cannot be null");

        }
        if (name.length() > 200 || name.length() < 2) {
            throw new ArgumentException("The name is incorrect, the number of characters in" +
                    " the name must not be less than 2 or more than 1000");
        }
    }

}


