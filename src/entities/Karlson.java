package entities;

import items.AlarmClock;
import items.Apple;
import items.NotEnoughSpaceException;
import mapping.Prostranstvo;

public class Karlson extends Entity {

    public Karlson(int x,int y,String name){
        super(x,y,name);
    }

    public void bake(Apple apple){
        apple.setBaked(true);
    }
    public void repairAlarm(AlarmClock alarm){ alarm.setBroken(false);}
    public void breakAlarm(AlarmClock alarm){  alarm.setBroken(true);}

    public void flySomewhere(String destination, Prostranstvo prostranstvo){
        super.location = destination;
        prostranstvo.deleteObj(this);
    }
    public void returnToRoof(Prostranstvo prostranstvo) throws NotEnoughSpaceException {
        super.location = "Roof";
        prostranstvo.addOnMap(this);
    }


    /* functions
    садиться
    вставать
     */

}
