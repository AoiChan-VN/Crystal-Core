package aoichan.crystal.api.socket;

import java.util.ArrayList;
import java.util.List;

public class SocketData {

    // [!] Code: Gem IDs
    private final List<String> gems = new ArrayList<>();

    public List<String> getGems() {
        return gems;
    }

    // [!] Code: Add gem
    public void add(String gemId) {

        gems.add(gemId);
    }

    // [!] Code: Remove gem
    public void remove(String gemId) {

        gems.remove(gemId);
    }

    // [!] Code: Gem count
    public int size() {

        return gems.size();
    }
} 
