package animeTierList.animeTierList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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



        VBox container = new VBox();
        container.setViewOrder(3);
        AnchorPane anchorPane = new AnchorPane(container);

        TierList tierList = new TierList(anchorPane);
        tierList.setMinWidth(800);
        tierList.setMaxWidth(800);
        ListRow listRow = new ListRow(anchorPane);
        listRow.setMinWidth(616.5);
        ScrollPane scrollPane = new ScrollPane(listRow);
        scrollPane.setMaxWidth(616.5);
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        // scrollPane.setMaxWidth(Double.MAX_VALUE);
        scrollPane.setPrefHeight(Unit.UNIT_HEIGHT);
        container.getChildren().add(scrollPane);
        container.getChildren().add(tierList);

        TierPropertiesMenu tierPropertiesMenu = new TierPropertiesMenu();
        anchorPane.getChildren().add(tierPropertiesMenu);
        tierPropertiesMenu.setViewOrder(1);
        var scene = new Scene(anchorPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}