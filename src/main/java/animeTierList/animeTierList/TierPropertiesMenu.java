package animeTierList.animeTierList;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TierPropertiesMenu extends VBox {
    private TextField tierLabel;

    public TierPropertiesMenu() {
        this.tierLabel = new TextField();
        this.getChildren().add(tierLabel);
    }
}
