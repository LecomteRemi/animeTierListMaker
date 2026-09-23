package animeTierList.animeTierList;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Unit extends VBox {
    private Label title;
    private ImageView image;

    private double mouseAnchorX;
    private double mouseAnchorY;

    private HBox row;
    private AnchorPane anchorPane;

    private Label dummy;
    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Unit(String title) {
        this.title = new Label(title);
        image = new ImageView("https://cdn.myanimelist.net/images/anime/2/28090l.jpg");

        double ratio = image.getImage().getHeight() / image.getImage().getWidth();
        int width = 60;
        double height = ratio * width;
        image.setFitHeight(height);
        image.setFitWidth(width);

        this.getChildren().add(image);
        this.getChildren().add(this.title);

        this.setOnMousePressed(mouseEvent -> {

            if (row != null && row.getChildren().contains(this)) {
                int unitIndex = row.getChildren().indexOf(this);
                dummy = new Label();

                dummy.setMinSize(this.getWidth(), this.getHeight());
                row.getChildren().add(unitIndex, dummy);
                row.getChildren().remove(this);
                anchorPane.getChildren().add(this);

                this.setViewOrder(2);
                this.setLayoutY(this.getLayoutY() + row.getLayoutY());

            }
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });
        this.setOnMouseDragged(mouseEvent -> {
            if (dummy != null) {
                row.getChildren().remove(dummy);
                dummy = null;
            }
            this.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            this.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });
        this.setOnMouseReleased(mouseEvent -> {

        });
    }
    
    public void SetRow(HBox hbox) {
        this.row = hbox;
    }

}
