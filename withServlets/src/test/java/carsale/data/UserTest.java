package carsale.data;

import carsale.models.Roles;
import carsale.models.Users;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    private static final Db STORAGE = DbHibernate.getInstance();

    @Test
    //@Ignore
    public void userAdd() {
       Roles role = STORAGE.getAllRoles().get(1);
       Users user = new Users("test", "test", "test@t.tu", "+7888566", role);
       int i = STORAGE.createUser(user);
       assertThat(user, is(this.STORAGE.getUserById(user.getId())));
    }
}
