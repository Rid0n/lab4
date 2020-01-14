package items;

public class Tool extends Matter {
    private String use;
    private String name;
    public Tool(int x,int y,String name,String use){
        super(x,y,name);
        this.use = use;
    };
    public void setUse(String use){
        this.use = use;
    }
    public String getUse() {
        return use;
    }

}
