package httpProtocole.project.Task3SecondVariant;

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
    private static final String URL_NAME = "https://jsonplaceholder.typicode.com/users/1/todos";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SneakyThrows
    public static List<User> getOpenTask(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        String newUrl = URL_NAME + "?completed=" + user.getCompleted();
        URL url = new URL(newUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        int responseCode = httpsURLConnection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK){
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))){
                String input;
                while ((input = bufferedReader.readLine())!=null){
                    stringBuilder.append(input);
                }
            }
        }
        return GSON.fromJson(stringBuilder.toString(),new TypeToken<List<User>>(){}.getType());
    }
}
