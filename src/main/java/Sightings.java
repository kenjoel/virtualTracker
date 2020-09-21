import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sightings{
    private String animalname;
    private String rangername;
    private String location;
    private int id;


    public Sightings(String animalname, String rangername, String location) {
        this.rangername = rangername;
        this.location = location;
        this.animalname = animalname;
    }

    public String getRangername() {
        return rangername;
    }
    public String getLocation(){return location; }


    public String getAnimalname() {
        return animalname;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Methods for the sightings Table
    public static void save(Sightings sightings){
        String sql = "INSERT INTO sightings (animalname, rangername, location) VALUES (:animalname, :rangername, :location);";
        try(Connection connection = DB.sql2o.open()){
            int id = (int) connection.createQuery(sql, true)
                    .addParameter("animalname", sightings.animalname)
                    .addParameter("rangername", sightings.rangername)
                    .addParameter("location", sightings.location)
                    .executeUpdate().getKey();
            sightings.setId(id);
        }catch (Sql2oException ex ){
            System.out.println(ex);
        }
    }

    public static List<Sightings> retrieveFromSightings(){
        String sql = "SELECT * FROM sightings ";

        try(Connection con = DB.sql2o.open()){
            Query query =con.createQuery(sql);
            System.out.println(query.executeAndFetch(Sightings.class));
            return query.executeAndFetch(Sightings.class);
        }
    }

    public static void clearAllAnimals() {
        String sql = "DELETE FROM sightings *;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ey) {
            System.out.println(ey);
        }
    }

}
