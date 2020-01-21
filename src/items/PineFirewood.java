package items;

public class PineFirewood extends Firewood {
    private static int burniness = 5; //added time
    private static final String NAME = "Сосновые дрова";
    public PineFirewood(int x, int y, int size){
        super(x,y, size,NAME);
    }
    @Override
    public int getBurntime() {
        return burniness*this.size;
    }
}
