package controller;

import models.*;

import java.util.List;

public interface Controller {
    boolean addItem(Ads ads);
    boolean updateItem(Ads ads);
    boolean removeItem(Ads ads);
    boolean removeUser(Users user);
    List<Ads> getAllItems();
    List<Users> getUsers();
    Users getUserById(String id);
    Users getUserByLoginPass(String login, String pass);
    Integer createUser(Users user);
    boolean updateUser(Users user);
    boolean createCar(Car car);
    List<Roles> getAllRoles();
    List<Brands> getBrands();
    List<Models> getModelsById(int brandId);
    List<BodyType> getBodies();
    List<Engines> getEngines();
    boolean isLoginFree(String login);

    Ads getAdById(String id);
    Brands getBrandById(String id);
    Models getModelById(String id);
    BodyType getBodyById(String id);
    Engines getEngineById(String id);

}
