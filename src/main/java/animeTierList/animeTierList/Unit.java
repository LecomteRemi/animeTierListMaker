package animeTierList.animeTierList;


import java.awt.image.BufferedImage;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
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

    private Label dummy;

    private MediaData mediaData;

    public static final int UNIT_HEIGHT = 120;
    public static final int UNIT_WIDTH = 60;
    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Unit(MediaData mediaData) {
        this.mediaData = mediaData;
        this.title = new Label(mediaData.getOriginalName());
        
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
        image = new ImageView(mediaData.getImageURL());
        // }

        

        double ratio = image.getImage().getHeight() / image.getImage().getWidth();
        int width = UNIT_WIDTH;
        double height = ratio * width;
        image.setFitHeight(height);
        image.setFitWidth(width);

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

            if (row != null && row.getChildren().contains(this)) {
                int unitIndex = row.getChildren().indexOf(this);
                dummy = new Label();
                dummy.setMinSize(
                        UNIT_WIDTH + BorderStroke.DEFAULT_WIDTHS.getRight(),
                        UNIT_HEIGHT);

                Point2D point = this.localToScene(this.getLayoutX(), this.getLayoutY());
                row.getChildren().add(unitIndex, dummy);
                row.getChildren().remove(this);
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
        });
        this.setOnMouseDragged(mouseEvent -> {
            if (dummy != null) {
                row.getChildren().remove(dummy);
                dummy = null;
            }
            this.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            this.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
            MediaCursor.getInstance().checkTierRowsMouseLocation(mouseEvent.getSceneX(), mouseEvent.getSceneY());
        });
        this.setOnMouseReleased(mouseEvent -> {
            if (dummy != null) {

                anchorPane.getChildren().remove(this);
                int unitIndex = row.getChildren().indexOf(dummy);

                row.getChildren().add(unitIndex, this);
                row.getChildren().remove(dummy);
            } else {
                if(MediaCursor.getInstance().getCurrentHoveredUnitRow() != null) {
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
    
    public void SetRow(UnitRow row) {
        this.row = row;
    }
    
    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }

}
