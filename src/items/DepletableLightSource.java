package items;



public abstract class DepletableLightSource extends LightSource implements DepletableInterface {
    protected int fuel_amount;
    protected int maxCapacity=100;
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
        if (this.fuel_amount + burnTime.getBurntime()*amount > this.maxCapacity){
            this.fuel_amount = this.maxCapacity;
            System.out.println("Больше топлива не влезет!");
        }
        else {
            this.fuel_amount += burnTime.getBurntime()*amount;
        }
    }
    public void refuel(Firewood burnTime){
        if (this.fuel_amount + burnTime.getBurntime() > this.maxCapacity){
            this.fuel_amount = this.maxCapacity;
            System.out.println("Больше топлива не влезет!");
        }
        else {
            this.fuel_amount += burnTime.getBurntime();
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
    public int getFuel_amount(){
        return this.fuel_amount;
    }

    public void burn_fuel(){
        if (this.isActive()){
            this.fuel_amount -= 1;
            System.out.printf("%s горит %n", getName());
        }
        else {
            System.out.println(getName() + " не горит");
        }
    }
    public int getMaxCapacity(){
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }
}
