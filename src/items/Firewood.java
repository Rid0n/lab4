package items;

abstract class Firewood extends Matter {
    //size - 1 to 3
    protected int size;
    public Firewood(int x, int y, int size, String name){
        super(x,y,name);
        this.size = size;
    }
    public int getBurntime() {
        return this.size;
    }
    public void setSize(int size){
        this.size = size;
    }
}
//check