package com.aoi.crystalengine.event;

import java.lang.reflect.Method;
import java.util.*;

/*
#【!】Code:
Internal event system cho các module.
Giống Bukkit Event nhưng lightweight.
*/

public class EventBus {

    private final Map<Class<?>, List<ListenerMethod>> listeners = new HashMap<>();

    public void register(Object listener) {

        for (Method method : listener.getClass().getDeclaredMethods()) {

            if (!method.isAnnotationPresent(Subscribe.class))
                continue;

            if (method.getParameterCount() != 1)
                continue;

            Class<?> eventType = method.getParameterTypes()[0];

            listeners
                .computeIfAbsent(eventType, k -> new ArrayList<>())
                .add(new ListenerMethod(listener, method));
        }
    }

    public void post(Object event) {

        List<ListenerMethod> list = listeners.get(event.getClass());

        if (list == null)
            return;

        for (ListenerMethod lm : list) {

            try {

                lm.method.invoke(lm.owner, event);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    /*
    #【!】Code:
    Listener wrapper
    */
    private static class ListenerMethod {

        private final Object owner;
        private final Method method;

        public ListenerMethod(Object owner, Method method) {

            this.owner = owner;
            this.method = method;
        }

    }

}
