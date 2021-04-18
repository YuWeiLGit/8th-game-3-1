package scene;

import camera.Camera;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import controllers.ImageController;
import gameobj.*;
import maploader.MapInfo;
import maploader.MapLoader;
import utils.CommandSolver;
import utils.Delay;
import utils.Global;
import utils.Vector;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestScene extends Scene {
    private Camera cam;
    private SpaceShip spaceShip;
    private Image image;
    private Map map;
    private int num;
    private int XX;
    private int YY;
    private ArrayList<GameObject> gameObjectArr; //牆
    private double degree;
    public int dx;
    public int dy;
    int state;///能量bar
    private int count;//按壓時間
    private boolean willMove;
    private int moveStep;//移動基礎步數
    private EnergyBar energyBar;
    private InBar inBar;
    private ArrayList<InBar> inBars;
    private int savePointX;
    private int savePointY;

    private Goal goal;
    private Delay delay;


    public TestScene() {
    }

    @Override
    public void sceneBegin() {
        delay=new Delay(30);
        delay.loop();
        map = new Map();
        image = ImageController.getInstance().tryGet("/mapSceneBack.png");
        Scanner sc = new Scanner(System.in);
        spaceShip = new SpaceShip(100, 150, 7);
        goal=new Goal(150,150);
        energyBar = new EnergyBar(60, 30, 118, 51);
        inBars = new ArrayList<>();
        inBars.add(new InBar(13, 14));
        inBars.add(new InBar(32, 14));
        inBars.add(new InBar(52, 14));
        inBars.add(new InBar(72, 14));
        inBars.add(new InBar(93, 14));
        savePointX = 100;
        savePointY = 150;
        willMove = false;
        degree = 0;
        dx = 0;
        dy = 0;
        willMove = false;
        cam = new Camera.Builder(1000, 1000).setChaseObj(spaceShip, 1, 1)
                .setCameraStartLocation(0, 0).gen();
        try {
            MapLoader mapLoader = new MapLoader("/genMap.bmp", "/genMap.txt");
            ArrayList<MapInfo> test = mapLoader.combineInfo();
            this.gameObjectArr = mapLoader.creatObjectArray("wall", 32, test, new MapLoader.CompareClass() {
                        @Override
                        public GameObject compareClassName(String gameObject, String name, MapInfo mapInfo, int size) {
                            GameObject tmp = null;
                            if (gameObject.equals(name)) {
                                tmp = new Wall(mapInfo.getX() * size, mapInfo.getY() * size);
                                return tmp;
                            }
                            return null;
                        }
                    }
            );
        } catch (IOException ex) {
            Logger.getLogger(MapScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyTyped(char c, long trigTime) {

            }

            @Override
            public void keyPressed(int commandCode, long trigTime) {
                if (commandCode == 2) {

//                    Move(XX, YY, spaceShip);
                }
//                if(commandCode==6){  //角色斷線時發送斷線訊息
//                    ArrayList<String> strs = new ArrayList<String>();
//                    strs.add(String.valueOf(ClientClass.getInstance().getID()));
//                    ClientClass.getInstance().sent(Global.InternetCommand.DISCONNECT,strs);
//                    ClientClass.getInstance().disConnect();
//                    System.exit(0);
//                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
                if (commandCode == 2) {
                    for (int i = 0; i < inBars.size(); i++) {
                        inBars.get(i).setShow(false);
                    }
                    state = 0;
                } else if (commandCode == 0) {
                    spaceShip.back(savePointX, savePointY);
                }
            }
        };
    }

//    private void Move(int x1, int y1, GameObject gameObject) {
//        if (gameObject.painter().centerX() - cam.painter().left() == x1 && gameObject.painter().centerY() - cam.painter().top() == 0) {
//            return;
//        }
//        float a = Math.abs(gameObject.painter().centerX() - cam.painter().left() - x1);
//        float a1 = gameObject.painter().centerX() - x1;
//        float b = Math.abs(gameObject.painter().centerY() - cam.painter().top() - y1);
//        float b1 = gameObject.painter().centerY() - y1;
//        if (gameObject.painter().centerX() - cam.painter().left() == 0 && gameObject.painter().centerY() - cam.painter().top() == 0) {
//            return;
//        }
//        if (gameObject.painter().centerX() - cam.painter().left() < 5 && gameObject.painter().centerY() - cam.painter().top() < 5) {
//            return;
//        }
//        if (a == 0 && b == 0) {
//            return;
//        }
//        double d = Math.sqrt(a * a + b * b);
//        double xM = Math.cos(Math.acos(a / d)) * (spaceShip.getMoveStep());
//        double yM = Math.cos(Math.acos(b / d)) * (spaceShip.getMoveStep());
//
//        if (gameObject.painter().centerX() - cam.painter().left() > x1) {
//            xM = -xM;
//        }
//        if (gameObject.painter().centerY() - cam.painter().top() > y1) {
//            yM = -yM;
//        }
//        if (a == 0 && b != 0) {
//            if (yM < 0) {
//                gameObject.painter().offset(0, -spaceShip.getMoveStep());
//                gameObject.collider().offset(0, -spaceShip.getMoveStep());
//                return;
//            } else {
//                gameObject.painter().offset(0, spaceShip.getMoveStep());
//                gameObject.collider().offset(0, spaceShip.getMoveStep());
//                return;
//            }
//        }
//        if (a != 0 && b == 0) {
//            if (xM < 0) {
//                gameObject.painter().offset(-spaceShip.getMoveStep(), 0);
//                gameObject.collider().offset(-spaceShip.getMoveStep(), 0);
//                return;
//            } else {
//                gameObject.painter().offset(spaceShip.getMoveStep(), 0);
//                gameObject.collider().offset(spaceShip.getMoveStep(), 0);
//                return;
//            }
//        }
//        moveDirX = -xM;
//        moveDirY = -yM;
//        if (gameObject.getCollisionState() == GameObject.CollisionState.ANGLE) {
//            gameObject.painter().offset(-xM, -yM);
//            gameObject.collider().offset(-xM, -yM);
////            System.out.println("TOP&BOT");
//        }
//        else if (gameObject.getCollisionState() == GameObject.CollisionState.TOPnBOT ) {
//            gameObject.painter().offset(xM, -yM);
//            gameObject.collider().offset(xM, -yM);
////            System.out.println("left&right");
//        }else if(gameObject.getCollisionState() == GameObject.CollisionState.RIGHTnLEFT) {
//            gameObject.painter().offset(-xM, yM);
//            gameObject.collider().offset(-xM, yM);
//        }
//        else  {
//            gameObject.painter().offset(xM, yM);
//            gameObject.collider().offset(xM, yM);
//        }
//    }


    @Override
    public CommandSolver.MouseListener mouseListener() {
        return (e, state, trigTime) -> {
                if (state == CommandSolver.MouseState.RELEASED) {
                    spaceShip.changeCollisionState(GameObject.CollisionState.NORMAL);
                    goal.changeCollisionState(GameObject.CollisionState.STEADY);
                    count=30;
                    double x = e.getX() + cam.painter().left()-spaceShip.painter().centerX();
                    double y = e.getY() +cam.painter().top() -spaceShip.painter().centerY();
//                    System.out.println("mx:"+e.getX());
//                    System.out.println("my:"+e.getY());
//                    System.out.println("sx:"+spaceShip.painter().centerX());
//                    System.out.println("sx:"+spaceShip.painter().centerY());
//                    System.out.println("x:"+x);
//                    System.out.println("y:"+y);
                    Vector speed = new Vector(x, y);
                    Vector tmpSpeed=new Vector(0,0);
                    speed.setLength(Global.getHypotenuse(x, y) / 50);
                    spaceShip.setSpeed(speed);
                    goal.setSpeed(tmpSpeed);

                }
//                if (state != null) {
//                     XX = e.getX();
//                     YY  = e.getY();
//                     setDegree(XX,YY);
//                    int count1 = Math.abs((int) Global.getHypotenuse(XX, YY, spaceShip.painter().centerX() - cam.painter().left(), spaceShip.painter().centerY() - cam.painter().top()));
//                }
        };
    }

    private void setDegree(int x, int y) {
        double a = 0.5 * spaceShip.painter().height();//check
        double b = Global.getHypotenuse(x, y
                , spaceShip.painter().centerX() - cam.painter().left(), spaceShip.painter().top() - cam.painter().top());
        double c = Global.getHypotenuse(x, y
                , spaceShip.painter().centerX() - cam.painter().left(), spaceShip.painter().centerY() - cam.painter().top());
        double t1 = 2 * a * c;
        double t2 = (c * c + a * a - b * b);
        double t3 = t2 / t1;
        double t4 = Math.acos(t3);
        double t5 = Math.toDegrees(t4);
        if (x < spaceShip.painter().centerX() - cam.painter().left() && y > spaceShip.painter().top() - cam.painter().top()) {
            t5 = -t5;
        }
        if (x < spaceShip.painter().centerX() - cam.painter().left() && y < spaceShip.painter().top() - cam.painter().top()) {
            t5 = -t5;
        }
        degree = t5;

    }

    @Override
    public void paint(Graphics g) {
        cam.start(g);
        g.drawImage(image, 0, 0, null);
        for (int i = 0; i < gameObjectArr.size(); i++) {
            gameObjectArr.get(i).paint(g);
        }
        spaceShip.paintComponent(g, degree);
        spaceShip.paint(g);
        goal.paint(g);
        goal.paintComponent(g);
//        spaceShip.paint(g);
//      this.spaceShip.get(0).paint(g); //自己決角色
        cam.end(g);
        energyBar.paintComponent(g);
        for (int i = 0; i < inBars.size(); i++) {
            inBars.get(i).paintComponent(g);
        }
    }

    @Override
    public void update() {
        cam.update();
        if (count < 0) {
            count = 0;
        }
        count--;
        for (int i = 0; i < state; i++) {
            inBars.get(i).setShow(true);
        }
        for (int i = 0; i < gameObjectArr.size(); i++) {
            if (spaceShip.isCollision(gameObjectArr.get(i)) ) {
                break;
            }
        }
        for (int i = 0; i < gameObjectArr.size(); i++) {
            if (goal.isCollision(gameObjectArr.get(i)) ) {
                break;
            }
        }
        System.out.println("goal:"+goal.getCollisionState());
        spaceShip.isCollision(goal);

        if (count > 0) {
            goal.move();
            spaceShip.move();
            }
        }
    }




