package carsale.data;

import carsale.models.*;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AdsTest {
    private static final Db STORAGE = DbHibernate.getInstance();

    @Test
    public void adAdd() {
        Roles role = STORAGE.getAllRoles().get(1);
        Users user = new Users("testOrig", "test", "test@t.tu", "+7888566", role);
        int num = STORAGE.createUser(user);
        Brands br = STORAGE.getBrandById(1);
        Models m = STORAGE.getModelById(1);
        Car car = new Car(br, m, STORAGE.getBodyById(1), STORAGE.getEngineById(1), "f", "f");
        Timestamp dateNow = new Timestamp(System.currentTimeMillis());
        Ads ad = new Ads(user, car, "description original", dateNow, false, 45);
        STORAGE.addNewItem(ad);
        int id = ad.getId();
        assertThat("description original", is(STORAGE.getAdById(id).getDescr()));
    }
}
