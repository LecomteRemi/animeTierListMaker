package animeTierList.animeTierList;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public interface UnitRow {
    public ObservableList<Node> getChildren();

    public void addUnit(Unit unit);

    public void enableEndDummy();

    public void disableEndDummy();

    public Node getNode();

    public void removeUnit(Unit unit);

    public void replaceUnitByDummy(Unit unit);

    public void replaceDummyByUnit(Unit unit);

    public void disableReplacementDummy(Unit replacedUnit);

    public boolean containsUnit(Unit unit);

    public void clear();
}
