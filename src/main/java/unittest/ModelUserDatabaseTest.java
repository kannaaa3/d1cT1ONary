package unittest;

import model.database.Database;
import org.junit.Assert;
import org.junit.Test;

public class ModelUserDatabaseTest {
    @Test
    public void registerUserTest1() {
        clearDatabase();
        Database database = new Database();
        String userID = database.register("AA", "BB");
        Assert.assertEquals(userID, database.login("AA", "BB"));
        database.closeConnection();
    }

    @Test
    public void registerUserTest2() {
        clearDatabase();
        Database database = new Database();
        String userID = database.register("AA", "BB");
        Assert.assertEquals(userID, database.login("AA", "BB"));
        userID = database.register("CC", "DD");
        Assert.assertEquals(userID, database.login("CC", "DD"));
        database.closeConnection();
    }

    @Test
    public void containsUserTest1() {
        clearDatabase();
        Database database = new Database();
        database.register("AA", "BB");
        database.register("CC", "DD");
        Assert.assertTrue(database.containsUsername("AA"));
        Assert.assertTrue(database.containsUsername("CC"));
        Assert.assertFalse(database.containsUsername("BB"));
        Assert.assertFalse(database.containsUsername("DD"));
        database.closeConnection();
    }

    /**
     * Function to reset all database data.
     */
    public static void clearDatabase() {
        Database database = new Database();
        database.removeAllTables();
        database.closeConnection();
    }
}