package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;

public class MediaCursor {
    
    private static MediaCursor instance;
    private List<UnitRow> unitRows;
    private UnitRow currentHoveredUnitRow;

    public UnitRow getCurrentHoveredUnitRow() {
        return currentHoveredUnitRow;
    }

    public void checkTierRowsMouseLocation(double x, double y) {
        currentHoveredUnitRow = null;
        for (UnitRow unitRow : unitRows) {
            if (unitRow.getNode().contains(unitRow.getNode().sceneToLocal(x, y))) {
                if (currentHoveredUnitRow != null) {
                    currentHoveredUnitRow.disableEndDummy();
                }
                currentHoveredUnitRow = unitRow;
                unitRow.activateEndDummy();
            } else {
                unitRow.disableEndDummy();
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
        unitRows = new ArrayList<UnitRow>();
    }

    public void AddUnitRow(UnitRow unitRow) {
        this.unitRows.add(unitRow);
    }
    private Label label = new Label("super test");



}
