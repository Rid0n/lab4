package items;

public class AlarmClock extends Matter {
    public AlarmClock(int x,int y,boolean isBroken,String name){
        super(x,y,name);
        this.isBroken = isBroken;
    }
    private boolean isBroken;
    public boolean isBroken(){ return isBroken;}
    public void setBroken(boolean h){
        this.isBroken = h;
    }

}
