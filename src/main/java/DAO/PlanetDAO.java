package DAO;

import exceptions.ArgumentException;
import model.Planet;

import java.util.List;

public interface PlanetDAO {
    public  String create(Planet planet) throws ArgumentException;
    public List<Planet> getAll();
    public Planet getById(String id) throws ArgumentException;
    public void deleteById(String Id) throws ArgumentException;
    public void updateById(String id, String name) throws ArgumentException;

}
