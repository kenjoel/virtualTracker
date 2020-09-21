import org.junit.Test;

import static org.junit.Assert.*;

public class SightingsTest {

    @Test
    public void instanceIsValid(){
        Sightings sightings = new Sightings("cow","marcus","moringa");
        assertTrue(sightings instanceof Sightings);
    }

}