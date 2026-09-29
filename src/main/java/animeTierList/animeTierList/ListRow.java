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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ListRow extends HBox implements UnitRow {
    public List<Unit> mediaList;

    private boolean isEndDummyActivated = false;
    private Label dummy;
    private AnchorPane anchorPane;

    public ListRow(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
        MediaCursor.getInstance().AddUnitRow(this);
        dummy = new Label("dummy");
        dummy.setMinHeight(Unit.UNIT_HEIGHT);
        dummy.setMinWidth(Unit.UNIT_WIDTH + BorderStroke.DEFAULT_WIDTHS.getRight());
        this.mediaList = new ArrayList<Unit>();


        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));




        // System.out.println(mediaList.get(0).getMinWidth() * 10);
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

    public void syncMedia(String username) {
        List<String> json = new MangaAnimeListRequester().requestMangaList(username);
        List<MediaData> mediaDataList = new JsonConverter().convertMangaJson(json);
        json = new MangaAnimeListRequester().requestAnimeList(username);
        mediaDataList.addAll(new JsonConverter().convertMangaJson(json));

        ListRow listRow = this;
        System.out.println("ok1");
        for (MediaData mediaData : mediaDataList) {
            if (!AllMediaList.getInstance().getList().contains(mediaData)) {
                AllMediaList.getInstance().getList().add(mediaData);
                Unit media = new Unit(mediaData);
                media.setAnchorPane(anchorPane);
                media.SetRow(listRow);
                // media.setMinHeight(100);
                // media.setOnMouseClicked(eventHandlerBox);
                mediaList.add(media);
            }
        }
        this.getChildren().clear();
        this.getChildren().addAll(mediaList);
    }

    public void removeUnit(Unit unit) {
        if (mediaList.contains(unit)) {
            mediaList.remove(unit);
        }
    }

}
