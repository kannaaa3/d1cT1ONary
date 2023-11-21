package unittest;

import model.database.Database;
import org.junit.Assert;
import org.junit.Test;

public class ModelUserDatabaseTest {
    @Test
    public void registerUserTest1() {
        clearDatabase();
        String userID = Database.register("AA", "BB");
        Assert.assertEquals(userID, Database.login("AA", "BB"));
        Database.closeConnection();
    }

    @Test
    public void registerUserTest2() {
        clearDatabase();
        String userID = Database.register("AA", "BB");
        Assert.assertEquals(userID, Database.login("AA", "BB"));
        userID = Database.register("CC", "DD");
        Assert.assertEquals(userID, Database.login("CC", "DD"));
        Database.closeConnection();
    }

    @Test
    public void containsUserTest1() {
        clearDatabase();
        Database.register("AA", "BB");
        Database.register("CC", "DD");
        Assert.assertTrue(Database.containsUsername("AA"));
        Assert.assertTrue(Database.containsUsername("CC"));
        Assert.assertFalse(Database.containsUsername("BB"));
        Assert.assertFalse(Database.containsUsername("DD"));
        Database.closeConnection();
    }

    /**
     * Function to reset all Database data.
     */
    public static void clearDatabase() {
        Database.removeAllUserRecord();
        Database.closeConnection();
    }
}