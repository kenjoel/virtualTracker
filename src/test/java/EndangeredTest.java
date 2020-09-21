import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class EndangeredTest {

    private static Endangered species; //ignore me for now. We'll create this soon. and my brother
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
        Endangered.clearAllAnimals();
    }

    @AfterClass
    public static void logOut(){
        System.out.println("Beaming out... bye!");
        conn.close();
    }

    @Test
    public void newInstanceValid(){
        Endangered endangered = new Endangered("panda", 3);
        assertTrue(endangered instanceof Endangered);
    }
    @Test
    public void saveMethodWorks(){
        Endangered tiger = new Endangered("tiger", 3);
        Endangered.save(tiger);
        System.out.println("successfully saved");
        assertEquals(tiger.getAnimalId(), Endangered.relative_All().get(0).getAnimalId());
        }

}
