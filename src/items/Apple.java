package items;

public class Apple extends Matter {
    private boolean isBaked;
    private String condition = "Свежее";
    public int position;
    public Apple(int x,int y,int position,String name){
        super(x,y,name);
        this.position = position;
    }
    public void setBaked(boolean h){
        this.isBaked = h;
    }
    public int size = 2;
    public void decreaseSizeOfApple(){
        size = size - 1;
    }
    public boolean isBaked(){
        return isBaked;
    }

    static int appleCount;

    public int showAppleSize(){
        return size;
    }

    public void affectApple(String state){
        this.condition = state;
    }


}
