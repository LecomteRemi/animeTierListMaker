package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class TierRow extends FlowPane {
    public Label tierLabel;
    public List<Unit> mediaList;

    private boolean isEndDummyActivated = false;
    private Label dummy;

    public TierRow(AnchorPane anchorPane) {
        MediaCursor.getInstance().AddTierRow(this);
        dummy = new Label("dummy");

        dummy.setMinSize(Unit.UNIT_WIDTH, Unit.UNIT_HEIGHT);
        this.mediaList = new ArrayList<Unit>();
        tierLabel = new Label("tier1");
        BackgroundFill background_fill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
        tierLabel.setBackground(new Background(background_fill));
        tierLabel.setMinHeight(100);

        BackgroundFill background_fill2 = new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY);
        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));


        TierRow tierRow = this;
        for (int i = 0; i < 10; i++) {
            Unit media = new Unit("media" + i);
            media.setAnchorPane(anchorPane);
            media.SetRow(tierRow);
            media.setMinHeight(100);
            media.setBackground(new Background(background_fill2));
            media.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
            // media.setOnMouseClicked(eventHandlerBox);
            mediaList.add(media);

        }
        this.getChildren().add(tierLabel);
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
}
