package mapping;

public enum LightLevel {

    OchBlizko("Очень ярко"),
    Blizko("Ярко"),
    NetakNeedak("Нормально"),
    Daleko("Довольно тускло"),
    OchenDaleko("Темень сплошная");



    private final String description;
    private LightLevel(String p){
        description = p;
    }
    public  String toString(){
        return description;
    }
}
