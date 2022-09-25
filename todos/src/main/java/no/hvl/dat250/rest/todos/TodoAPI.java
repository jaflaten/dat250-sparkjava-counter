package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static spark.Spark.*;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {

    static Todo todo = null;
    public static void main(String[] args) {


        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        todo = new Todo("f", "b");

        after((req, res) -> res.type("application/json"));

        // TODO: Implement API, such that the testcases succeed.

        get("/todos", (req, res) -> todo.toJson());
        delete("/todos", (req, res) -> {
            Gson gson = new Gson();

            todo = gson.fromJson(req.body(), Todo.class);
            return todo.toJson();
        });
        post("/todos", (req, res) -> {
            Gson gson = new Gson();

            todo = gson.fromJson(req.body(), Todo.class);
            return todo.toJson();
        });
        put("/todos", (req, res) -> {
            Gson gson = new Gson();

            todo = gson.fromJson(req.body(), Todo.class);
            return todo.toJson();
        });



    }


}
