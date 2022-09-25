package no.hvl.dat250.rest.todos;

import okhttp3.*;

import java.io.IOException;

public class PutRequest {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        //Todo todo = new Todo(Long.valueOf(args[0]), args[1], args[2]);
        Todo todo = new Todo(1l, "f", "bar");
        Request request = new Request.Builder()
                .url("http://localhost:8080/todos")
                .put(RequestBody.create(JSON, todo.toJson()))
                .build();

        System.out.println(request.toString());

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
