package carsale.controller;

import carsale.data.Db;
import carsale.data.DbHibernate;
import carsale.models.Ads;
import carsale.models.Car;
import carsale.models.Roles;
import carsale.models.Users;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AdsControllerTest {

    private final AdsController controller = AdsController.getInstance();

    @Test
    public void whenAdAppliedItsInStorage() {
        Timestamp dateNow = new Timestamp(System.currentTimeMillis());
        Roles role = controller.getAllRoles().get(1);
        Users user = new Users("testOrig", "test", "test@t.tu", "+7888566", role);
        controller.createUser(user);
        byte[] byteArr = new byte[]{};
        Car carDetails = new Car(controller.getBrandById("1"),
                                 controller.getModelById("2"),
                                 controller.getBodyById("1"),
                                 controller.getEngineById("1"),
                                 "1999", "red");
        controller.createCar(carDetails);

        Ads ad = new Ads(user, carDetails, "description",
                dateNow, false, byteArr, 555);
        controller.addItem(ad);
        Ads retAd = controller.getAdById(ad.getId().toString());
        assertThat(ad, is(retAd));
    }

}