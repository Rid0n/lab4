package items;

import entities.Entity;

public class SittableOnItem extends Matter {
    private String material;
    private String type;
    private int legNumber;
    private boolean isSatOn;
    public SittableOnItem(int x, int y, String material, String type, int legNumber){
        super(x,y,type);
        this.material = material;
        this.legNumber = legNumber;
    }
    public boolean isSatOn(){
        return isSatOn;
    }
    public void sit(Entity Character){
        this.isSatOn = true;
        Character.setPosition(getX(),getY());
    }
    public void standUp(){
        this.isSatOn = false;
    }
    //sit on it func
}
