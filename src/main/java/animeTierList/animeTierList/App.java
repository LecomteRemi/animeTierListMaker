package animeTierList.animeTierList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {


    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        VBox tierList = new VBox();
        tierList.setMinWidth(800);
        tierList.setMaxWidth(800);
        tierList.setViewOrder(3);
        AnchorPane anchorPane = new AnchorPane(tierList);

        for (int i = 0; i < 3; i++) {
            TierRow tierRow = new TierRow(anchorPane);
            tierList.getChildren().add(tierRow);
        }

        var scene = new Scene(anchorPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}