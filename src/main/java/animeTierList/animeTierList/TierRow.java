package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class TierRow extends FlowPane implements UnitRow {
    public List<Unit> mediaList;

    private boolean isEndDummyActivated = false;
    private Label dummy;

    public TierRow(AnchorPane anchorPane) {
        MediaCursor.getInstance().AddUnitRow(this);
        dummy = new Label("dummy");
        dummy.setMinHeight(Unit.UNIT_HEIGHT);
        dummy.setMinWidth(Unit.UNIT_WIDTH + BorderStroke.DEFAULT_WIDTHS.getRight());
        this.mediaList = new ArrayList<Unit>();


        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));


        TierRow tierRow = this;
        /*
         * for (int i = 0; i < 10; i++) { Unit media = new Unit("media" + i);
         * media.setAnchorPane(anchorPane); media.SetRow(tierRow); //
         * media.setMinHeight(100); // media.setOnMouseClicked(eventHandlerBox);
         * mediaList.add(media);
         * 
         * }
         */
        this.getChildren().addAll(mediaList);
        this.setMinHeight(100);

    }

    public void activateEndDummy() {
        if (!isEndDummyActivated) {
            isEndDummyActivated = true;
            this.getChildren().add(dummy);
        }
    }

    public void disableEndDummy() {
        if (isEndDummyActivated) {
            isEndDummyActivated = false;
            this.getChildren().remove(dummy);
        }
    }

    public void addUnit(Unit unit) {
        mediaList.add(unit);
        this.getChildren().add(unit);
    }

    @Override
    public Node getNode() {
        return this;
    }

    public void removeUnit(Unit unit) {
        if (mediaList.contains(unit)) {
            mediaList.remove(unit);
        }
    }

}
