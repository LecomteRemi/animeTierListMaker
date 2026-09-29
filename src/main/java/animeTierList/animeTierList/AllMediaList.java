package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

public class AllMediaList {
    private static AllMediaList instance;

    private List<MediaData> mediaList;
    private ListRow defaultRow;

    private AllMediaList() {
        mediaList = new ArrayList<MediaData>();
    }

    public static AllMediaList getInstance() {
        if (instance == null)
            instance = new AllMediaList();
        return instance;
    }

    public List<MediaData> getList() {
        return mediaList;
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

}
