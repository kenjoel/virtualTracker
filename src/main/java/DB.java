import org.sql2o.*;


public class DB {
    public static String connection = "jdbc:postgresql://ec2-54-165-164-38.compute-1.amazonaws.com:5432/db3oif40117pv9" + "?sslmode=require";
    public static Sql2o sql2o = new Sql2o(connection, "wplwjmygxzheoh", "b478f40143eee55eab86d8ef0a8ec65807ebe4b37fb0ab45a975c01263f5b3ad");
//   public static String connection = "jdbc:postgresql://ec2-54-158-122-162.compute-1.amazonaws.com:5432/ddtlg2nhd0t1tf" + "?sslmode=require";
//   public  static Sql2o sql2o = new Sql2o(connection,"eotdlxhaxigrqc ","18485361f7aa35f658be1b843d817fc058cb2284e3c1eadfa08995047d8659fa");
}

//postgresql-round-42051

//connect to database locally


