package scene;

import camera.Camera;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import controllers.ImageController;
import gameobj.*;
import maploader.MapInfo;
import maploader.MapLoader;
import utils.CommandSolver;
import utils.Global;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapScene extends Scene {
    private Camera cam;
    private SpaceShip spaceShip;
    private Image image;
    private Map map;
    private int num;
    private int XX;
    private int YY;
    private ArrayList<GameObject> gameObjectArr2; //無法被撞到,但撞到會轉狀態
    private ArrayList<GameObject> gameObjectArr1; //無法被撞到
    private ArrayList<GameObject> gameObjectArr; //牆
    private double degree;
    public int dx;
    public int dy;
    int state;///能量bar
    private int count;//按壓時間
    private boolean willMove;
   private int moveStep;//移動基礎步數
//    private boolean willMove;
    private EnergyBar energyBar;
    private InBar inBar;
    private ArrayList<InBar> inBars;
    private double mouseX;
    private double mouseY;


    public MapScene() {
    }

    @Override
    public void sceneBegin() {

        map = new Map();
        image = ImageController.getInstance().tryGet("/mapSceneBack.png");
        gameObjectArr = new ArrayList();
        gameObjectArr1 = new ArrayList();
        gameObjectArr2 = new ArrayList();
        Scanner sc = new Scanner(System.in);
        spaceShip = new SpaceShip(100, 1200, 7);
        energyBar = new EnergyBar(60, 30, 118, 51);
        inBars = new ArrayList<>();
        inBars.add(new InBar(13, 14));
        inBars.add(new InBar(32, 14));
        inBars.add(new InBar(52, 14));
        inBars.add(new InBar(72, 14));
        inBars.add(new InBar(93, 14));
        int state = 0;
        willMove = false;
        degree = 0;
        dx = 0;
        dy = 0;
        willMove = false;
        //______________
        //System.out.print("輸入0~7決定角色: ");
        //this.num = sc.nextInt();
        //______________
//        ArrayList<String> str=new ArrayList<>();
//        str.add("200");
//        str.add("200");
//        System.out.print("輸入0~7決定角色: ");
//        int num = sc.nextInt();
//        str.add(num+"");
//        spaceShip.add(new spaceShip(Integer.valueOf(str.get(0)),Integer.valueOf(str.get(1)),num));
//        ClientClass.getInstance().sent(Global.InternetCommand.CONNECT,str);
//        spaceShip.get(0).setId(ClientClass.getInstance().getID());
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

            this.gameObjectArr1 = mapLoader.creatObjectArray("slowDown", 32, test, new MapLoader.CompareClass() {
                        @Override
                        public GameObject compareClassName(String gameObject, String name, MapInfo mapInfo, int size) {
                            GameObject tmp = null;
                            if (gameObject.equals(name)) {
                                tmp = new SlowDownRoad(mapInfo.getX() * size, mapInfo.getY() * size);
                                return tmp;
                            }
                            return null;
                        }
                    }
            );
            this.gameObjectArr1.addAll(mapLoader.creatObjectArray("acceleration", 32, test, new MapLoader.CompareClass() {
                        @Override
                        public GameObject compareClassName(String gameObject, String name, MapInfo mapInfo, int size) {
                            GameObject tmp = null;
                            if (gameObject.equals(name)) {
                                tmp = new AccelerationRoad(mapInfo.getX() * size, mapInfo.getY() * size);
                                return tmp;
                            }
                            return null;
                        }
                    }
                    )
            );
            /*this.gameObjectArr.addAll(mapLoader.creatObjectArray("P3", 32, test, new MapLoader.CompareClass() {
                        @Override
                        public GameObject compareClassName(String gameObject, String name, MapInfo mapInfo, int size) {
                            GameObject tmp = null;
                            if (gameObject.equals(name)) {
                                tmp = new TestObject3(mapInfo.getX() * size, mapInfo.getY() * size);
                                return tmp;
                            }
                            return null;
                        }
                    }
                    )
            );
            this.gameObjectArr.addAll(mapLoader.creatObjectArray("P3", 32, test, new MapLoader.CompareClass() {
                        @Override
                        public GameObject compareClassName(String gameObject, String name, MapInfo mapInfo, int size) {
                            GameObject tmp = null;
                            if (gameObject.equals(name)) {
                                tmp = new TestObject3(mapInfo.getX() * size, mapInfo.getY() * size);
                                return tmp;
                            }
                            return null;
                        }
                    }
                    )
            );*/
//            for (int i = 0; i < test.size(); i++) {    //  這邊可以看array內容  {String name ,int x, int y, int xSize, int ySize}
//                System.out.println(test.get(i).getName());
//                System.out.println(test.get(i).getX());
//                System.out.println(test.get(i).getY());
//                System.out.println(test.get(i).getSizeX());
//                System.out.println(test.get(i).getSizeY());
//            }
            this.gameObjectArr2 = mapLoader.creatObjectArray("randomMaterial", 32, test, new MapLoader.CompareClass() {
                        @Override
                        public GameObject compareClassName(String gameObject, String name, MapInfo mapInfo, int size) {
                            GameObject tmp = null;
                            if (gameObject.equals(name)) {
                                tmp = new RandomMaterial(mapInfo.getX() * size, mapInfo.getY() * size);
                                return tmp;
                            }
                            return null;
                        }
                    }
            );
        } catch (IOException ex) {
            Logger.getLogger(MapScene.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Server.instance().create(12345); //建立連接埠
//        Server.instance().start();
//        System.out.println(Server.instance().getLocalAddress()[0]); //印出host IP
//        try{
////            //連接("host IP:127.0.0.1", port)
////            ClientClass.getInstance().connect("127.0.0.1", 12345);
//        }catch(IOException ex){
//            Logger.getLogger(MapScene.class.getName()).log(Level.SEVERE, null, ex);
//        }

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
                    count = count + 3;
                    state = count / 10;
                    if(state>5){
                        state=5;
                    }
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
                }
                willMove = true;
            }
        };
    }

    private void Move(int x1, int y1, GameObject gameObject) {
        if (gameObject.painter().centerX() - cam.painter().left() == x1 && gameObject.painter().centerY() - cam.painter().top() == 0) {
            return;
        }

        float a = Math.abs(gameObject.painter().centerX() - cam.painter().left() - x1);
        float a1 = gameObject.painter().centerX() - x1;
        float b = Math.abs(gameObject.painter().centerY() - cam.painter().top() - y1);
        float b1 = gameObject.painter().centerY() - y1;
//        System.out.println(x1+"/"+y1);
//        System.out.println(spaceShip.painter().centerX()+"/"+spaceShip.painter().centerY());

        if (gameObject.painter().centerX() - cam.painter().left() == 0 && gameObject.painter().centerY() - cam.painter().top() == 0) {
            System.out.println("return1");
            System.out.println("QQQ2");
            return;
        }

        if (gameObject.painter().centerX() - cam.painter().left() < 5 && gameObject.painter().centerY() - cam.painter().top() < 5) {
            System.out.println("QQQ3");
            return;
        }
        if (a == 0 && b == 0) {
            System.out.println("QQQ4");
            return;
        }
        double d = Math.sqrt(a * a + b * b);
        double xM = Math.cos(Math.acos(a / d)) * (spaceShip.getMoveStep());
        double yM = Math.cos(Math.acos(b / d)) * (spaceShip.getMoveStep());

        if (gameObject.painter().centerX() - cam.painter().left() > x1) {
            xM = -xM;
        }
        if (gameObject.painter().centerY() - cam.painter().top() > y1) {
            yM = -yM;
        }
        if (a == 0 && b != 0) {
            if (yM < 0) {
                gameObject.painter().offset(0, -spaceShip.getMoveStep() * 0.2);
                gameObject.collider().offset(0, -spaceShip.getMoveStep() * 0.2);
            } else {
                gameObject.painter().offset(0, spaceShip.getMoveStep() * 0.2);
                gameObject.collider().offset(0, spaceShip.getMoveStep() * 0.2);
            }
        }
        if (a != 0 && b == 0) {
            if (xM < 0) {
                gameObject.painter().offset(-spaceShip.getMoveStep() * 0.2, 0);
                gameObject.collider().offset(-spaceShip.getMoveStep() * 0.2, 0);
            } else {
                gameObject.painter().offset(spaceShip.getMoveStep() * 0.2, 0);
                gameObject.collider().offset(spaceShip.getMoveStep() * 0.2, 0);
            }
        }
        gameObject.painter().offset(xM, yM);
        gameObject.collider().offset(xM, yM);
        int XM = (int) xM;
        int YM = (int) yM;
    }
    public void mouseUpdate(){
        mouseX = XX + cam.getCameraWindowX();
        mouseY= YY + cam.getCameraWindowY();
    }

    @Override
    public CommandSolver.MouseListener mouseListener() {
        return (e, state, trigTime) -> {
            if(!willMove){
            if (state != null) {
                XX = e.getX();
                YY = e.getY();
                setDegree(XX, YY);
            }}
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
        g.drawImage(image,0,0,null);
        for (int i = 0; i < gameObjectArr.size(); i++) {
            gameObjectArr.get(i).paint(g);
        }
        for (int i = 0; i < gameObjectArr1.size(); i++) {
            gameObjectArr1.get(i).paint(g);
        }
        for (int i = 0; i < gameObjectArr2.size(); i++) {
            gameObjectArr2.get(i).paint(g);
        }
        spaceShip.paintComponent(g, degree);
        spaceShip.paint(g);

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
        } else if (count > 70) {
            count = 70;
        }
        count--;
        for (int i = 0; i < state; i++) {
            inBars.get(i).setShow(true);}
        for (int i = 0; i < gameObjectArr.size(); i++) {
            if (cam.isCollision(gameObjectArr.get(i))) {
                gameObjectArr.get(i).update();
            }
        }
        for(int i=0;i<gameObjectArr1.size();i++) {
            gameObjectArr1.get(i).update();
        }
        for(int i=0;i<gameObjectArr1.size();i++) {
            if (gameObjectArr1.get(i).getState() == GameObject.State.BURN) {
            //如果撞到就改變狀態
                if (spaceShip.isCollision(gameObjectArr1.get(i))) {
                //如果狀態是出生狀態才可以進去
                    //設定為消失狀態
                    gameObjectArr1.get(i).setState(GameObject.State.DISAPPEAR);
                    //接著加速
                    gameObjectArr1.get(i).active(spaceShip);
                }
            }
        }
        for(int i =0;i<gameObjectArr2.size();i++){
            gameObjectArr2.get(i).update();
        }
        for(int i=0;i<gameObjectArr2.size();i++) {
            if (gameObjectArr2.get(i).getState() == GameObject.State.BURN) {
                if (spaceShip.isCollision(gameObjectArr2.get(i))) {
                    gameObjectArr2.get(i).setState(GameObject.State.DISAPPEAR);
                }
            }
        }
        for(int i =0;i<gameObjectArr2.size();i++){
            gameObjectArr2.get(i).update();
        }
        mouseUpdate();
        spaceShip.update();

//        if (XX - spaceShip.painter().centerX() < 3 && YY - spaceShip.painter().centerY() < 3) {
//            willMove = false;
//            return;
//        }
//        for (int i = 0; i < gameObjectArr.size(); i++) {
//            if (cam.isCollision(gameObjectArr.get(i))) {
//                gameObjectArr.get(i).update();
//            }
//        }
//        for(int i=0;i<gameObjectArr1.size();i++) {
//            if (spaceShip.isCollision(gameObjectArr1.get(i))) {
//                gameObjectArr1.get(i).active(spaceShip);
//            }
//        }
//        for(int i=0;i<gameObjectArr1.size();i++) {
//            gameObjectArr1.get(i).update();
//        }
//        for(int i =0;i<gameObjectArr2.size();i++){
//            gameObjectArr2.get(i).update();
//        }
//        for(int i=0;i<gameObjectArr2.size();i++){
//            if(spaceShip.isCollision(gameObjectArr2.get(i))){
//                gameObjectArr2.get(i).setState(GameObject.State.DISAPPEAR);
//            }
//        }
//        for(int i =0;i<gameObjectArr2.size();i++){
//            gameObjectArr2.get(i).update();
//        }
        if (count > 0) {
            if (willMove) {
                Move(XX, YY, spaceShip);
                count = count -1;
                System.out.println("count" + count);
            }
        } else {
            willMove = false;
        }
    }}


