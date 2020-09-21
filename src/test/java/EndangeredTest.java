import org.junit.Test;

import static org.junit.Assert.*;


public class EndangeredTest {
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
        Endangered[]arr =new Endangered[] {tiger};
        assertTrue (Endangered.relative_All().contains(arr));
    }

}
