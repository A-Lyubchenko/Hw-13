package httpProtocole.project.Task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Client {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SneakyThrows
    private static List<Comments> getComments(String uriComments,int postId) {
        String newUri = String.format(uriComments,postId);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(newUri))
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
       return GSON.fromJson(send.body(), new TypeToken<List<Comments>>() {
        }.getType());

    }
    @SneakyThrows
    public static int getMaxId(String uriUser,User user){
        String newUri = String.format(uriUser,user.getId());

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(newUri))
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        List<User> userList = GSON.fromJson(send.body(),new TypeToken<List<User>>(){}.getType());
        return userList.stream().mapToInt(User::getId).max().orElse(0);

    }
    public static List<Comments> getCommentByPost(String post,String comments,User user){
        int maxId = getMaxId(post, user);
       return getComments(comments,maxId);
    }
    }

