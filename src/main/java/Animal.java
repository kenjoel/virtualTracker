import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public  class Animal {
    public int id;
    public String name;


    public Animal( String name){
        this.name = name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void save(){
        String sql = "INSERT INTO animals(name) VALUES (:name);";
        try(Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery (sql, true)
                    .addParameter("name", name)
                    .executeUpdate ()
                    .getKey();
            setId(id);
        }catch (Sql2oException ex ){
            System.out.println(ex);

        }
    }




    public static List<Animal> relative_All(){
        String sql = "SELECT * FROM animals;";
        try(Connection con = DB.sql2o.open()){
            Query query =con.createQuery(sql);
            System.out.println(query.executeAndFetch(Endangered.class));
            return query.executeAndFetch(Animal.class);
        }
    }


    public static void clearAllAnimals() {
        String sql = "DELETE FROM animals *;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ey) {
            System.out.println(ey);
        }
    }


    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        }
    }


    public void update() {
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE animals SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void delete(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }
}
