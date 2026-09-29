package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonConverter {
    public List<MediaData> convertMangaJson(String json) {
        List<MediaData> res = new ArrayList<MediaData>();
        convertMangaJson(json, res);
        return res;
    }

    public List<MediaData> convertMangaJson(List<String> jsonList) {
        List<MediaData> res = new ArrayList<MediaData>();
        for (String json : jsonList) {
            convertMangaJson(json, res);
        }
        return res;
    }

    private void convertMangaJson(String json, List<MediaData> res) {
        JSONObject jsonObject = new JSONObject(json);

        JSONArray mangaList = jsonObject.getJSONArray("data");
        for (int i = 0; i < mangaList.length(); i++) {
            JSONObject manga = mangaList.getJSONObject(i);
            // System.out.println(manga.getJSONObject("node").getString("title"));
            JSONObject mangaData = manga.getJSONObject("node");
            String originalTitle = mangaData.getString("title");
            String englishTitle = "";
            int id = mangaData.getInt("id");
            String imageURL = mangaData.getJSONObject("main_picture").getString("large");

            // System.out.println(originalTitle + " |" + imageURL + "|");
            MediaData mediaData = new MediaData(MediaType.MANGA, id, originalTitle, englishTitle, imageURL);
            res.add(mediaData);
        }
    }

    public String getNextRequestURL(String json) {
        JSONObject object = new JSONObject(json);
        if (!object.getJSONObject("paging").isNull("next")) {
            return object.getJSONObject("paging").getString("next");
        } else {
            return null;
        }
    }
}
