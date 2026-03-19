package aoichan.eventbus;

import aoichan.thread.AsyncPool;

import java.lang.reflect.Method;
import java.util.*;

public class EventBus {

    private static final Map<Class<?>, List<ListenerMethod>> listeners = new HashMap<>();

    public static void register(Object obj) {
        for (Method m : obj.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Subscribe.class)) continue;

            Class<?> type = m.getParameterTypes()[0];
            Subscribe meta = m.getAnnotation(Subscribe.class);

            listeners.computeIfAbsent(type, k -> new ArrayList<>())
                    .add(new ListenerMethod(obj, m, meta.priority(), meta.async()));
        }

        listeners.values().forEach(list -> list.sort(Comparator.comparing(l -> l.priority)));
    }

    public static void post(Event event) {
        List<ListenerMethod> list = listeners.get(event.getClass());
        if (list == null) return;

        for (ListenerMethod lm : list) {
            if (event.isCancelled()) break;

            Runnable task = () -> {
                try {
                    lm.method.invoke(lm.owner, event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            if (lm.async) {
                AsyncPool.run(task);
            } else {
                task.run();
            }
        }
    }

    private static class ListenerMethod {
        Object owner;
        Method method;
        EventPriority priority;
        boolean async;

        ListenerMethod(Object o, Method m, EventPriority p, boolean async) {
            owner = o;
            method = m;
            priority = p;
            this.async = async;
            method.setAccessible(true);
        }
    }
}
