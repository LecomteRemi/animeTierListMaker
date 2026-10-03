package animeTierList.animeTierList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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


        HBox globalContainer = new HBox();
        globalContainer.setViewOrder(3);

        ReviewMenu reviewMenu = new ReviewMenu();

        VBox tierListMakerContainer = new VBox();
        globalContainer.getChildren().add(tierListMakerContainer);
        tierListMakerContainer.setViewOrder(3);
        AnchorPane anchorPane = new AnchorPane(globalContainer);

        TierList tierList = new TierList(anchorPane);
        tierList.setMinWidth(800);
        tierList.setMaxWidth(800);
        ListRow listRow = new ListRow(anchorPane);
        AllMediaList.getInstance().setDefaultRow(listRow);
        listRow.setMinWidth(616.5);
        /*
         * ScrollPane scrollPane = new ScrollPane(listRow);
         * scrollPane.setMaxWidth(616.5);
         * scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
         * scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER); //
         * scrollPane.setMaxWidth(Double.MAX_VALUE);
         * scrollPane.setPrefViewportHeight(Unit.UNIT_HEIGHT);
         */

        SyncTable syncTable = new SyncTable();
        tierListMakerContainer.getChildren().add(syncTable);
        tierListMakerContainer.getChildren().add(listRow);
        // container.getChildren().add(scrollPane);
        tierListMakerContainer.getChildren().add(tierList);

        TierPropertiesMenu tierPropertiesMenu = new TierPropertiesMenu(anchorPane);
        tierList.setTierPropertiesMenu(tierPropertiesMenu);
        // anchorPane.getChildren().add(tierPropertiesMenu);
        tierPropertiesMenu.setViewOrder(1);
        tierPropertiesMenu.setPrefSize(400, 400);
        tierPropertiesMenu.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));

        globalContainer.getChildren().add(reviewMenu);
        var scene = new Scene(anchorPane, 640, 480);



        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}