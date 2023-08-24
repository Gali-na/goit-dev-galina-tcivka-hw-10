package DAO;

import model.Client;

import java.util.List;

public interface ClientDAO {
    public long create(String name) throws Exception;

    public String getById(long id) throws Exception;

    ;

    public void setName(long id, String name) throws Exception;

    ;

    public void deleteById(long id) throws Exception;

    ;

    public List<Client> listAll();

    ;
}
