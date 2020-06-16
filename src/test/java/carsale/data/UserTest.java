package carsale.data;

import carsale.models.Roles;
import carsale.models.Users;
import org.hsqldb.rights.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class UserTest {
    private static final Db STORAGE = DbHibernate.getInstance();

    @Test
    public void whenUserAddItsInStorage() {
       Roles role = STORAGE.getAllRoles().get(1);
       Users user = new Users("testOrig", "test", "test@t.tu", "+7888566", role);
       int i = STORAGE.createUser(user);
       Users resUser = STORAGE.getUserById(i);
       assertThat("testOrig", is(resUser.getLogin()));
       assertThat(user, is(resUser));
    }

    @Test
    public void whenUserRemovedItsNotInStorage() {
        Roles role = STORAGE.getAllRoles().get(1);
        Users user = new Users("testOrig", "test", "test@t.tu", "+7888566", role);
        int i = STORAGE.createUser(user);
        STORAGE.removeUser(user);
        Users resUser = STORAGE.getUserById(i);
        assertNull(resUser);
    }
}
