package items;

public class BirchFirewood extends Firewood {
    private static final String NAME = "Birch firewood";
    private static int burniness = 10; //added time
    public BirchFirewood(int x, int y, int size){
        super(x,y, size, NAME);
    }
    @Override
    public int getBurnTime() {
        return this.burniness*this.size;
    }
}// change string(size) to sth else

