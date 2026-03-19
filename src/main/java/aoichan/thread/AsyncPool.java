package aoichan.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncPool {

    private static ExecutorService pool;

    public static void init() {
        pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public static void run(Runnable task) {
        pool.submit(task);
    }

    public static void shutdown() {
        pool.shutdown();
    }
} 
