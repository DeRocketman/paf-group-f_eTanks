package main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class HttpRequest {
    //Method 1: java.net.http.HttpClient -> use if java version >= 11
    //Method 2: java.net.HttpURLConnection -> use if java version < 11


    public static void main(String[] args) throws IOException {


        // Sending get request
        URL url = new URL("http://192.168.188.102:8080/user");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjM5MDY1OTM2LCJleHAiOjE2MzkxNTIzMzZ9.rk4r32-dP4RztsrxjzZQtjU1bW0-LmfG8FYquEGMIILyOJr3nZCR6PoSDyMmrrB7UkjapEHzsIlrrW_54mI5fA");

        // Aktueller Token eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXhpbWlsaWFtaTEiLCJpYXQiOjE2Mzg2NDYzNzAsImV4cCI6MTYzODczMjc3MH0.Od5_NG6980ti70eQUy2Xcfm_F0Jch6Vgx3Q7ovEPX1aYNSiQLxOGW3Z70ls39fUozy3tN1_xbe-f1D6Wbjk-_w

        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }

        in.close();
        // printing result from response
        System.out.println("Response:-" + response.toString());
    }
}
