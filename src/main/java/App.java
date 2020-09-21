import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker"; //connect to todolist, not todolist_test!\n";  //"jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "://postgres");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Endangered animal = new Endangered("PANDA",1);
            Endangered.save(animal);
            Sightings sight = new Sightings("Pandemic", "Bob","Zoo B");
            Sightings.save(sight);
            List<Endangered> allCategories = Endangered.relative_All();
            model.put("animals", allCategories);

            List<Sightings> sightings = Sightings.retrieveFromSightings();
            model.put("sightings", sightings);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//        //get: show a form to create a new category
//        get("/categories/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<> categories = categoryDao.getAll(); //refresh list of links for navbar
//            model.put("categories", categories);
//            return new ModelAndView(model, "category-form.hbs"); //new layout
//        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new category
        post("/sightings", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String animalname = req.queryParams("animalname");
            String rangername = req.queryParams("rangername");
            String location = req.queryParams("location");

            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

    }


    }
