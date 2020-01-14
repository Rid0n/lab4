package mapping;

public enum Enum {
    Wall("Стена"),
    Surface("Поверхность"),
    Air("Воздух");
    final String description;
    private Enum(String p){
        description = p;
    }
    public  String toString(){
        return description;
    }

}
