package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Optional;

import static spark.Spark.*;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {

    static ArrayList<Todo> todos = new ArrayList<>();
    static long ids = 1;
    public static void main(String[] args) {


        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }


        after((req, res) -> res.type("application/json"));

        // TODO: Implement API, such that the testcases succeed.

        get("/todos", (req, res) -> {
            Gson gson = new Gson();

            return gson.toJson(todos);
        });

        get("/todos/:id", (req, res) -> {
            long param;
            try {
                param = Long.parseLong(req.params(":id"));
            }
            catch (NumberFormatException nfe) {
                return String.format("The id \"%s\" is not a number!", req.params(":id"));
            }

            Optional<Todo> found = todos.stream().filter(t -> t.getId().equals(param)).findFirst();

            if(found.isPresent()) {
                return found.get().toJson();
            } else {
                return String.format("Todo with the id  \"%s\" not found!", param);
            }

        });

        delete("/todos/:id", (req, res) -> {
            long param;
            try {
                param = Long.parseLong(req.params(":id"));
            }
            catch (NumberFormatException nfe) {
                return String.format("The id \"%s\" is not a number!", req.params(":id"));
            }
            Optional<Todo> found = todos.stream().filter(t -> t.getId().equals(param)).findFirst();
            if(found.isPresent()) {
                todos.remove(found.get());
            }
            return "deleted id: " + found.get().getId().toString();
        });

        post("/todos", (req, res) -> {
            Gson gson = new Gson();

            Todo todo = gson.fromJson(req.body(), Todo.class);
            Todo returnedTodo = new Todo(ids, todo.getSummary(), todo.getDescription());

            todos.add(returnedTodo);
            ids++;

            return returnedTodo.toJson();
        });
        put("/todos/:id", (req, res) -> {
            Gson gson = new Gson();

            Todo todo = gson.fromJson(req.body(), Todo.class);

            long param;
            try {
                param = Long.parseLong(req.params(":id"));
            }
            catch (NumberFormatException nfe) {
                return String.format("The id \"%s\" is not a number!", req.params(":id"));
            }
            Optional<Todo> found = todos.stream().filter(t -> t.getId().equals(param)).findFirst();

            if(found.isPresent()) {
                todos.remove(found.get());
                todos.add(todo);
                return todo;
            } else {
                return String.format("Todo with the id  \"%s\" not found!", param);
            }
        });

//

    }


}
