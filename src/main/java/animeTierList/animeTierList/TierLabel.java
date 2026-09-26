package animeTierList.animeTierList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class TierLabel extends HBox {
    private Label label;
    private GridPane optionsBox;
    private Button upButton;
    private Button optionButton;
    private Button downButton;

    private Tier tier;

    public Color backGroundColor;

    public TierLabel(String label, Tier tier) {
        this.tier = tier;

        optionsBox = new GridPane();
        upButton = new Button("up");

        int width = 50;
        upButton.setMinWidth(width);
        optionButton = new Button("...");
        optionButton.setMinWidth(width);
        downButton = new Button("down");
        downButton.setMinWidth(width);
        optionsBox.add(upButton, 0, 0);
        optionsBox.add(optionButton, 0, 1);
        optionsBox.add(downButton, 0, 2);

        upButton.setOnMouseClicked(e -> {
            tier.getTierList().moveTierUp(tier.getIndexInList());
        });
        downButton.setOnMouseClicked(e -> {
            tier.getTierList().moveTierDown(tier.getIndexInList());
        });
        optionButton.setOnMouseClicked(e -> {
            tier.getTierList().getTierPropertiesMenu().EnableMenu(tier);
        });

        this.getChildren().add(optionsBox);
        this.label = new Label(label);
        this.label.setMinWidth(width * 2);
        this.getChildren().add(this.label);
        this.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        setBackGroundColor(Color.BLUE);
        this.label.setTextAlignment(TextAlignment.CENTER);

        this.setHgrow(optionsBox, Priority.ALWAYS);
        this.setHgrow(this.label, Priority.ALWAYS);
    }

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
        this.setBackground(new Background(new BackgroundFill(backGroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public String getLabel() {
        return this.label.getText();
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }
}
