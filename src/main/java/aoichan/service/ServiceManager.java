package aoichan.service;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    private final List<IService> services = new ArrayList<>();

    public void init() {
        // register services here
    }

    public void shutdown() {
        for (IService s : services) {
            s.shutdown();
        }
    }
} 
