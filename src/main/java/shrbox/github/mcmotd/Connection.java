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
            URL url = null;
            if (MMain.api == 1) {
                url = new URL("http://motdpe.blackbe.xyz/api.php?ip=" + address + "&port=" + port);
            } else {
                url = new URL("https://motdpe.xiaomen.online:10185/api.php?ip=" + address + "&port=" + port);
            }

            URLConnection urlConnection = null;
            HttpURLConnection httpURLConnection = null;

            for (int tryc = 1; tryc <= 2;) {
                urlConnection = url.openConnection();
                httpURLConnection = (HttpURLConnection) urlConnection;
                if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    if (MMain.api == 1) {
                        url = new URL("https://motdpe.xiaomen.online:10185/api.php?ip=" + address + "&port=" + port);
                    } else {
                        url = new URL("http://motdpe.blackbe.xyz/api.php?ip=" + address + "&port=" + port);
                    }
                    tryc++;
                } else {
                    MMain.api = 2;
                    tryc = 3;
                }
            }

            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "";
            }

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String re = bufferedReader.readLine();
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
            return re;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
