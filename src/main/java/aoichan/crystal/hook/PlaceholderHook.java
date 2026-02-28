package aoidev.crystal.hook;

import aoidev.crystal.gem.GemManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderHook extends PlaceholderExpansion {

    private final GemManager manager;

    public PlaceholderHook(GemManager manager) {
        this.manager = manager;
    }

    @Override
    public String getIdentifier() {
        return "gems";
    }

    @Override
    public String getAuthor() {
        return "AoiDev";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {

        if (params.equalsIgnoreCase("total")) {
            return String.valueOf(manager.getAllGems().size());
        }

        return "";
    }
}