//        for (int i = 0; i < gameObjectArr1.size(); i++) {
//            if (spaceShip.isCollision(gameObjectArr.get(i))) {
//                gameObjectArr.get(i).active(spaceShip);
//                break;
//            }

//        ArrayList<String> strr=new ArrayList<>();
//        strr.add(ClientClass.getInstance().getID()+"");
//        strr.add(spaceShip.get(0).painter().centerX()+"");
//        strr.add(spaceShip.get(0).painter().centerY()+"");
//        strr.add(spaceShip.get(0).getDir()+"");
//        ClientClass.getInstance().sent(Global.InternetCommand.MOVE,strr);

        /*ArrayList<String> strrr=new ArrayList<>();
        strrr.add(spaceShip.get(0).getNum()+"");
        ClientClass.getInstance().sent(Global.InternetCommand.CONNECT,strrr);*/
//
//        ClientClass.getInstance().consume(new CommandReceiver() {
//            @Override
//            public void receive(int serialNum, int internetcommand, ArrayList<String> strs) {
////                switch(internetcommand){
//                    case Global.InternetCommand.CONNECT:
//                        System.out.println("Connect " + serialNum);
//                        boolean isburn = false;
//                        for (int i = 0; i < spaceShip.size(); i++) {
//                            if (spaceShip.get(i).getId() == serialNum) {
//                                isburn = true;
//                                break;
//                            }
//                        }
//                        if(!isburn) {
//                            spaceShip.add(new spaceShip(Integer.valueOf(strs.get(0)),Integer.valueOf(strs.get(1)), Integer.valueOf(strs.get(2))));
//                            System.out.println("!!!!!!!!!!!!!!!!!!!!");
//                            spaceShip.get(spaceShip.size() - 1).setId(serialNum);
//                            ArrayList<String> str=new ArrayList<>();
//                            str.add(spaceShip.get(0).painter().centerX()+"");
//                            str.add(spaceShip.get(0).painter().centerY()+"");
//                            str.add(spaceShip.get(0).getNum()+"");
//                            ClientClass.getInstance().sent(Global.InternetCommand.CONNECT,str);
//                        }
//                        break;
//                    case Global.InternetCommand.MOVE:
//                        for(int i=1;i<spaceShip.size();i++) {
//                            if(spaceShip.get(i).getId()==Integer.valueOf(strs.get(0))) {
//                               spaceShip.get(i).painter().setCenter(Integer.valueOf(strs.get(1)),Integer.valueOf(strs.get(2)));
//                               spaceShip.get(i).walk(Global.Direction.getDirection(Integer.valueOf(strs.get(3))));
//                               break;
//                            }
//                        }
//                        break;
//                        case Global.InternetCommand.DISCONNECT:
//                            for(int i=0;i<spaceShip.size();i++){
//                                if(spaceShip.get(i).getId()==Integer.valueOf(strs.get(0))){
//                                    spaceShip.remove(i);
//                                }
//                            }
//                            break;
//                }
//            }
//        });
//        }



