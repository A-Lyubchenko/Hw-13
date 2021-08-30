package httpProtocole.project.Task2SecondVariant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Client {
    private static final String URL_NAME = "https://jsonplaceholder.typicode.com/users/%s/posts";
    private static final String COMMENTS_URI = "https://jsonplaceholder.typicode.com/posts/%s/comments";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SneakyThrows
    public static int getMaxId(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        String newString = String.format(URL_NAME, user.getId());
        URL url = new URL(newString);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        int responseCode = httpsURLConnection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))) {
                String input;
                while ((input = bufferedReader.readLine()) != null) {
                    stringBuilder.append(input);
                }
            }
        }
        List<User> userList = GSON.fromJson(stringBuilder.toString(), new TypeToken<List<User>>() {
        }.getType());
        return userList.stream().mapToInt(User::getId).max().orElse(0);
    }

    @SneakyThrows
    private static List<Comments> comments(int postId) {
        StringBuilder stringBuilder = new StringBuilder();
        String newUrl = String.format(COMMENTS_URI, postId);
        URL url = new URL(newUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }
        }
        return GSON.fromJson(stringBuilder.toString(), new TypeToken<List<Comments>>() {
        }.getType());

    }

    public static List<Comments> getComments(User user) {
        return comments(getMaxId(user));
    }
}
