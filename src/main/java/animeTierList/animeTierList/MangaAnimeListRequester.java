package animeTierList.animeTierList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MangaAnimeListRequester {

    private final String malId = "675f43b8f0eaa726cd6b0abbb5742b26";
    private final String malIdHeader = "X-MAL-CLIENT-ID";

    public List<String> requestMangaList(String userName) {
        return requestMediaList(userName, "mangalist");
    }

    public List<String> requestAnimeList(String userName) {
        return requestMediaList(userName, "animelist");
    }

    public List<String> requestMediaList(String userName, String mediaListName) {
        String request = "https://api.myanimelist.net/v2/users/" + userName
                + "/" + mediaListName + "?fields=list_status&limit=100&nsfw=true";

        URL url;

        List<String> jsonList = new ArrayList<String>();
        try {
            boolean ended = false;
            while (request != null) {
                System.out.println(request);
                String json = sendMediaRequest(request);
                TimeUnit.SECONDS.sleep(2);
                request = new JsonConverter().getNextRequestURL(json);
                jsonList.add(json);
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonList;
    }

    private String sendMediaRequest(String request) throws MalformedURLException, IOException, ProtocolException {
        URL url;
        url = new URL(request);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty(malIdHeader, malId);

        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        // System.out.println(content);
        return content.toString();
    }
}
