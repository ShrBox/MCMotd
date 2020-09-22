package shrbox.github.mcmotd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Connection {
    public static String getURL(String address, String port) {
        try {
            URL url = new URL("http://motdpe.blackbe.xyz/api.php?ip=" + address + "&port=" + port);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) return "";
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String return_value = bufferedReader.readLine();
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
            return return_value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
