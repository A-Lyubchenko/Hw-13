package httpProtocole.project.Task1SecondVariant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Main {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        //Task1
        //System.out.println(GSON.toJson(Client.createUser(setUser())));
        //Task2
        //System.out.println(GSON.toJson(Client.updateUser(setUser())));
        //Task3
        //System.out.println(Client.deleteUser(setUser()));
        //Task4
        //Client.getAllUsers();
        //Task5
        //Task6
        //System.out.println(GSON.toJson(Client.getUserById(setUser())));
        //Task7
        System.out.println(GSON.toJson(Client.getUserByUserName(setUser())));


    }
    private static User setUser() {
       User user = new User();
        user.setName("Minny");
        user.setUsername("Bret");
        user.setEmail("mv.gmail.com");
        user.setId(3);
        return user;
    }
}