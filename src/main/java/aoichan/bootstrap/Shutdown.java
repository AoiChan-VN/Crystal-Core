package aoichan.bootstrap;

import aoichan.engine.Engine;

public class Shutdown {

    public static void execute() {
        Engine.shutdown();
    }
} 
