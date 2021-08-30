package httpProtocole.project.Task2SecondVariant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class Main {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @SneakyThrows
    public static void main(String[] args) {
        String jsonObject = GSON.toJson(Client.getComments(setUser()));
        String newUrl = String.format("D:\\MyProject\\Hw-13\\src\\main\\java\\httpProtocole\\project\\Task2SecondVariant\\user-%s-post-%s-comments.gson"
                ,Client.getMaxId(setUser()),Client.getMaxId(setUser()));
        Files.write(Path.of(newUrl), Collections.singleton(jsonObject));

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
}
