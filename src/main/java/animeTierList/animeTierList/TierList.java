package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class TierList extends VBox {
    private List<Tier> tiers;

    public TierList(AnchorPane anchorPane) {
        tiers = new ArrayList<Tier>();
        for (int i = 0; i < 3; i++) {
            Tier tier = new Tier("tier" + i, this, anchorPane);
            getChildren().add(tier);
            tiers.add(tier);
            tier.setIndexInList(i);
        }
    }

    public void moveTierUp(int idx) {
        if (idx > 0) {
            Tier tmp = tiers.remove(idx);
            tiers.add(idx - 1, tmp);
        }
        updateTierList();
    }

    public void moveTierDown(int idx) {
        if (idx + 1 < tiers.size()) {
            Tier tmp = tiers.remove(idx);
            tiers.add(idx + 1, tmp);
        }
        updateTierList();
    }

    private void updateTierList() {
        this.getChildren().clear();
        for (int i = 0; i < tiers.size(); i++) {
            tiers.get(i).setIndexInList(i);
            this.getChildren().add(tiers.get(i));
        }
    }
}
