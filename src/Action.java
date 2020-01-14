import items.DepletableLightSource;


interface Action {
    void addLightToList(DepletableLightSource dls);
    void refreshEnvironment();
}

