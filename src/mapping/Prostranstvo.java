package mapping;
import items.*;

import java.lang.*;

public class Prostranstvo {
    private Matter[][] myMap;
    private Enum[][] enumMap;
    private ItemMultiplied[][] myMultMap;
    private int sizeX;
    private int sizeY;
    private LightLevel[][] svetMap;

    public Prostranstvo() {
        this(50, 70);
    }

    public Prostranstvo(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.myMap = new Matter[sizeX][sizeY];
        this.myMultMap = new ItemMultiplied[sizeX][sizeY];
        this.enumMap = new Enum[sizeX][sizeY];
        this.svetMap = new LightLevel[sizeX][sizeY];

        for (int i = 0; i <= sizeX-1; i++) {
            for (int j = 0; j <= sizeY-1 ; j++) {
                this.enumMap[i][j] = Enum.AIR;
                if (j == 0) {
                    this.enumMap[i][j] = Enum.SURFACE;
                }else {
                    if (i == 0 | i == sizeX-1) {
                        this.enumMap[i][j] = Enum.WALL;
                    }
                }
            }
        }

    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void addItemOnWorkbench(Matter item, ItemMultiplied workbench, int workbenchX){
    if (workbenchX+workbench.getX() <= workbench.getX2() & workbenchX + workbench.getX() >= workbench.getX()){
        this.myMap[workbench.getX()+workbenchX][workbench.getY2()+1]=item;
    }


    }
    public void addMultlObjOnMap(ItemMultiplied obj)  {

            for (int i = obj.getY2(); i >= obj.getY(); i--) {
                for (int j = obj.getX(); j < obj.getX2(); j++) {
                    if (this.getObjInCoord(i, j) == null) {
                        this.myMultMap[j][i] = obj;

                    } else {

                    }
                }
            }
            showMap();




    }


    public void addOnMap(Matter obj) throws ArrayIndexOutOfBoundsException,NotEnoughSpaceException {
        if (obj.getX() <= sizeX & obj.getX() > -1 | obj.getY() <=sizeY & obj.getY() > -1) {
            if (this.getObjInCoord(obj.getX(), obj.getY()) == null) {
                if (obj.getName() == "Karl") {
                    this.myMap[obj.getX()][obj.getY()] = obj;
                    System.out.println(obj.toString() + " уже здесь!");
                } else {
                    if (this.myMap[obj.getX()][obj.getY() - 1] != null) {
                        this.myMap[obj.getX()][obj.getY()] = obj;
                        System.out.println(obj.toString() + " уже здесь!");
                    } else if (this.enumMap[obj.getX()][obj.getY() + 19] != Enum.AIR) {
                        this.myMap[obj.getX()][obj.getY()] = obj;
                        System.out.println(obj.toString() + " уже здесь!");
                    } else {
                        for (int i = obj.getY(); this.myMap[obj.getX()][i - 1] == null; i--) {
                            if (this.enumMap[obj.getX()][i - 1] == Enum.AIR) {
                                this.myMap[obj.getX()][i] = obj;
                                System.out.println(obj.getName() + " Падает!");
                                moveObj(obj.getX(), i, obj.getX(), i - 1);
                                obj.setY(i - 1);
                            } else {
                                obj.setY(i);
                                showMap();
                                break;
                            }

                        }
                    }
                    System.out.println(obj.getName() + " уже здесь!");


                }
            } else {
                throw new NotEnoughSpaceException("Это место уже занято!");
            }
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Выход за границы массива!");
        }
    }

    public Matter getObjInCoord(int x, int y) {
        return this.myMap[x][y];
    }

    public void addOnWire(Apple apple,Wire wire){
        moveObj(apple.getX(),apple.getY(),wire.getX()+apple.position-1,wire.getY());

    }

    public ItemMultiplied getMultObjInCoord(int x,int y){ return this.myMultMap[x][y];}

    public Enum getEnumInCoord(int x, int y) {
        return this.enumMap[x][y];
    }

    public void moveObj (int x, int y, int xwant, int ywant)  {
        Matter obj = this.getObjInCoord(x, y);
        this.myMap[xwant][ywant] = obj;
        deleteObj(x,y);
        obj.setY(ywant);
        obj.setX(xwant);


    }
    public void moveObj(Matter obj, int xwant, int ywant) {
        this.myMap[xwant][ywant] = obj;
        deleteObj(obj.getX(),obj.getY());
        obj.setX(xwant);
        obj.setY(ywant);
    }

    public void deleteObj(int x, int y) {
        this.myMap[x][y] = null;
    }

    public void deleteObj(Matter obj){this.myMap[obj.getX()][obj.getY()] = null;}

    public void showMap() {

        String s = "";
        for (int i = this.sizeY-1; i >= 0; i--) {
            for (int j = 0; j < this.sizeX; j++) {
                String p = " ";
                Object obj = this.getObjInCoord(j, i);
                Object multObj = this.getMultObjInCoord(j,i);
                Enum en = this.getEnumInCoord(j, i);

                if (obj != null) {
                    p = " " + obj.getClass().getSimpleName().toCharArray()[0]+" ";

                }
                else if (multObj != null){
                    p = " " + multObj.getClass().getSimpleName().toCharArray()[0]+" ";
                }
                else {
                    switch (en) {
                        case WALL:
                            p = "|";
                            break;
                        case SURFACE:
                            p = "‾‾‾";
                            break;
                        case AIR:
                            p = " . ";
                            break;
                    }
                }
                s += p;
            }
            s += "\n";
        }
        System.out.println(s);
    }
    public class Lighting{
        public void addIstSveta(Matter obj, int lightLevel)  {
            for (int i = 0; i <= sizeY - 1; i++) {
                for (int j = 0; j <= sizeX - 1; j++) {

                    if (i==obj.getY() && j ==obj.getX()){
                        myMap[i][j] = obj;
                    }else {
                        if ((Math.abs(-obj.getX() + j)+Math.abs(-obj.getY() + i)) < 3) { // distance from light source to point
                            svetMap[i][j] = LightLevel.OCH_BLIZKO; //light intensity
                        } else if ((Math.abs(-obj.getX() + j)+Math.abs(-obj.getY() + i)) < 2 * lightLevel && svetMap[i][j] != LightLevel.OCH_BLIZKO) {
                            svetMap[i][j] = LightLevel.BLIZKO;
                        } else if ((Math.abs(-obj.getX() + j)+Math.abs(-obj.getY() + i)) < 4 * lightLevel && svetMap[i][j] != LightLevel.OCH_BLIZKO & svetMap[i][j] != LightLevel.BLIZKO) {
                            svetMap[i][j] = LightLevel.NETAK_NEEDAK;
                        } else if ((Math.abs(-obj.getX() + j)+Math.abs(-obj.getY() + i)) < 6 * lightLevel && svetMap[i][j] != LightLevel.OCH_BLIZKO & svetMap[i][j] != LightLevel.BLIZKO & svetMap[i][j] != LightLevel.NETAK_NEEDAK) {
                            svetMap[i][j] = LightLevel.DALEKO;
                        } else if ((Math.abs(-obj.getX() + j)+Math.abs(-obj.getY() + i)) < 500 && svetMap[i][j] != LightLevel.OCH_BLIZKO & svetMap[i][j] != LightLevel.BLIZKO & svetMap[i][j] != LightLevel.NETAK_NEEDAK & svetMap[i][j] != LightLevel.DALEKO) {
                            svetMap[i][j] = LightLevel.OCHEN_DALEKO;
                        }

                    }
                }

            }
            showLightMap();


        }
        public void leaveLightFrom(Matter obje){
            String s = "";
            for (int i = sizeY-1; i >= 0; i--) {
                for (int j = 0; j < sizeX; j++) {

                    LightLevel sv = this.getLightLevelInCoord(j, i);
                    Enum en = getEnumInCoord(j, i);
                    Object obj = getObjInCoord(j,i);
                    Object multObj = getMultObjInCoord(j,i);
                    if (en != null && obj != null && multObj != null){

                    }
                    else{
                        svetMap[j][i] = LightLevel.DALEKO;
                    }


                }

            }
            addIstSveta(obje, 5);


            showLightMap();
        }

        public LightLevel getLightLevelInCoord(int x,int y){
            return svetMap[x][y];
        }
        public void showLightMap(){
            String s = "";
            for (int i = sizeY-1; i >= 0; i--) {
                for (int j = 0; j < sizeX; j++) {
                    String p = " ";
                    LightLevel sv = this.getLightLevelInCoord(j, i);
                    Enum en = getEnumInCoord(j, i);
                    Object obj = getObjInCoord(j,i);
                    Object multObj = getMultObjInCoord(j,i);
                    if (obj != null) {
                        p = " " + obj.getClass().getSimpleName().toCharArray()[0]+" ";
                    }
                    else if (multObj != null){
                        p = " " + multObj.getClass().getSimpleName().toCharArray()[0]+" ";
                    }
                    else if (enumMap != null | svetMap != null){
                        switch (en) {
                            case WALL:
                                p = "|";
                                break;
                            case SURFACE:
                                p = "‾‾‾";
                                break;
                            case AIR:
                                switch (sv) {
                                    case OCH_BLIZKO:
                                        p = " * ";
                                        break;
                                    case BLIZKO:
                                        p = " & ";
                                        break;
                                    case NETAK_NEEDAK:
                                        p = " @ ";
                                        break;
                                    case DALEKO:
                                        p = " . ";
                                        break;
                                    case OCHEN_DALEKO:
                                        p = " / ";
                                        break;
                                }
                                break;


                        }

                    }
                    else{

                    }
                    s += p;
                }
                s += "\n";
            }
            System.out.println(s);
        }
    }
}