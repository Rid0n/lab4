package items;

public abstract class DepletableLightSource extends LightSource implements DepletableInterface {
    protected int fuel_amount;
    protected static int maxCapacity=100;

    public static class InternalParameter{
        public int getMaxCapacity(){
            return maxCapacity;
        } // static nested class
    }

    public DepletableLightSource(int x, int y, boolean isLIT, String name, int fuel_amount, int maxCapacity){
        super(x,y, isLIT,name);
        this.fuel_amount = fuel_amount;
        this.maxCapacity = maxCapacity;
    }
    public DepletableLightSource(int x, int y, boolean isLIT, String name, int fuel_amount){
        super(x,y, isLIT,name);
        this.fuel_amount = fuel_amount;
    }
    public void refuel(Firewood burnTime, int amount){
        if (this.fuel_amount + burnTime.getBurnTime()*amount > this.maxCapacity){
            this.fuel_amount = this.maxCapacity;
            System.out.println("No room for any more fuel");
        }
        else {
            this.fuel_amount += burnTime.getBurnTime()*amount;
        }
    }
    public void refuel(Firewood burnTime){
        if (this.fuel_amount + burnTime.getBurnTime() > this.maxCapacity){
            this.fuel_amount = this.maxCapacity;
            System.out.println("No room for any more fuel");
        }
        else {
            this.fuel_amount += burnTime.getBurnTime();
        }
    }
    public boolean fueled(){
        if (this.fuel_amount > 0){
            return true;
        }
        else{
            this.deactivate();
            return false;// to main
        }
    }
    @Override
    public boolean isActive(){
        return fueled() & super.isActive();
    }
    public int getFuelAmount(){
        return this.fuel_amount;
    }

    public void burn_fuel(){


        if (this.isActive()){
            this.fuel_amount -= 1;
            //emitLight();
            System.out.printf("%s is burning %n", getName());
        }
        else {
            System.out.println(getName() + " is not lit or no fuel");
        }
    }
    public void setMaxCapacity(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }



}
