import entities.*;

import items.*;
import mapping.Prostranstvo;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args){
        Action act = new Action() {
            private ArrayList<DepletableLightSource> Light_sources = new ArrayList<DepletableLightSource>();
            @Override
            public void addLightToList(DepletableLightSource dls){
                Light_sources.add(dls);
            }//anon
            @Override
            public void refreshEnvironment() {
                for (DepletableLightSource i : Light_sources) {
                    i.burn_fuel();
                }
            }
        };

        Fireplace fireplace = new Fireplace(1,1,true,3);
        act.addLightToList(fireplace);

        BirchFirewood birch1 = new BirchFirewood(15,11,2);
        BirchFirewood birch2 = new BirchFirewood(16,12,2);

        Wire wire = new Wire(5,10,3,3,"Provoloka");

        Apple apple1 = new Apple(14,6,1,"apple1");
        Apple apple2 = new Apple(10,6,2,"apple2");
        Apple apple3 = new Apple(14,3,3,"apple3");

        KerosineLamp lamp = new KerosineLamp(48,22,false,10);


        Workbench workbench = new Workbench(35,49,1,7,"workbench"); //learn to map big objects

        Tool hammer = new Tool(5,6,"hammer","To provide strong blunt force");
        Tool scissors = new Tool(7,6, "scissors","To cut through surfaces");
        AlarmClock alarm = new AlarmClock(2,2,false,"Kogsvort");

        Karlson karl= new Karlson(6,3,"карлсон");
        Entity junior = new Entity(7,2,"малыш");
        Entity.Possessions juniorPossessions = junior.new Possessions();
        Entity.Possessions karlPossessions = karl.new Possessions(hammer);

        SittableOnItem chair = new SittableOnItem(31,35,1,4,"Wood","chair",4);

        Prostranstvo map = new Prostranstvo();

        try {
            map.addOnMap(apple1);
            map.addOnMap(apple2);
            map.addOnMap(apple3);
            map.addOnMap(birch1);
            map.addOnMap(birch2);
            map.addOnMap(hammer);
            map.addMultlObjOnMap(chair);
            map.addOnMap(scissors);
            map.addOnMap(alarm);
            map.addMultlObjOnMap(workbench);



            map.addOnMap(karl);
            map.addOnMap(junior);

        }
        catch (NotEnoughSpaceException ex){
            System.err.println("Место уже занято!");
        }
        catch (ArrayIndexOutOfBoundsException  e){
            System.err.println("Объект размещён за пределами карты!");
        }

        workbench.addItem(hammer, 1,map);
        workbench.addItem(scissors, 2,map);


        map.showMap();
        try {
            map.addIstSveta(fireplace);
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.err.println("Объект размещён за пределами карты!");
        }
        catch (NotEnoughSpaceException e){
            System.err.println(e.getMessage());
        }

        try {
            map.addOnWire(apple1, wire);
            map.addOnWire(apple2, wire);
            map.addOnWire(apple3, wire);
        }
        catch (ArrayIndexOutOfBoundsException ex){

        }


        junior.eatApple(apple1);
        karl.bake(apple1);
        karl.bake(apple2);
        karl.bake(apple3);
        junior.eatApple(apple1);
        junior.eatApple(apple1);
        junior.eatApple(apple1);
        karl.eatApple(apple2);
        act.refreshEnvironment();

        junior.talk("Как хорошо, когда трещат поленья! -- Дни стали холодными. По всему видно, что пришла осень");

        map.moveObj(karl, 2,1);
        System.out.println("Карлсон подошел к Камину");


        act.refreshEnvironment(); // взял за руку

        //karl.action(birch,1,fireplace::);
        fireplace.refuel(birch1,1); // уверенно сказал
        map.deleteObj(birch1);
        System.out.println("Карлсон вкинул бревно");
        map.showLightMap();
        act.refreshEnvironment();
        fireplace.refuel(birch1);
        System.out.println("Карлсон вкинул бревно");
        map.deleteObj(birch2);
        map.showLightMap();
        act.refreshEnvironment();


        while(fireplace.isActive()){
            map.showLightMap();
            act.refreshEnvironment();
        }
        System.out.println("Камин прогорел");


        map.moveObj(karl,lamp.getX(),lamp.getY()-1);
        System.out.println("Карлсон подлетел к лампе");
        //!!!!!
        karl.action(lamp::activate);
        System.out.println("Карлсон включил лампу");
        act.addLightToList(lamp);
        map.leaveLightFrom(lamp);



        act.refreshEnvironment();
        map.showLightMap();

        junior.talk("Карлсон, не хочешь поиграть в трейдеров?");
        karl.talk("Всегда Готов!");

        act.refreshEnvironment();

        for (Matter i : workbench.getItemlist()){
            //junior.askFor(i)
            if (i.getName() == "Kogsvort") {
                juniorPossessions.trade(karlPossessions,i);
            }
        }
        karl.repairAlarm(alarm);
        System.out.println(juniorPossessions.getPossessions());
        System.out.println("This is very fun!");
    }

}
