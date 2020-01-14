package items;

public abstract class Matter /*extends map*/{
    private int x;
    private int y;
    private String name;
    public Matter(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public void moveTo(int x, int y){
        this.x = x;
        this.x = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public int[] getPosition() {
        int[] pose = new int[2];
        pose[0] = x;
        pose[1] = y;
        return pose;
    }
    @Override
    public String toString(){
        return name;
    }
}