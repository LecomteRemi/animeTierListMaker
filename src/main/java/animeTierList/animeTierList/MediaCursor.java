package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;

public class MediaCursor {
    
    private static MediaCursor instance;
    private List<TierRow> tierRows;
    private TierRow currentHoveredTierRow;

    public TierRow getCurrentHoveredTierRow() {
        return currentHoveredTierRow;
    }

    public void checkTierRowsMouseLocation(double x, double y) {

        for (TierRow tierRow : tierRows) {
            if (tierRow.contains(tierRow.sceneToLocal(x, y))) {
                if (currentHoveredTierRow != null) {
                    currentHoveredTierRow.disableEndDummy();
                }
                currentHoveredTierRow = tierRow;
                tierRow.activateEndDummy();
            } else {
                tierRow.disableEndDummy();
            }
        }
    }

    public static MediaCursor getInstance() {
        if (instance == null) {
            instance = new MediaCursor();
        }
        return instance;
    }

    private MediaCursor() {
        tierRows = new ArrayList<TierRow>();
    }

    public void AddTierRow(TierRow tierRow) {
        this.tierRows.add(tierRow);
    }
    private Label label = new Label("super test");



}
