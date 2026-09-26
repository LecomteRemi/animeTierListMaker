package animeTierList.animeTierList;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TierPropertiesMenu extends VBox {
    private TextField tierLabel;
    private ColorPicker colorPicker;

    private Tier currentTier;
    private AnchorPane anchorPane;
    private boolean isEnabled = false;

    private Button okButton;
    private Button cancelButton;

    private Button addTierAboveButton;
    private Button addTierUnderButton;

    private Button deleteTierButton;

    public TierPropertiesMenu(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
        this.tierLabel = new TextField();
        this.getChildren().add(tierLabel);
        this.colorPicker = new ColorPicker();
        this.getChildren().add(colorPicker);

        this.addTierAboveButton = new Button("Add tier above");
        this.addTierAboveButton.setOnMouseClicked(e -> {
            currentTier.getTierList().addNewTier(currentTier.getIndexInList());
        });

        this.addTierUnderButton = new Button("Add tier under");
        this.addTierUnderButton.setOnMouseClicked(e -> {
            currentTier.getTierList().addNewTier(currentTier.getIndexInList() + 1);
        });

        this.getChildren().add(addTierAboveButton);
        this.getChildren().add(addTierUnderButton);
        this.okButton = new Button("OK");
        this.okButton.setOnMouseClicked(e -> {
            if (currentTier != null) {
                currentTier.getTierLabel().setLabel(tierLabel.getText());
                currentTier.getTierLabel().setBackGroundColor(colorPicker.getValue());
            }
            DisableMenu();
        });
        this.cancelButton = new Button("Cancel");
        this.cancelButton.setOnMouseClicked(e -> {
            DisableMenu();
        });
        HBox hbox = new HBox();
        hbox.getChildren().add(cancelButton);
        hbox.getChildren().add(okButton);
        this.getChildren().add(hbox);

        this.deleteTierButton = new Button("Delete tier");

        this.deleteTierButton.setOnMouseClicked(e -> {
            currentTier.getTierList().deleteTier(currentTier.getIndexInList());
            DisableMenu();
        });
        this.getChildren().add(deleteTierButton);

    }

    public void EnableMenu(Tier tier) {
        if (!isEnabled) {
            anchorPane.getChildren().add(this);
            isEnabled = true;
        }
        currentTier = tier;
        this.colorPicker.setValue(currentTier.getTierLabel().getBackGroundColor());
        this.tierLabel.setText(currentTier.getTierLabel().getLabel());
        this.deleteTierButton.setDisable(currentTier.getTierList().getTiersCount() == 1);
    }

    public void DisableMenu() {
        if (isEnabled) {
            isEnabled = false;
            anchorPane.getChildren().remove(this);
        }
    }
}
