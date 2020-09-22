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


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animalnew", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animalForm.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangerednew", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return modelAndView(model,"endangeredForm.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightingsnew", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return modelAndView(model,"sightingsForm.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List peter = Endangered.relative_All();
            System.out.println(peter.get(0));
            model.put("endangered", peter);
            return modelAndView(model, "endangered.hbs");
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

            res.redirect("/endangered");
            return null;
        }, new HandlebarsTemplateEngine());



        //post methods
        post("/success", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            Endangered endangered = new Endangered(name, health, age);
            endangered.save();
            System.out.println("Please enter all input fields.");
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

    }


    }
