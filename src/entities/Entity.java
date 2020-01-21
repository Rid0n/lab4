package entities;

import items.*;


import java.util.ArrayList;
import java.util.List;


public class Entity extends Matter {
    protected String location;

    public static class Possessions {
        private List<Matter> Possessions = new ArrayList<>();

        public Possessions(){}

        public Possessions(Matter item){
            this.Possessions.add(item);
        }

        public void addItem(Matter item){
            Possessions.add(item);
        }//basic inner class

        public void removeItem(Matter item){
            Possessions.remove(item);
        }

        public void trade(Possessions traderInv, Matter item){
            this.addItem(item);
            traderInv.removeItem(item);
        }
        public void trade(Possessions traderInv, Matter item,Matter offer){
            this.addItem(item);
            this.removeItem(offer);
            traderInv.removeItem(item);
            traderInv.addItem(offer);
        }
        public List<Matter> getPossessions(){
            return Possessions;
        }
    }


    public Entity(int x, int y,String name){
        super(x,y,name);
    }


    public void talk(String phrase){
        System.out.println(getName()+ ": " + phrase);
    }

    public void eatApple(Apple apple) {
        if (apple.isBaked() == true) {
            apple.decreaseSizeOfApple();

            if (apple.size < 0) {
                System.out.println(" Кушать нечего( ");
            } else {
                System.out.println(apple.getName() + " - это вкусно !");
            }
        }
        else{
            System.out.println(apple.getName() + " нужно запечь ");
        }
    }
    public String getLocation(){
        return location;
    }


}