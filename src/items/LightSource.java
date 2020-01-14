package items;

import java.util.Objects;

public abstract class LightSource extends Matter implements LightInterface {
    private boolean isLIT;

    private int intensity=5;
    public LightSource(int x, int y, boolean isLIT, String name){
        super(x,y,name);
        this.isLIT = isLIT;

    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getIntensity() {
        return intensity;
    }

    public void activate(){
        this.isLIT = true;
    }
    public void deactivate(){
        this.isLIT = false;
    }
    public boolean isActive(){
        return isLIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LightSource that = (LightSource) o;
        return getX() == that.getX() && getY() == that.getY() && isLIT == that.isLIT && getName().equals(that.getName());
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(getX(), getY());
        result = 31 * result + getName().hashCode();
        result = 31 * result + Boolean.valueOf(isLIT).hashCode();
        return result;
    }

}
