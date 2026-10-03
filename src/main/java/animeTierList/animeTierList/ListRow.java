package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ListRow extends VBox implements UnitRow {

    public List<MediaData> mediaList;
    public List<Unit> visibleUnitList;

    private boolean isEndDummyEnabled = false;
    private boolean isReplacementDummyEnabled = false;
    private Label dummy;
    private AnchorPane anchorPane;
    private HBox listNode;
    private ScrollBar scrollBar;

    private final int MAX_VISIBLE_UNIT_COUNT = 13;

    private int idx;

    public ListRow(AnchorPane anchorPane) {

        this.listNode = new HBox();
        this.getChildren().add(listNode);
        this.anchorPane = anchorPane;
        MediaCursor.getInstance().AddUnitRow(this);
        dummy = new Label("dummy");
        dummy.setMinHeight(Unit.UNIT_HEIGHT);
        dummy.setMinWidth(Unit.UNIT_WIDTH + BorderStroke.DEFAULT_WIDTHS.getRight());
        dummy.setMaxWidth(Unit.UNIT_WIDTH + BorderStroke.DEFAULT_WIDTHS.getRight());
        this.mediaList = new ArrayList<MediaData>();
        this.visibleUnitList = new ArrayList<Unit>();

        this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // System.out.println(mediaList.get(0).getMinWidth() * 10);
        // listNode.getChildren().addAll(mediaList);
        this.setMinHeight(100);

        this.scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.HORIZONTAL);
        scrollBar.valueProperty().addListener((ov, oldValue, newValue) -> {
            int tmp = (int) Math.floor((double) newValue);
            if (tmp != idx) {
                System.out.println(idx + "->" + tmp);
                idx = tmp;
                updateVisibleUnits();
                updateScrollBar();
            }

        });

        this.getChildren().add(scrollBar);

        updateScrollBar();
        AllMediaList.getInstance().getAllRows().add(this);

    }

    public void enableEndDummy() {

        if (!isEndDummyEnabled) {
            isEndDummyEnabled = true;
            if (visibleUnitList.size() < MAX_VISIBLE_UNIT_COUNT)
                listNode.getChildren().add(dummy);
        }

    }

    public void disableEndDummy() {

        if (isEndDummyEnabled) {
            isEndDummyEnabled = false;
            if (listNode.getChildren().contains(dummy))
                listNode.getChildren().remove(dummy);
        }

    }

    public void addUnit(Unit unit) {
        mediaList.add(unit.getMediaData());
        // listNode.getChildren().add(unit);
        updateVisibleUnits();
        updateScrollBar();
    }

    @Override
    public Node getNode() {
        return this;
    }

    public void syncMedia(String username) {

        Task<List<MediaData>> task = new Task<List<MediaData>>() {

            @Override
            protected List<MediaData> call() throws Exception {
                List<String> json = new MangaAnimeListRequester().requestMangaList(username);
                List<MediaData> mediaDataList = new ArrayList<MediaData>();
                mediaDataList.addAll(new JsonConverter().convertMangaJson(json));
                json = new MangaAnimeListRequester().requestAnimeList(username);
                mediaDataList.addAll(new JsonConverter().convertMangaJson(json));
                return mediaDataList;
            }
        };
        task.setOnSucceeded(e -> {

            ListRow listRow = this;
            System.out.println("ok1");
            int cpt = 0;
            this.mediaList.addAll(task.getValue());
            AllMediaList.getInstance().addAllMedias(task.getValue());
            System.out.println("ok2");
            // listNode.getChildren().clear();
            // listNode.getChildren().addAll(mediaList);
            System.out.println("ok3");
            updateVisibleUnits();
            updateScrollBar();
        });
        new Thread(task).start();

    }

    public void removeUnit(Unit unit) {
        if (mediaList.contains(unit.getMediaData())) {
            mediaList.remove(unit.getMediaData());
            visibleUnitList.remove(unit);
            updateScrollBar();
        }
    }

    public void clear() {
        mediaList.clear();
        listNode.getChildren().removeAll(visibleUnitList);
        visibleUnitList.clear();
        idx = 0;
        updateVisibleUnits();
        updateScrollBar();
    }

    @Override
    public void replaceUnitByDummy(Unit unit) {
        if (!isReplacementDummyEnabled) {
            if (mediaList.contains(unit.getMediaData()) && visibleUnitList.contains(unit)) {
                int idx = listNode.getChildren().indexOf(unit);
                listNode.getChildren().add(idx, dummy);
                listNode.getChildren().remove(unit);
                isReplacementDummyEnabled = true;

            }
        }

    }

    public void disableReplacementDummy(Unit replacedUnit) {
        if (isReplacementDummyEnabled) {
            isReplacementDummyEnabled = false;
            listNode.getChildren().remove(dummy);
            mediaList.remove(replacedUnit.getMediaData());
            visibleUnitList.remove(replacedUnit);
            updateVisibleUnits();
            updateScrollBar();
        }
    }

    @Override
    public void replaceDummyByUnit(Unit unit) {
        if (isReplacementDummyEnabled) {
            if (mediaList.contains(unit.getMediaData())) {
                int idx = listNode.getChildren().indexOf(dummy);
                listNode.getChildren().add(idx, unit);
                listNode.getChildren().remove(dummy);
                isReplacementDummyEnabled = false;
            }
        }

    }

    @Override
    public boolean containsUnit(Unit unit) {
        return this.listNode.getChildren().contains(unit);
    }

    private void updateScrollBar() {
        if (idx + MAX_VISIBLE_UNIT_COUNT > mediaList.size())
            scrollBar.setValue(idx);

        if (mediaList.size() > 0) {
            scrollBar.setVisible(true);
            scrollBar.setVisibleAmount(
                    MAX_VISIBLE_UNIT_COUNT /** (mediaList.size() - MAX_VISIBLE_UNIT_COUNT) / mediaList.size() */
            );
            scrollBar.setMax(mediaList.size() /*- MAX_VISIBLE_UNIT_COUNT*/);
        } else {
            scrollBar.setVisible(false);
        }
    }

    private void updateVisibleUnits() {

        if (idx + MAX_VISIBLE_UNIT_COUNT > mediaList.size())
            idx = Math.max(0, mediaList.size() - MAX_VISIBLE_UNIT_COUNT);
        while (visibleUnitList.size() > mediaList.size() - idx) {
            visibleUnitList.remove(visibleUnitList.size() - 1);
        }
        while (visibleUnitList.size() < MAX_VISIBLE_UNIT_COUNT && visibleUnitList.size() < mediaList.size() - idx) {
            Unit unit = new Unit();
            unit.setRow(this);
            unit.setAnchorPane(anchorPane);
            visibleUnitList.add(unit);
            this.listNode.getChildren().add(unit);
        }
        for (int i = 0; i < visibleUnitList.size(); i++) {
            if (idx + i < mediaList.size()) {
                visibleUnitList.get(i).setMediaData(mediaList.get(idx + i));
            }
        }
    }

}
