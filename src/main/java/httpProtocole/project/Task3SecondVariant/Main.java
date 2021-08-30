package httpProtocole.project.Task3SecondVariant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Main {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
        System.out.println(GSON.toJson(Client.getOpenTask(setUser())));
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
