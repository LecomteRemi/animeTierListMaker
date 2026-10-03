package animeTierList.animeTierList;


import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class Unit extends VBox {
    private Label title;
    private ImageView image;

    private double mouseAnchorX;
    private double mouseAnchorY;

    private UnitRow row;
    private AnchorPane anchorPane;


    private MediaData mediaData;


    public static final int UNIT_HEIGHT = 120;
    public static final int UNIT_WIDTH = 60;

    private boolean wasMouseDragged = false;
    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Unit() {
        this.title = new Label();
        this.image = new ImageView();
        
        /*
         * String[] splitUrl = mediaData.getImageURL().split("\\."); if (splitUrl.length
         * > 0 && splitUrl[splitUrl.length - 1].equals("webp")) {
         * System.out.println("webp: " + this.mediaData.getOriginalName());
         * System.out.println("webp: " + this.mediaData.getImageURL()); try {
         * 
         * BufferedImage bufferedImage = ImageIO.read(new
         * File(mediaData.getImageURL())); this.image = new
         * ImageView(convertToFxImage(bufferedImage));
         * 
         * } catch (IOException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); } }else {
         */
        // }



        this.getChildren().add(image);
        this.getChildren().add(this.title);
        this.setMaxHeight(UNIT_HEIGHT);
        this.setMaxWidth(UNIT_WIDTH);
        this.setPrefHeight(UNIT_HEIGHT);
        this.setPrefWidth(UNIT_WIDTH);
        BackgroundFill background_fill2 = new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY);
        this.setBackground(new Background(background_fill2));
        this.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
        this.setOnMousePressed(mouseEvent -> {

            if (row != null && row.containsUnit(this)) {

                Point2D point = this.localToScene(this.getLayoutX(), this.getLayoutY());
                row.replaceUnitByDummy(this);
                // row.getChildren().add(unitIndex, dummy);
                // row.getChildren().remove(this);
                anchorPane.getChildren().add(this);

                this.setViewOrder(2);

                double layoutX = 0;
                double layoutY = 0;
                Node node = row.getNode();
                while (node != null) {
                    layoutX += node.getLayoutX();
                    layoutY += node.getLayoutY();
                    node = node.getParent();
                }

                this.setLayoutY(this.getLayoutY() + layoutY);// + row.getParent().getLayoutY());
                this.setLayoutX(this.getLayoutX() + layoutX);// + row.getLayoutX() + row.getParent().getLayoutX());

            }
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
            wasMouseDragged = false;

            ReviewMenu.getInstance().displayReview(mediaData);
        });
        this.setOnMouseDragged(mouseEvent -> {
            row.disableReplacementDummy(this);
            this.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            this.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
            MediaCursor.getInstance().checkTierRowsMouseLocation(mouseEvent.getSceneX(), mouseEvent.getSceneY());
            wasMouseDragged = true;
        });
        this.setOnMouseReleased(mouseEvent -> {

            anchorPane.getChildren().remove(this);
            if (!wasMouseDragged) {
                row.replaceDummyByUnit(this);
                System.out.println("ok111");
            } else {
                if (MediaCursor.getInstance().getCurrentHoveredUnitRow() != null) {
                    row.removeUnit(this);
                    row = MediaCursor.getInstance().getCurrentHoveredUnitRow();
                    row.addUnit(this);
                    row.disableEndDummy();
                } else {
                    row.addUnit(this);
                    row.disableEndDummy();
                }
            }
        });
    }
    
    public void setMediaData(MediaData mediaData) {
        this.mediaData = mediaData;
        image.setImage(mediaData.getImage());
        this.title.setText(mediaData.getOriginalName());
        double ratio = image.getImage().getHeight() / image.getImage().getWidth();
        int width = UNIT_WIDTH;
        double height = ratio * width;
        image.setFitHeight(height);
        image.setFitWidth(width);
    }

    public void setRow(UnitRow row) {
        this.row = row;
    }

    public MediaData getMediaData() {
        return mediaData;
    }

}
