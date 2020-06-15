package carsale.data;

import carsale.models.*;

import java.util.List;

public interface Db {
    boolean addNewRole(Roles role);
    boolean addNewItem(Ads ads);
    boolean updateItem(Ads ads);
    boolean removeItem(Ads ads);
    boolean removeUser(Users user);
    Roles getRoleById(Integer id);
    Ads getAdById(int id);
    List<Ads> getAllItems();
    List<Users> getUsers();
    Users getUserById(Integer id);
    Users getUserByLoginPass(String login, String pass);
    int createUser(Users user);
    boolean updateUser(Users user);
    boolean createCar(Car car);
    List<Roles> getAllRoles();
    List<Brands> getBrands();
    List<Models> getModelsById(int brandId);
    List<BodyType> getBodies();
    List<Engines> getEngines();
    boolean isLoginFree(String login);

    Brands getBrandById(Integer id);
    Models getModelById(Integer id);
    BodyType getBodyById(Integer id);
    Engines getEngineById(Integer id);
}
