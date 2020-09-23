import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class AnimalTest {

    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connection = "jdbc:postgresql://localhost:5432/wildlife_tracker";
        Sql2o sql2o = new Sql2o(connection, "moringa", "://postgres");
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("database disconnecting");
        Animal.clearAllAnimals();
    }

    @AfterClass
    public static void logOut(){
        System.out.println("Beaming out... bye!");
        conn.close();
    }

    @Test
    public void instanceIsValid(){
        Animal animal = new Animal("rabbit");
        assertTrue(animal instanceof Animal);
    }

    @Test
    public void saveMethodWorks(){
        Animal bob = new Animal("kangaroo");
        bob.save();
        bob.testWhatsUp();
        Animal.relative_All();
        System.out.println("successfully saved");
        assertEquals(bob.getName(), Animal.relative_All().get(0).getName());
    }

    @Test
    public void updateWorks(){
        Animal frank = new Animal("piglet");
        frank.save();
        frank.update();
        assertEquals(frank.getName(), Animal.find(frank.getId()).getName());
    }



}