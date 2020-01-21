package items;

public class Fireplace extends DepletableLightSource {
    private static String type = "Камин";

    public Fireplace(int x, int y, boolean isLIT, int fuel_amount, int maxCapacity) {
        super(x,y, isLIT, type,fuel_amount, maxCapacity);
    }


    public Fireplace(int x, int y, boolean isLIT, int fuel_amount) {
        super(x,y, isLIT,type, fuel_amount);
    }
}


