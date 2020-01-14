package entities;


import items.AlarmClock;
import items.Apple;
import items.Matter;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Karlson extends Entity {

    public Karlson(int x,int y,String name){
        super(x,y,name);
    }

    public void action(Runnable method){
        method.run();
    }
    public void action(Matter item, Consumer<Matter> method){
        method.accept(item);
    }
    public void action(Matter item, int count, BiConsumer<Matter,Integer> method){
        method.accept(item,count);
    }

    public void bake(Apple apple){
        apple.setBaked(true);
    }
    public void repairAlarm(AlarmClock alarm){ alarm.setBroken(true);}
    public void breakAlarm(AlarmClock alarm){  alarm.setBroken(false);}
    // Consumer Supplier Runnable Function
    /* functions
    подкинуть полено - передать параметры полена етсь
    зажечь лампу - передать колво топлива есть
    погасить есть
    ходить есть
    садиться
    вставать
    взять со стола
    поставить на стол
     */

}
