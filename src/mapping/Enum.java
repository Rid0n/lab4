package mapping;

public enum Enum {
    WALL("Стена"),
    SURFACE("Поверхность"),
    AIR("Воздух");
    private final String description;
    private Enum(String p){
        description = p;
    }
    public  String toString(){
        return description;
    }

}
