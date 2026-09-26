package animeTierList.animeTierList;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;

public class Tier extends HBox {

    private TierLabel tierLabel;
    private TierRow tierRow;

    private final int MAX_UNIT_WIDTH = 10;
    private double width;

    private int indexInList;
    private TierList tierList;
    

    public TierList getTierList() {
        return tierList;
    }

    public int getIndexInList() {
        return indexInList;
    }

    public void setIndexInList(int indexInList) {
        this.indexInList = indexInList;
    }

    public Tier(String tierName, TierList tierList, AnchorPane anchorePane) {
        this.tierList = tierList;
        this.tierLabel = new TierLabel(tierName, this);
        this.tierRow = new TierRow(anchorePane);


        System.out.println(BorderStroke.DEFAULT_WIDTHS.getLeft());
        width = 616.5; // (double) (MAX_UNIT_WIDTH)* ((double) Unit.UNIT_WIDTH +
                     // BorderStroke.DEFAULT_WIDTHS.getLeft() * 2.0);
        System.out.println("width: " + width);
        tierRow.setPrefWidth(width);
        this.getChildren().add(tierLabel);
        this.getChildren().add(tierRow);
        this.setMinHeight(100);
        tierLabel.setMaxHeight(Double.MAX_VALUE);
        tierLabel.setPrefWidth(60);
    }

    public TierLabel getTierLabel() {
        return tierLabel;
    }
}
