import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class SightingsTest {

    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";               // "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "://postgres");
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("database disconnecting");
        Sightings.clearAllAnimals();
    }

    @AfterClass
    public static void logOut(){
        System.out.println("Beaming out... bye!");
        conn.close();
    }

    @Test
    public void instanceIsValid(){
        Sightings sightings = new Sightings("rabbit","moringa", "Nairobi");
        assertTrue(sightings instanceof Sightings);
    }

    @Test
    public void saveMethodWorks(){
        Sightings bob = new Sightings("kangaroo", "ken", "Kenya");
        bob.save();
        System.out.println("successfully saved");
        assertEquals(bob.getRangername(), Sightings.retrieveFromSightings().get(0).getRangername());
    }

}