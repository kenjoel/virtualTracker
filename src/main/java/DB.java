import org.sql2o.*;


public class DB {
    public static String connection = "jdbc:postgresql://ec2-54-160-202-3.compute-1.amazonaws.com:5432/dd3eb1kj21jv1d";
    public  static Sql2o sql2o = new Sql2o(connection,"rmbmjsglawjnca","783febd5f6a94cb01223ea6cd0a67f292bb3fc4721fc62c54ffdd60349aa183f");
}

//postgres://rmbmjsglawjnca:783febd5f6a94cb01223ea6cd0a67f292bb3fc4721fc62c54ffdd60349aa183f@ec2-54-160-202-3.compute-1.amazonaws.com:5432/dd3eb1kj21jv1d


//connect to database locally
//localhost:5432/wildlife_tracker", "moringa", "://postgres"


