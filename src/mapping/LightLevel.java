package mapping;

public enum LightLevel {

    OCH_BLIZKO("Очень ярко"),
    BLIZKO("Ярко"),
    NETAK_NEEDAK("Нормально"),
    DALEKO("Довольно тускло"),
    OCHEN_DALEKO("Темень сплошная");



    private final String description;
    private LightLevel(String p){
        description = p;
    }
    public  String toString(){
        return description;
    }
}
