package animeTierList.animeTierList;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ReviewMenu extends VBox {
    private TextArea reviewArea;
    private Button okButton;

    private MediaData currentMedia;

    private static ReviewMenu instance;

    public ReviewMenu() {
        instance = this;
        this.reviewArea = new TextArea();
        reviewArea.setWrapText(true);
        this.getChildren().add(reviewArea);

        okButton = new Button("ok");
        okButton.setOnMouseClicked(e->{
            if (currentMedia != null)
                AllMediaList.getInstance().setReview(currentMedia, reviewArea.getText());
        });
        this.getChildren().add(okButton);
    }

    public static ReviewMenu getInstance() {
        if (instance == null)
            instance = new ReviewMenu();
        return instance;
    }

    public void displayReview(MediaData mediaData) {
        this.reviewArea.setText(AllMediaList.getInstance().getReview(mediaData));
        currentMedia = mediaData;
    }
}
