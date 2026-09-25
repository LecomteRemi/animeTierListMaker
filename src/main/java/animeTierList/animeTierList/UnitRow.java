package animeTierList.animeTierList;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public interface UnitRow {
    public ObservableList<Node> getChildren();

    public void addUnit(Unit unit);

    public void activateEndDummy();

    public void disableEndDummy();

    public Node getNode();
}
