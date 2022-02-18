package app.foodapp.controller.checkInternetConnexion;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class InternetConnexion {
    public static void checkStatus() throws IOException {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            System.out.println("Internet is connected" + "\n");
        } catch (UnknownHostException e) {
            System.out.println(
                "You are not connected to internet " + "\n" +
                "Please, try again"
            );
        }
    }
}
