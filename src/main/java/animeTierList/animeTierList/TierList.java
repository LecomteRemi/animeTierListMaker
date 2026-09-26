package animeTierList.animeTierList;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class TierList extends VBox {
    private List<Tier> tiers;
    private TierPropertiesMenu tierPropertiesMenu;

    private AnchorPane anchorPane;

    public TierPropertiesMenu getTierPropertiesMenu() {
        return tierPropertiesMenu;
    }

    public void setTierPropertiesMenu(TierPropertiesMenu tierPropertiesMenu) {
        this.tierPropertiesMenu = tierPropertiesMenu;
    }

    public TierList(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
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

    public void addNewTier(int idx) {
        Tier tmp = new Tier("default", this, anchorPane);
        if (idx < tiers.size()) {
            tiers.add(idx, tmp);
        } else {
            tiers.add(tmp);
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

    public void deleteTier(int idx) {
        if (idx < tiers.size()) {
            tiers.remove(idx);
        }
        updateTierList();
    }

    public int getTiersCount() {
        return tiers.size();
    }
}
