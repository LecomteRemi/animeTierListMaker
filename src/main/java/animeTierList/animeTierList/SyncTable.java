package animeTierList.animeTierList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SyncTable extends VBox {
    private Button syncButton;
    private Button clearButton;
    private TextField usernameTextField;

    public SyncTable() {
        syncButton = new Button("sync");
        usernameTextField = new TextField();
        usernameTextField.setPromptText("username");
        syncButton.setOnMouseClicked(e -> {
            AllMediaList.getInstance().getDefaultRow().syncMedia(usernameTextField.getText());
        });
        HBox hbox = new HBox();
        hbox.getChildren().add(usernameTextField);
        hbox.getChildren().add(syncButton);
        this.getChildren().add(hbox);
    }
}
