import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.util.List;

public class Endangered extends  Animal{
    private int animalId;

    //Constants health
    public final int HEALTHY = 10;
    public final int ILL = 0;
    public final int OKAY = 5;

    //Constants age
    public final int NEWBORN = 0;
    public final int YOUNG = 4;
    public final int OLD = 8;

    public Endangered(String name, int animalId){
        this.name = name;
        this.animalId = animalId;
    }

    public static void save(Endangered endangered){
        String sql = "INSERT INTO endangered(name,animalId) VALUE (:name, :animalId)";
        try(Connection con = DB.sql2o.open()){
            con.createQuery (sql).bind (endangered).executeUpdate ();

        }catch (Sql2oException ex ){
            System.out.println(ex);

        }
    }

    public static List<Endangered> relative_All(){
        String sql = "SELECT * FROM endangered ";

        try(Connection con = DB.sql2o.open()){
            Query query =con.createQuery(sql);
            System.out.println(query.executeAndFetch(Endangered.class));
            return query.executeAndFetch(Endangered.class);

        }
    }
}
