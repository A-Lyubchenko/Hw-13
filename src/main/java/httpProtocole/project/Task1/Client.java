package httpProtocole.project.Task1;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Client {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @SneakyThrows
    public static User createUser(String url, User user) {
        String requestBody = GSON.toJson(user);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type", "application/json")
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(send.body(), User.class);

    }

    @SneakyThrows
    public static User updateUser(String url, User user) {
        String newUrl = url + "/" + user.getId();
        String requestBody = GSON.toJson(user);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(newUrl))
                .headers("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(send.body(), User.class);

    }

    @SneakyThrows
    public static int getDeleteStatus(String url, User user) {
        String newUrl = url + "/" + user.getId();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(newUrl))
                .DELETE()
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return send.statusCode();

    }

    @SneakyThrows
    public static List<User> getUsers(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(send.body(), new TypeToken<List<User>>() {
        }.getType());

    }

    @SneakyThrows
    public static User getUserById(String url, User user) {
        String newUrl = url + "/" + user.getId();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(newUrl))
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(send.body(), User.class);


    }

    @SneakyThrows
    public static List<User> getUserByUserName(String url, User user) {
        String newUrl = url + "?username=" + user.getUsername();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(newUrl))
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(send.body(), new TypeToken<List<User>>(){}.getType());
    }
}
