package httpProtocole.project.Task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;


public class Main {
    private static final String URL_NAME = "https://jsonplaceholder.typicode.com/users/1/todos";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        System.out.println(GSON.toJson(Client.getOpenTask(URL_NAME, setUser())));

    }

    private static User setUser() {
        User user = new User();
        user.setName("Minny");
        user.setUsername("Bret");
        user.setEmail("mv.gmail.com");
        user.setId(1);
        user.setPostId(10);
        user.setCompleted(true);
        return user;
    }
}