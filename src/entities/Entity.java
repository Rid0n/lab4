package entities;

import items.*;

import java.util.ArrayList;
import java.util.List;


public class Entity extends Matter {

    public class Possessions {
        private List<Matter> Possessions = new ArrayList<Matter>();// координаты менять

        public Possessions(){

        }
        public Possessions(Matter item){
            this.Possessions.add(item);
        }

        public void addItem(Matter item){
            Possessions.add(item);
        }//vnutr

        public void removeItem(Matter item){
            Possessions.remove(item);
        }

        public void trade(Possessions traderInv, Matter item){
            this.addItem(item);
            traderInv.removeItem(item);
        }
        public List<Matter> getPossessions(){
            return Possessions;
        }
    }


    public Entity(int x, int y,String name){
        super(x,y,name);
    }



    public String talk(String phrase){
        return phrase;
    }

    public void eatApple(Apple apple) {
        if (apple.isBaked() == true) {
            apple.decreaseSizeOfApple();

            if (apple.size < 0) {
                System.out.println(" Кумшать нечего( ");
            } else {
                System.out.println(apple.getName() + " - это вкусно !");
            }
        }
        else{
            System.out.println(apple.getName() + " нужно запечь ");
        }
    }
}
