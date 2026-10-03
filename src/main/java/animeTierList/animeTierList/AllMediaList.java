package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AllMediaList {
    private static AllMediaList instance;

    private List<MediaData> mediaList;

    private HashMap<MediaData, String> reviews;
    private ListRow defaultRow;

    private List<UnitRow> allRows;

    private AllMediaList() {
        mediaList = new ArrayList<MediaData>();
        allRows = new ArrayList<UnitRow>();
        reviews = new HashMap<MediaData, String>();
    }

    public static AllMediaList getInstance() {
        if (instance == null)
            instance = new AllMediaList();
        return instance;
    }

    /*
     * public List<MediaData> getList() { return mediaList; }
     */

    public void addAllMedias(Collection<MediaData> medias) {
        mediaList.addAll(medias);
        for (MediaData mediaData : medias) {
            if (!reviews.containsKey(mediaData)) {
                reviews.put(mediaData, mediaData.getOriginalName());
            }
        }
    }

    public void setDefaultRow(ListRow listRow) {
        this.defaultRow = listRow;
    }

    public ListRow getDefaultRow() {
        return defaultRow;
    }
    public void addBackToDefaultRow(Unit unit) {
        if (defaultRow != null) {
            defaultRow.addUnit(unit);
        }
    }

    public List<UnitRow> getAllRows() {
        return allRows;
    }

    public String getReview(MediaData mediaData) {
        return reviews.get(mediaData);
    }

    public void setReview(MediaData mediaData, String review) {
        reviews.replace(mediaData, review);
    }

}
