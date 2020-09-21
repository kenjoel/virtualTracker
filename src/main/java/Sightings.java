import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Timer;


public class Sightings extends Animal {
    private String rangername;
    private String location;
    public Timer created;

    public Sightings(String rangername, String location) {
        this.id = id;
        this.rangername = rangername;
        this.location = location;
        created = new Timer();
    }

    public String getRangername() {
        return rangername;
    }
    public String getLocation(){return location; }



    //Methods for the sightings Table
    public static void save(Sightings sightings){
        String sql = "INSERT INTO sightings (rangername, location) VALUES (:rangername, :location);";
        try(Connection connection = DB.sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("rangername", sightings.rangername).addParameter("location", sightings.location)
                    .executeUpdate();
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

}
