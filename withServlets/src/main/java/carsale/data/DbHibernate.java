package carsale.data;


import carsale.models.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbHibernate implements Db {

    //реализация потокобезопасного синглтона.
    private static final DbHibernate INSTANCE = new DbHibernate();
    private static final Logger LOG = LoggerFactory.getLogger(DbHibernate.class);

    public static DbHibernate getInstance() {
        return INSTANCE;
    }

    private static final HibernateUtil HUTIL = HibernateUtil.getInstance();
    private SessionFactory sessionFactory = HUTIL.getSessionConf();
    //private PopulateModels models;

    public DbHibernate() {

        //this.models = new PopulateModels();
    }


    private <T> T wrapper(final Function<Session, T> command) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.warn((e.toString()));
            throw e;
        } finally {
            session.close();
        }
    }

    private Boolean wrapperBool(final Consumer<Session> command) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        boolean successful = false;
        try {
            command.accept(session);
            tx.commit();
            successful = true;
            return successful;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.warn((e.toString()));
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Add advertisment;
     */
    @Override
    public boolean addNewItem(Ads ads) {
        return this.wrapperBool(session -> session.save(ads));
    }

    @Override
    public boolean addNewRole(Roles role) {
        return this.wrapperBool(session -> session.save(role));
    }

    @Override
    public boolean updateItem(Ads ads) {
        return this.wrapperBool(session -> session.saveOrUpdate(ads));
    }

    @Override
    public boolean updateUser(Users user) {
        return this.wrapperBool(session -> session.saveOrUpdate(user));
    }



    @Override
    public boolean removeItem(Ads ad) {
        return this.wrapperBool(session -> session.delete(ad));
    }

    @Override
    public boolean removeUser(Users user) {
        return this.wrapperBool(session -> session.delete(user));
    }


    @Override
    public List<Ads> getAllItems() {
        return this.wrapper(
                session ->         session.createQuery("from Ads").list()
        );
    }


    @Override
    public List<Users> getUsers() {
        return this.wrapper(
                session -> {
                    Query query = session.createQuery("from Users where role_id = :paramName");
                    query.setParameter("paramName", 2);
                    return query.getResultList();
                }
        );
    }



    public int createUser(Users user) {
        Session session = HibernateUtil.getSessionXml().openSession();
        Integer id = null;
        try {
            session.beginTransaction(); //open transaction - provides atomicity!
            session.save(user);
            id = user.getId();
            session.getTransaction().commit();
        } catch (HibernateException r) {
            session.getTransaction().rollback();
            LOG.warn((r.toString()));
        } finally {
            session.close();
        }
        session.close();
        return id;
    }

    public boolean createCar(Car car) {
        return this.wrapperBool(session -> session.save(car));
    }


    @Override
    public Ads getAdById(int id) {
        return this.wrapper(session -> session.get(Ads.class, id));
    }

    @Override
    public Roles getRoleById(Integer id) {
        return this.wrapper(session -> session.get(Roles.class, id));
    }

    @Override
    public List<Roles> getAllRoles() {
        return this.wrapper(
                session ->         session.createQuery("from Roles").list()
        );
    }

    @Override
    public List<Brands> getBrands() {
        return this.wrapper(
                session ->         session.createQuery("from Brands").list()
        );
    }

    @Override
    public List<BodyType> getBodies() {
        return this.wrapper(
                session ->         session.createQuery("from BodyType").list()
        );
    }

    @Override
    public List<Engines> getEngines() {
        return this.wrapper(
                session ->         session.createQuery("from Engines").list()
        );
    }

    @Override
    public List<Models> getModelsById(int brandId) {
        return this.wrapper(
                session -> {
                    Query query = session.createQuery("from Models where brand_id = :paramName");
                    query.setParameter("paramName", brandId);
                    return query.getResultList();
                }
        );
    }

    @Override
    public boolean isLoginFree(String login) {
        return this.wrapper(
                session -> {
                    Query query = session.createQuery("from Users where login = :paramName");
                    query.setParameter("paramName", login);
                    boolean res = query.getResultList().isEmpty();
                    return res;
                }
        );
    }

    @Override
    public Users getUserById(Integer id) {
        return this.wrapper(session -> session.get(Users.class, id));
    }


    @Override
    public BodyType getBodyById(Integer id) {
        return this.wrapper(session -> session.get(BodyType.class, id));
    }

    @Override
    public Brands getBrandById(Integer id) {
        return this.wrapper(session -> session.get(Brands.class, id));
    }

    @Override
    public Models getModelById(Integer id) {
        return this.wrapper(session -> session.get(Models.class, id));
    }

    @Override
    public Engines getEngineById(Integer id) {
        return this.wrapper(session -> session.get(Engines.class, id));
    }

    @Override
    public Users getUserByLoginPass(String login, String pass) {
        Session session = sessionFactory.openSession();
        Users user = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Users" +
                    " where login = :paramLogin and password = :paramPass");
            query.setParameter("paramLogin", login);
            query.setParameter("paramPass", pass);
            List list = query.getResultList();
            if (list.size() != 0) {
                user = (Users) list.get(0);
            }
            session.getTransaction().commit();
        } catch (HibernateException r) {
            session.getTransaction().rollback();
            LOG.warn((r.toString()));
        } finally {
            session.close();
        }
        return user;
    }

    /*
     List<Post> posts = session.createQuery(
        "select distinct p " +
        "from Post p " +
        "join fetch p.comments c")
    .list();
     */
}
