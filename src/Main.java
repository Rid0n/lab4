import entities.*;
import items.BirchFirewood;
import items.*;

import mapping.Environment;
import mapping.Prostranstvo;
import mapping.TimeOfDay;

import java.util.ArrayList;
import java.util.function.Supplier;



public class Main {
    public static void main(String[] args){
        Environment environment = new Environment() {
            public int actions(Supplier<Integer> f) {
                return f.get();
            }
            TimeOfDay dayTime = TimeOfDay.DAY;
            //private ArrayList<Object> Changeable_vars = new ArrayList<Object>();
            private ArrayList<DepletableLightSource> Light_sources = new ArrayList<DepletableLightSource>();

            @Override
            public void addLightToList(DepletableLightSource dls) {
                Light_sources.add(dls);
            }

            @Override
            public void refreshEnvironment() {
                for (DepletableLightSource i : Light_sources) {
                    i.burn_fuel();
                }
            }
            @Override
            public TimeOfDay timeOfDay(){
                return dayTime;
            }
            @Override
            public void setTimeOfDay(TimeOfDay timeOfDay){
                this.dayTime = timeOfDay;
            }
        };
        Karlson karl = new Karlson(3,10,"Карлсон");
        Entity mal = new Entity(17,2,"Малыш");
        Entity vendor = new Entity(4,4,"Продавец Яблок");
        Entity frekenBok = new Entity(1,1,"фрекен Бок");

        Entity.Possessions karlPossessions = new Entity.Possessions();
        Entity.Possessions juniorPossessions = new Entity.Possessions();
        Entity.Possessions vendorPossessions = new Entity.Possessions();


        Matter coins = new Matter(1,10,"Монетки");
        AlarmClock alarm = new AlarmClock(2,2,true,"Kogsvort");
        Wire wire = new Wire(5,10,3,3,"Проволока");
        Apple apple1 = new Apple(13,6,1,"Яблоко 1");
        Apple apple2 = new Apple(10,6,2,"Яблоко 2");
        Apple apple3 = new Apple(14,3,3,"Яблоко 3");

        Fireplace fireplace = new Fireplace(1,1,true,2);
        environment.addLightToList(fireplace);

        BirchFirewood birch1 = new BirchFirewood(15,11,2);
        BirchFirewood birch2 = new BirchFirewood(16,12,2);

        KerosineLamp lamp = new KerosineLamp(48,22,false,30);

        Workbench workbench = new Workbench(29,49,1,7,"Верстак");

        Tool hammer = new Tool(2,4,"Молоток","Для большой ударной силы");
        Tool scissors = new Tool( 1,5,"Ножницы","Чтобы что-нибудь разрезать");
        Tool paper = new Tool(12,4,"Бумага", "Для ведения записей");

        Prostranstvo map = new Prostranstvo(50,50);
        Prostranstvo.Lighting lighting = map.new Lighting();


        karlPossessions.addItem(hammer);
        karlPossessions.addItem(scissors);
        karlPossessions.addItem(paper);
        karlPossessions.addItem(coins);
        karlPossessions.addItem(alarm);

        vendorPossessions.addItem(apple1);
        vendorPossessions.addItem(apple2);
        vendorPossessions.addItem(apple3);

//        map.addItemOnWorkbench(hammer,workbench,3);
//        map.addItemOnWorkbench(scissors,workbench,5);
//        map.addItemOnWorkbench(alarm,workbench,4);
//        map.addItemOnWorkbench(paper,workbench,2);

        workbench.addItem(alarm,6,map);
        workbench.addItem(scissors,5,map);
        workbench.addItem(paper,7,map);
        workbench.addItem(hammer,3,map);

        System.out.println(workbench.getItemlist());

        try {
            map.addOnMap(birch1);
            map.addOnMap(birch2);
            map.addOnMap(mal);
            map.addMultlObjOnMap(wire);
            map.addOnMap(karl);
            map.addMultlObjOnMap(workbench);
        }
        catch (NotEnoughSpaceException ex){
            System.err.println("Место уже занято!");
        }
        catch (ArrayIndexOutOfBoundsException  e){
            System.err.println("Объект размещён за пределами карты!");
        }

        //start scenario


        System.out.println(environment.timeOfDay());

        frekenBok.talk("Как хорошо в спокойствии, вдали от буйных Малыша и Карлсона");

        map.showMap();
        lighting.addIstSveta(fireplace,13);

        karl.flySomewhere("улица Хетерге", map);

        System.out.println("Карлсон улетел на улицу Хетерге");
        environment.refreshEnvironment();
        lighting.showLightMap();
        karlPossessions.trade(vendorPossessions,apple1,coins);
        karlPossessions.trade(vendorPossessions,apple2,coins);
        karlPossessions.trade(vendorPossessions,apple3,coins);
        System.out.println("Карлсон купил яблок, его предметы:");
        System.out.println(karlPossessions.getPossessions());
        System.out.println("Предметы продавца: ");
        System.out.println(vendorPossessions.getPossessions());


        try {
            karl.returnToRoof(map);
            map.addOnMap(apple1);
            map.addOnMap(apple2);
            map.addOnMap(apple3);
        }
        catch (NotEnoughSpaceException ex){
            System.err.println("Место уже занято!");
        }
        catch (ArrayIndexOutOfBoundsException  e){
            System.err.println("Объект размещён за пределами карты!");
        }

        System.out.println();

        map.addOnWire(apple1, wire);
        map.addOnWire(apple2, wire);

        map.showMap();

        map.addOnWire(apple3, wire);

        mal.eatApple(apple1);
        karl.bake(apple1);
        karl.bake(apple2);
        karl.bake(apple3);

        System.out.println("Карлсон нанизал яблоки на проволоку и печет их над огнем.");
        environment.setTimeOfDay(TimeOfDay.TWILIGHT);
        System.out.println(environment.timeOfDay());
        mal.eatApple(apple1);
        mal.eatApple(apple1);
        mal.eatApple(apple1);
        map.deleteObj(apple1);
        karl.eatApple(apple2);
        map.deleteObj(apple2);

        karlPossessions.removeItem(apple1);
        karlPossessions.removeItem(apple2);
        System.out.println("Малыш и Карлсон съели по яблоку");

        environment.refreshEnvironment();
        map.moveObj(karl,fireplace.getX()+1,fireplace.getY());
        mal.talk("Как хорошо, когда трещат поленья! -- Дни стали холодными. По всему видно, что пришла осень");

        environment.setTimeOfDay(TimeOfDay.NIGHT);
        System.out.println(environment.timeOfDay());

        fireplace.refuel(birch1,1);
        map.deleteObj(birch1);
        lighting.showLightMap();
        environment.refreshEnvironment();
        fireplace.refuel(birch1);
        map.deleteObj(birch2);
        lighting.showLightMap();
        environment.refreshEnvironment();

        while(fireplace.isActive()){
            lighting.showLightMap();
            environment.refreshEnvironment();
        }
        System.out.println("Камин прогорел");

        map.moveObj(karl,lamp.getY(),lamp.getX()-1);
        lamp.activate();
        System.out.println("Карлсон зажег керосиновую лампу, что висела под потолком");
        environment.addLightToList(lamp);

        lighting.leaveLightFrom(lamp);

        lighting.addIstSveta(lamp,5);

        environment.refreshEnvironment();

        map.moveObj(karl,mal.getX()-1,mal.getY());

        lighting.showLightMap();
        mal.talk("Карлсон, не хочешь поиграть в трейдеров?");
        karl.talk("Всегда Готов!");

        environment.refreshEnvironment();

        for (Matter i : workbench.getItemlist()){
            if (i.getName().equals("Kogsvort")) {
                juniorPossessions.trade(karlPossessions,i);
            }
            else{
                karl.talk(i.getName() + " я тебе не отдам!");
            }
        }

        karl.talk("Можешь взять этот будильник");
        System.out.println("Вещи малыша: " + juniorPossessions.getPossessions());
        System.out.println("Вещи Карлсона: " + karlPossessions.getPossessions());

        mal.talk("Это очень увлекательно:)");
        System.out.println(alarm.isBroken() ? "А будильник сломанный" : "С будильником все в порядке");

    }
}
