package items;

public class Fireplace extends DepletableLightSource {
    private final static String TYPE = "Fireplace";
    private int intensity = 15;
    public Fireplace(int x, int y, boolean isLIT, int fuel_amount, int maxCapacity) {
        super(x,y, isLIT, TYPE,fuel_amount, maxCapacity);
        super.setIntensity(intensity);
    }

    public Fireplace(int x, int y, boolean isLIT, int fuel_amount) {
        super(x,y, isLIT,TYPE, fuel_amount);
        setIntensity(intensity);
    }


}


