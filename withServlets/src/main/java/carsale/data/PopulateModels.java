package carsale.data;

import carsale.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class PopulateModels {
    private static final Logger LOG = LoggerFactory.getLogger(PopulateModels.class);
    private SessionFactory sessionFactory;


    public PopulateModels() {
        sessionFactory = HibernateUtil.getSessionConf();
        fillRoles();
        fillBrandsAndModels();
        fillEngines();
        fillBodies();
    }

    private void fillRoles() {
        if (checkIfTableIsEmpty("roles")) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Roles role = new Roles(1, "Guest");
            session.save(role);
            session.getTransaction().commit();

            session.beginTransaction();
            role = new Roles(2, "User");
            session.save(role);
            session.getTransaction().commit();

            session.beginTransaction();
            role = new Roles(3, "Admin");
            session.save(role);
            session.getTransaction().commit();

            session.close();
        }
    }


    private void fillBrandsAndModels() {
        if (checkIfTableIsEmpty("brands")) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Map<String, List<String>> brendAndModelMap = Map.of("Ford", List.of("Focus", "Mustang", "Fusion"),
                                                                "BMW", List.of("M6", "X6", "i8"),
                                                                "Subaru", List.of("Outback", "Legasy", "Impreza"),
                                                                "Honda", List.of("Accord", "Civik", "Pilot"),
                                                                "Ваз", List.of("Ларгус", "Веста", "2121"));
            long i = 0;
            for (Map.Entry<String, List<String>> pair : brendAndModelMap.entrySet()) {
                Brands brand = new Brands(pair.getKey());
                session.saveOrUpdate(brand);
                for (String modelName : pair.getValue()) {
                    Models model = new Models(modelName, brand);
                    session.saveOrUpdate(model);
                }
            }
            session.getTransaction().commit();
            session.close();
        }
    }

    private void fillEngines() {
        if (checkIfTableIsEmpty("engines")) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<String> enginesList = List.of("Инжектор", "Карбюратор", "Турбо дизель", "Турбо бензин");
            long i = 0;
            for (String en : enginesList) {
                Engines engine = new Engines(en);
                session.saveOrUpdate(engine);
            }
            session.getTransaction().commit();
            session.close();
        }
    }

    private void fillBodies() {
        if (checkIfTableIsEmpty("bodytype")) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<String> bodiesList = List.of("Седан", "Универсал", "Пикап");
            long i = 0;
            for (String b : bodiesList) {
                BodyType body = new BodyType(b);
                session.saveOrUpdate(body);
            }
            session.getTransaction().commit();
            session.close();
        }
    }


    private boolean checkIfTableIsEmpty(String checkTable) {
        final Session session = sessionFactory.openSession();
        Boolean res = false;
        try {
            String sql = "select * from " + checkTable;
            Query query = session.createSQLQuery(sql);
            res = query.getResultList().isEmpty();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.warn((e.toString()));
            throw e;
        } finally {
            session.close();
        }
        return res;
    }
}
