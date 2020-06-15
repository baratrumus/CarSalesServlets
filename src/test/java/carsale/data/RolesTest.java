package carsale.data;

import carsale.models.Roles;
import carsale.models.Users;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RolesTest {
    private static final Db STORAGE = DbHibernate.getInstance();

    @Test
    public void adARole() {
        Roles role = new Roles("test");
        boolean i = STORAGE.addNewRole(role);
        assertThat("test", is(STORAGE.getRoleById(role.getId()).getRoleName()));
    }
}
