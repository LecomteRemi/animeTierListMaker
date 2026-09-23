package animeTierList.animeTierList;

import javafx.scene.control.Label;

public class MediaCursor {
    
    private static MediaCursor instance;

    public static MediaCursor getInstance() {
        if (instance == null) {
            instance = new MediaCursor();
        }
        return instance;
    }
    
    private Label label = new Label("super test");

    public void ActivateMediaCursor() {
        
    }
}
