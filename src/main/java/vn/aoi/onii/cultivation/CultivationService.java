package vn.aoi.onii.cultivation;

import vn.aoi.onii.player.PlayerData;
import vn.aoi.onii.player.PlayerManager;

public class CultivationService {

    private final PlayerManager manager;

    public CultivationService(PlayerManager manager) {
        this.manager = manager;
    }

    public void addPower(PlayerData data, int amount) {
        data.addPower(amount);
        checkBreakthrough(data);
    }

    private void checkBreakthrough(PlayerData data) {
        for (CultivationStage stage : CultivationStage.values()) {
            if (data.getPower() >= stage.getRequired()) {
                data.setStage(stage);
            }
        }
    }
} 
