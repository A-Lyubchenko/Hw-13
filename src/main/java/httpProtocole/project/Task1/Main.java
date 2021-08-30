package httpProtocole.project.Task1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;


public class Main {
    private static final String URL_NAME = "https://jsonplaceholder.typicode.com/users";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SneakyThrows
    public static void main(String[] args) {
        //Task 1
        //System.out.println(GSON.toJson(Client.createUser(URL_NAME, setUser())));
        //Task 2
        //System.out.println(GSON.toJson(Client.updateUser(URL_NAME,setUser())));
        //Task 3
        //System.out.println(GSON.toJson(Client.getDeleteStatus(URL_NAME,setUser())));
        //Task 4
        //System.out.println(GSON.toJson(Client.getUsers(URL_NAME)));
        //Task 5
        //System.out.println(GSON.toJson(Client.getUserById(URL_NAME, setUser())));
        //Task 6
        //System.out.println(GSON.toJson(Client.getUserByUserName(URL_NAME, setUser())));

    }

    private static User setUser() {
        User user = new User();
        user.setName("Minny");
        user.setUsername("Bret");
        user.setEmail("mv.gmail.com");
        user.setId(1);
        return user;
    }



}
