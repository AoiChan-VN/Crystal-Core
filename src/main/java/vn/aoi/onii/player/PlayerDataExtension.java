package vn.aoi.onii.player;

import vn.aoi.onii.combat.ElementProfile;

public class PlayerDataExtension {

    private ElementProfile elements = new ElementProfile();

    public ElementProfile getElements() {
        if (elements == null) elements = new ElementProfile();
        return elements;
    }
} 
