package carsale.data;

import carsale.models.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AdsTest {
    private static final Db STORAGE = DbHibernate.getInstance();

    @Test
    @Ignore
    public void adAdd() {
        Roles role = STORAGE.getAllRoles().get(1);
        Users user = new Users("test", "test", "test@t.tu", "+7888566", role);
        Brands br = STORAGE.getBrandById(1);
        Models m = STORAGE.getModelById(1);
        Car car = new Car(br, m, STORAGE.getBodyById(1), STORAGE.getEngineById(1), "f", "f");
        Ads ad = new Ads(user, car);
        boolean i = STORAGE.addNewItem(ad);
        List<Ads> li = STORAGE.getAllItems();
        assertThat(ad, is(this.STORAGE.getAdById(ad.getId())));
    }
}
