package observer;

import java.util.*;

public class EventBus {
    private static EventBus instance;

    private final Map<EventType, Set<Observer>> eventToObserver = new EnumMap<>(EventType.class);

    private EventBus() {
        for (EventType type : EventType.values()) {
            eventToObserver.put(type, new HashSet<>());
        }
    }

    public static EventBus getInstance() {
        if (instance == null)
            instance = new EventBus();
        return instance;
    }

    public void attach(Observer observer, EventType eventType) {
        eventToObserver.get(eventType).add(observer);
    }

    public void detach(Observer observer) {
        for (Set<Observer> observers : eventToObserver.values()) {
            observers.remove(observer);
        }
    }

    public void postMessage(EventType eventType, String description) {
        Set<Observer> notifySet = new HashSet<>();
        notifySet.addAll(eventToObserver.get(eventType));
        for (Observer observer : notifySet) {
            observer.update(description);
        }
    }
}
