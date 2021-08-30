package httpProtocole.project.Task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Main {
    private static final String URL_NAME = "https://jsonplaceholder.typicode.com/users/%s/posts";
    private static final String COMMENTS_URI = "https://jsonplaceholder.typicode.com/posts/%s/comments";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "D:\\MyProject\\Hw-13\\src\\main\\resources\\user-%s-post-%s-comments.json";

    @SneakyThrows
    public static void main(String[] args) {
        String result = GSON.toJson(Client.getCommentByPost(URL_NAME, COMMENTS_URI, setUser()));
        String format = String.format(FILE_NAME, Client.getMaxId(URL_NAME, setUser()), Client.getMaxId(URL_NAME, setUser()));
        Files.writeString(Path.of(format), result);


    }

    private static User setUser() {
        User user = new User();
        user.setName("Minny");
        user.setUsername("Bret");
        user.setEmail("mv.gmail.com");
        user.setId(1);
        user.setPostId(1);
        return user;
    }

    private static Comments setComments() {
        Comments comments = new Comments();
        comments.setName("Minny");
        comments.setBody("Bret");
        comments.setEmail("mv.gmail.com");
        comments.setId(1);
        comments.setPostId(1);
        return comments;
    }


}
