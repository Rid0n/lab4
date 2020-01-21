package mapping;

import items.DepletableLightSource;

public interface Environment {
    void addLightToList(DepletableLightSource dls);
    void refreshEnvironment();
    TimeOfDay timeOfDay();
    void setTimeOfDay(TimeOfDay timeOfDay);
}
