package mapping;

public enum Season {
    Summer("Лето"),
    Winter("Зима"),
    Autumn("Осень"),
    Spring("Весна");
    final String description;
    private Season(String p){
        description = p;
    }
    public  String toString(){
        return description;
    }

}
