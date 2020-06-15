package carsale.controller;

import carsale.data.DbHibernate;
import carsale.data.Db;
import carsale.models.*;

import java.util.List;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class AdsController implements Controller {

    private static final AdsController INSTANCE = new AdsController();

    private AdsController() {
    }

    public static AdsController getInstance() {
        return INSTANCE;
    }

    private static final Db STORAGE = DbHibernate.getInstance();

    @Override
    public boolean addItem(Ads ads) {
        return STORAGE.addNewItem(ads);
    }

    @Override
    public boolean updateItem(Ads ads) {
        return STORAGE.updateItem(ads);
    }

    @Override
    public boolean updateUser(Users user) {
        return STORAGE.updateUser(user);
    }

    @Override
    public boolean removeUser(Users user) {
        return STORAGE.removeUser(user);
    }

    @Override
    public boolean removeItem(Ads ads) {
        return STORAGE.removeItem(ads);
    }

    @Override
    public List<Ads> getAllItems() {
        return STORAGE.getAllItems();
    }

    @Override
    public  List<Users> getUsers() {
        return STORAGE.getUsers();
    }

    @Override
    public boolean isLoginFree(String login) {
        return STORAGE.isLoginFree(login);
    }

    @Override
    public Integer createUser(Users user) {
        return STORAGE.createUser(user);
    }

    @Override
    public boolean createCar(Car car) {
        return STORAGE.createCar(car);
    }

    @Override
    public Users getUserByLoginPass(String login, String pass) {
        Users user = STORAGE.getUserByLoginPass(login, pass);
        return user;
    }

    @Override
    public Ads getAdById(String id) {
        return STORAGE.getAdById(Integer.parseInt(id));
    }

    @Override
    public Users getUserById(String id) {
        return STORAGE.getUserById(Integer.parseInt(id));
    }

    @Override
    public Brands getBrandById(String id) {
        return STORAGE.getBrandById(Integer.parseInt(id));
    }

    @Override
    public Models getModelById(String id) {
        return STORAGE.getModelById(Integer.parseInt(id));
    }

    @Override
    public BodyType getBodyById(String id) {
        return STORAGE.getBodyById(Integer.parseInt(id));
    }

    @Override
    public Engines getEngineById(String id) {
        return STORAGE.getEngineById(Integer.parseInt(id));
    }

    @Override
    public List<Roles> getAllRoles() {
        return STORAGE.getAllRoles();
    }

    @Override
    public List<Brands> getBrands() {
        return STORAGE.getBrands();
    }

    @Override
    public List<Models> getModelsById(int brandId) {
        return STORAGE.getModelsById(brandId);
    }

    @Override
    public List<BodyType> getBodies() {
        return STORAGE.getBodies();
    }

    @Override
    public List<Engines> getEngines() {
        return STORAGE.getEngines();
    }


   /* @Override
    public Boolean changeItemDone(int itemId, boolean state) {
        return STORAGE.changeItemDone(itemId, state);
    }*/
}
