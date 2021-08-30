package httpProtocole.project.Task3;

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
    public static List<User> getOpenTask(String uri, User user) {
        String newUri = uri + "?completed=" + user.getCompleted();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(newUri))
                .headers("Content-Type", "application/json")
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(send.body(), new TypeToken<List<User>>() {
        }.getType());
    }
}