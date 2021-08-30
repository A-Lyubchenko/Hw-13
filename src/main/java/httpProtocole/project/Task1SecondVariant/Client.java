package httpProtocole.project.Task1SecondVariant;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Client {
    private static final String URL_NAME = "https://jsonplaceholder.typicode.com/users";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SneakyThrows
    public static void getAllUsers() {
        URL newUrl = new URL(URL_NAME);
        InputStream inputStream = newUrl.openStream();
        byte[] bytes = inputStream.readAllBytes();
        String users = new String(bytes);
        System.out.println(users);
    }

    @SneakyThrows
    public static User createUser(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        String jsonUser = GSON.toJson(user);
        URL url = new URL(URL_NAME);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))) {
            bufferedWriter.write(jsonUser);
        }
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
            }

        }
        return GSON.fromJson(stringBuilder.toString(), User.class);
    }

    @SneakyThrows
    public static User updateUser(User user) {
        String newUrl = URL_NAME + "/" + user.getId();
        StringBuilder stringBuilder = new StringBuilder();
        String jsonUser = GSON.toJson(user);
        URL url = new URL(newUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestMethod("PUT");
        httpsURLConnection.setRequestProperty("Content-Type", "application/json");
        httpsURLConnection.setDoOutput(true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpsURLConnection.getOutputStream()))) {
            bufferedWriter.write(jsonUser);
        }
        Thread.sleep(1000);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }
        }
        return GSON.fromJson(stringBuilder.toString(), User.class);
    }

    @SneakyThrows
    public static int deleteUser(User user) {
        String newUrl = URL_NAME + "/" + user.getId();
        String jsonUser = GSON.toJson(user);
        URL url = new URL(newUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestMethod("DELETE");
        httpsURLConnection.setDoOutput(true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpsURLConnection.getOutputStream()))) {
            bufferedWriter.write(jsonUser);
        }

        return httpsURLConnection.getResponseCode();

    }

    @SneakyThrows
    public static User getUserById(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        String newUrl = URL_NAME + "/" + user.getId();
        URL url = new URL(newUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestMethod("GET");
        int responseCode = httpsURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        }
        return GSON.fromJson(stringBuilder.toString(), User.class);
    }

    @SneakyThrows
    public static List<User> getUserByUserName(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        String newUrl = URL_NAME + "?username=" + user.getUsername();
        URL url = new URL(newUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        int responseCode = httpsURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))) {
                String input;
                while ((input = bufferedReader.readLine()) != null) {
                    stringBuilder.append(input);
                }
            }
        }
        return GSON.fromJson(stringBuilder.toString(),new TypeToken<List<User>>(){}.getType());
    }
}


