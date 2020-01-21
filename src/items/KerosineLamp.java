package items;

public class KerosineLamp extends DepletableLightSource { // lets be in percent
    private static final String NAME = "Керосиновая лампа";
    public KerosineLamp(int x, int y, boolean isLIT, int fuel_amount, int maxCapacity){
        super(x,y, isLIT, NAME,fuel_amount,maxCapacity);
    }
    //func for fuel amount change(fuel type)
    public KerosineLamp(int x, int y, boolean isLIT, int fuel_amount){
        super(x,y, isLIT, NAME, fuel_amount);
    }

}
