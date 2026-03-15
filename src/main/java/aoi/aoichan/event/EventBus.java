package aoi.aoichan.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/*
 EventBus đơn giản
*/

public class EventBus<T extends EngineEvent> {

    private final List<Consumer<T>> listeners = new ArrayList<>();

    public void register(Consumer<T> consumer) {

        listeners.add(consumer);

    }

    public void call(T event) {

        for (Consumer<T> consumer : listeners) {

            consumer.accept(event);

        }

    }

} 
