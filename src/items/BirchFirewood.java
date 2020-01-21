package items;

public class BirchFirewood extends Firewood {
    private static final String NAME = "Березовые дрова";
    private static final int BURNINESS = 2; //added time
    public BirchFirewood(int x, int y, int size){
        super(x,y, size, NAME);
    }
    @Override
    public int getBurntime() {
        return BURNINESS *this.size;
    }
}// change string(size) to sth else

