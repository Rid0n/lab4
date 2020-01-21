package mapping;

public enum TimeOfDay {
    DAY("День"),
    TWILIGHT("Сумерки"),
    NIGHT("Ночь"),
    MORNING("Утро");
    final String description;
    private TimeOfDay(String p){
        description = p;
    }
    public String toString(){
        return description;
    }

}
