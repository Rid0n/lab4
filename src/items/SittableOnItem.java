package items;

import entities.Entity;

public class SittableOnItem extends ItemMultiplied {
    private String material;
    private String type;
    private int legNumber;
    private boolean isSatOn;
    public SittableOnItem(int x, int x2,int y,int y2, String material, String type, int legNumber){
        super(x,x2,y,y2,type);
        this.material = material;
        this.legNumber = legNumber;
    }
    public boolean isSatOn(){
        return isSatOn;
    }
    public void sit(Entity Character){
        this.isSatOn = true;
        Character.moveTo(getX(),getY());
    }
    public void standUp(){
        this.isSatOn = false;
    }
    //sit on it func
}
