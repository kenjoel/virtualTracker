import java.util.Timer;


public class Sightings extends Animal {
    private String animalName;
    private String rangername;
    private String location;
    public Timer created;

    public Sightings(String animalName, String rangername, String location) {
        this.id = id;
        this.animalName = animalName;
        this.rangername = rangername;
        this.location = location;
        created = new Timer();
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getRangername() {
        return rangername;
    }
}
