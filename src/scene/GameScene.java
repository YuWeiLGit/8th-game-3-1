package scene;

import camera.Camera;
import camera.MapInformation;
import controllers.ImageController;
import controllers.RankControll;
import controllers.SceneController;
import gameobj.*;
import sun.awt.SunHints;
import utils.CommandSolver;
import utils.Delay;
import utils.Global;
import utils.Vector;

import java.io.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static utils.Global.*;

//測試地圖用
//並創建SceneTool
public class GameScene extends Scene {
    private String name;
    private BarrierV barrier;
    private SceneTool st;
    private SpaceShip spaceShip;
    private Image image;
    private Map map;
    private int XX;//滑鼠當下的X座標
    private int YY;//滑鼠當下的Y座標
    private ArrayList<GameObject> gameObjectArr; //牆
    private double degree;
    private double gDegree;//goal的degree;
    public int dx;
    public int dy;
    int state;///能量bar
    private int count;//按壓時間
    private boolean willMove;
    private EnergyBar energyBar;
    private ArrayList<EnergyBall> energyBalls;
    private ArrayList<BarrierH> barriersH;
    private ArrayList<BarrierV> barriersV;
    private EnergyBall energyBall;
    private ArrayList<InBar> inBars;
    private int savePointX;
    private int savePointY;
    private int totalTime;
    private ClockBack clockBack;
    private Goal goal;
    private Delay delay;
    private ArrayList<ClockNum> clockNums;
    private boolean isPardon;
<<<<<<< HEAD
    private ArrayList<String> tmp;
    private ArrayList<RankControll> rankControlls;
=======
    private boolean isPardon2;
    private boolean isPardon3;
    private boolean isPardon4;
    private boolean isPardon5;
    private boolean isPardon6;

    private ArrayList<String> ranking;
>>>>>>> origin/火焰修改+地圖修改

    public GameScene(String name) {
        this.name = name;
    }

    @Override
    public void sceneBegin() {
<<<<<<< HEAD
        rankControlls = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\zxcv0\\OneDrive\\文件\\8th-game-3-1-\\rank.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                String[] tmp = s.split("/");

                System.out.println(tmp[0]);
                System.out.println(Integer.parseInt(tmp[1]));
                rankControlls.add(new RankControll(Integer.parseInt(tmp[1]), tmp[0]));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
=======
        ranking = new ArrayList<>();
//        try {
////            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\zxcv0\\OneDrive\\文件\\8th-game-3-1-\\rank.txt"));
//            for (int i = 0; i < ranking.size(); i++) {
//                bw.write(ranking.get(i));
//            }
//
//            bw.write("name:" + name + "+" + totalTime);
//            bw.flush();
//            bw.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return;
//        }

>>>>>>> origin/火焰修改+地圖修改
        isPardon = false;
        isPardon2 = false;
        isPardon3 = false;
        isPardon4 = false;
        isPardon5 = false;
        isPardon6 = false;
        delay = new Delay(30);
        spaceShip = new SpaceShip(100, 2400);
        delay.loop();
        map = new Map();
        MapInformation.setMapInfo(0, 0, GAME_SCENE_WIDTH, GAME_SCENE_HEIGHT);
        st = new SceneTool.Builder()
                .setMaploader("/genMap2.bmp", "/genMap2.txt")
                .setCam(1080, 1280, spaceShip)
                .gen();
        st.genMap();
        //能量球
        energyBalls = new ArrayList<>();
        //中間
        energyBalls.add(new EnergyBall(random(1650, 2200), random(780, 848)));
        //右下
        energyBalls.add(new EnergyBall(random(2544, 2832), random(1520, 2032)));
        //左上
        energyBalls.add(new EnergyBall(random(560, 720), random(250, 400)));
        //右上
        energyBalls.add(new EnergyBall(random(3025, 3344), random(496, 432)));

        //障礙物
        barriersV = new ArrayList<>();
        barriersH = new ArrayList<>();
        //垂直的障礙物
        barriersV.add(new BarrierV(386, 2111));
        barriersV.add(new BarrierV(252, 1152));
        barriersV.add(new BarrierV(980, 768));
        barriersV.add(new BarrierV(1636, 1088));
        barriersV.add(new BarrierV(1348, 96));
        barriersV.add(new BarrierV(2624, 1250));
        barriersV.add(new BarrierV(1374, 2400));
        barriersV.add(new BarrierV(2690, 2302));
        barriersV.add(new BarrierV(3040, 576));
        barriersV.add(new BarrierV(3392, 608));
//        barriers.add(new Barrier())
        //水平障礙物
        barriersH.add(new BarrierH(126, 2016));
        barriersH.add(new BarrierH(1824, 770));
        barriersH.add(new BarrierH(2080, 770));
        barriersH.add(new BarrierH(2016, 1344));
        barriersH.add(new BarrierH(3232, 736));
        barriersH.add(new BarrierH(3208, 416));


        goal = new Goal(150, 2400);
        energyBar = new EnergyBar(60, 30, 118, 51);
        inBars = new ArrayList<>();
        inBars.add(new InBar(13, 14));
        inBars.add(new InBar(32, 14));
        inBars.add(new InBar(52, 14));
        inBars.add(new InBar(72, 14));
        inBars.add(new InBar(93, 14));
        clockBack = new ClockBack(450, 40, 106, 69);
        savePointX = 100;
        savePointY = 2400;
        clockNums = new ArrayList<>();
        clockNums.add(new ClockNum(460, 40, 0, 0, 520, 25, ClockNum.Hand.SecondHand));
        clockNums.add(new ClockNum(460, 40, 0, 0, 483, 25, ClockNum.Hand.SecondHand10));
        clockNums.add(new ClockNum(460, 40, 0, 0, 433, 25, ClockNum.Hand.MinuteHand));
        clockNums.add(new ClockNum(460, 40, 0, 0, 399, 25, ClockNum.Hand.HourHand));
        degree = 0;
        dx = 0;
        dy = 0;
    }

    @Override
    public void sceneEnd() {
        ImageController.getInstance().clear();
        rankControlls.add(new RankControll(totalTime / 60, name));
        for (int i = 0; i <rankControlls.size() ; i++) {
            for(int j=0;j<rankControlls.size()-i-1;j++){
                if(rankControlls.get(j).getScore()>rankControlls.get(j+1).getScore()){
                    RankSwap(j,j+1,rankControlls);
                }
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\zxcv0\\OneDrive\\文件\\8th-game-3-1-\\rank.txt"));
            for (int i = 0; i < rankControlls.size(); i++) {
                bw.write(rankControlls.get(i).getName() + "/" + rankControlls.get(i).getScore() + "\n");
            }
            bw.flush();
            bw.close();
        } catch (Exception ex) {
            return;
        }
    }

    @Override
    public CommandSolver.MouseListener mouseListener() {
        return (e, state, trigTime) -> {
            if(state!=null){
                XX = e.getX();
                YY = e.getY();
                setDegree(XX,YY);
            }
            if (state == CommandSolver.MouseState.RELEASED) {
                spaceShip.changeCollisionState(GameObject.CollisionState.NORMAL);
                goal.changeCollisionState(GameObject.CollisionState.STEADY);
                count = 40;
                double x = e.getX() + st.getCam().painter().left() - spaceShip.painter().centerX();
                double y = e.getY() + st.getCam().painter().top() - spaceShip.painter().centerY();
//                    System.out.println("mx:"+e.getX());
//                    System.out.println("my:"+e.getY());
//                    System.out.println("sx:"+spaceShip.painter().centerX());
//                    System.out.println("sx:"+spaceShip.painter().centerY());
//                    System.out.println("x:"+x);
//                    System.out.println("y:"+y);
                Vector speed = new Vector(x, y);
                Vector tmpSpeed = new Vector(0, 0);
                speed.setLength(Global.getHypotenuse(x, y) / 40);
                spaceShip.setSpeed(speed);
                goal.setSpeed(tmpSpeed);
//                goalSetDegree((int)goal.getSpeed().vx(),(int)goal.getSpeed().vy());

            }
        };
    }
    private void setDegree(int x, int y) {
        double a = 0.5 * spaceShip.painter().height();//check
        double b = Global.getHypotenuse(x, y
                , spaceShip.painter().centerX() - st.getCam().painter().left(), spaceShip.painter().top() - st.getCam().painter().top());
        double c = Global.getHypotenuse(x, y
                , spaceShip.painter().centerX() - st.getCam().painter().left(), spaceShip.painter().centerY() - st.getCam().painter().top());
        double t1 = 2 * a * c;
        double t2 = (c * c + a * a - b * b);
        double t3 = t2 / t1;
        double t4 = Math.acos(t3);
        double t5 = Math.toDegrees(t4);
        if (x < spaceShip.painter().centerX() - st.getCam().painter().left() && y > spaceShip.painter().top() - st.getCam().painter().top()) {
            t5 = -t5;
        }
        if (x < spaceShip.painter().centerX() - st.getCam().painter().left() && y < spaceShip.painter().top() - st.getCam().painter().top()) {
            t5 = -t5;
        }
        degree = t5;
    }
//    private void goalSetDegree(int x, int y) {
//        double a = 0.5 * goal.painter().height();//check
//        double b = Global.getHypotenuse(x, y
//                , goal.painter().centerX() - st.getCam().painter().left(), goal.painter().top() - st.getCam().painter().top());
//        double c = Global.getHypotenuse(x, y
//                , goal.painter().centerX() - st.getCam().painter().left(), goal.painter().centerY() - st.getCam().painter().top());
//        double t1 = 2 * a * c;
//        double t2 = (c * c + a * a - b * b);
//        double t3 = t2 / t1;
//        double t4 = Math.acos(t3);
//        double t5 = Math.toDegrees(t4);
//        if (x < goal.painter().centerX() - st.getCam().painter().left() && y > goal.painter().top() - st.getCam().painter().top()) {
//            t5 = -t5;
//        }
//        if (x < goal.painter().centerX() - st.getCam().painter().left() && y < goal.painter().top() - st.getCam().painter().top()) {
//            t5 = -t5;
//        }
//        gDegree = t5;
//    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyTyped(char c, long trigTime) {
            }

            @Override
            public void keyPressed(int commandCode, long trigTime) {
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
                    goal.back(savePointX + 40, savePointY);
                } else if (commandCode == 1) {
                    SceneController.getInstance().changeScene(new EndScene());
                }
            }
        };
    }


    @Override
    public void paint(Graphics g) {
        st.paintCamStart(g);
        st.paint(g);
        spaceShip.paintComponent(g, degree);
        spaceShip.paint(g);
        goal.paintComponent(g);
        goal.paint(g);
        for (int i = 0; i < energyBalls.size(); i++) {
            energyBalls.get(i).paint(g);
        }
        for (int i = 0; i < barriersV.size(); i++) {
            barriersV.get(i).paint(g);
            barriersV.get(i).paintComponent(g);
        }
        for (int i = 0; i < barriersH.size(); i++) {
            barriersH.get(i).paint(g);
            barriersH.get(i).paintComponent(g);
        }

        st.paintCamEnd(g);

        energyBar.paint(g);
        for (int i = 0; i < state; i++) {
            inBars.get(i).paint(g);
        }
        clockBack.paint(g);
        for (int i = 0; i < clockNums.size(); i++) {
            clockNums.get(i).paintComponent(g);
        }
        //用來碰撞判定的
        //st.getBasicBlock().get(i)
    }

    @Override
    public void update() {
        isPardon = false;
        isPardon2 = false;
        isPardon3 = false;
        isPardon4 = false;
        isPardon5 = false;
        isPardon6 = false;
        totalTime++;
        st.update();
        spaceShip.isCollision(goal);
        for (int i = 0; i < st.getBasicBlock().size(); i++) {
            if (spaceShip.isCollisionNotAngle(st.getBasicBlock().get(i))) {
                isPardon = true;
            }
        }
        if (!isPardon) {
            for (int i = 0; i < st.getBasicBlock().size(); i++) {
                if (spaceShip.AngleisCollision(st.getBasicBlock().get(i))) {
                    break;
                }
            }
        }
        for (int i = 0; i < st.getBasicBlock().size(); i++) {
            if (goal.isCollisionNotAngle(st.getBasicBlock().get(i))) {
                isPardon2 = true;
            }
        }
        if (!isPardon2) {
            for (int i = 0; i < st.getBasicBlock().size(); i++) {
                if (goal.AngleisCollision(st.getBasicBlock().get(i))) {
                    break;
                }
            }
        }

        for (int i = 0; i < state; i++) {
            inBars.get(i).setShow(true);
        }
        for (int i = 0; i < clockNums.size(); i++) {
            clockNums.get(i).update();
        }

        for (int i = 0; i < energyBalls.size(); i++) {
            if (energyBalls.get(i).isCollision(spaceShip)) {
                energyBalls.remove(i);
                state++;
            }
        }
        for (int i = 0; i < barriersV.size(); i++) {
            if (barriersV.get(i).isBarrier()) {
                if (spaceShip.isCollisionNotAngle(barriersV.get(i))) {
                    isPardon3 = true;
                }
            }
        }
        if (!isPardon3) {
            for (int i = 0; i < barriersV.size(); i++) {
                if (barriersV.get(i).isBarrier()) {
                    if (spaceShip.AngleisCollision(barriersV.get(i))) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < barriersH.size(); i++) {
            if (barriersH.get(i).isBarrier()) {
                if (spaceShip.isCollisionNotAngle(barriersH.get(i))) {
                    isPardon4 = true;
                }
            }
        }
        if (!isPardon4) {
            for (int i = 0; i < barriersH.size(); i++) {
                if (barriersH.get(i).isBarrier()) {
                    if (spaceShip.AngleisCollision(barriersH.get(i))) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < barriersV.size(); i++) {
            if (barriersV.get(i).isBarrier()) {
                if (goal.isCollisionNotAngle(barriersV.get(i))) {
                    isPardon5 = true;
                }
            }
        }
        if (!isPardon5) {
            for (int i = 0; i < barriersV.size(); i++) {
                if (barriersV.get(i).isBarrier()) {
                    if (goal.AngleisCollision(barriersV.get(i))) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < barriersH.size(); i++) {
            if (barriersH.get(i).isBarrier()) {
                if (goal.isCollisionNotAngle(barriersH.get(i))) {
                    isPardon6 = true;
                }
            }
        }
        if (!isPardon6) {
            for (int i = 0; i < barriersH.size(); i++) {
                if (barriersH.get(i).isBarrier()) {
                    if (goal.AngleisCollision(barriersH.get(i))) {
                        break;
                    }
                }
            }
        }
        if (count < 0) {
            count = 0;
            spaceShip.setIsMove(false);
        }
        count--;
        if (count > 0) {
            goal.move();
            spaceShip.move();
        }
    }

    public void RankSwap(int t1, int t2, ArrayList<RankControll> arrayList) {
        RankControll tmp1=new RankControll(0,null);
        RankControll tmp2=new RankControll(0,null);
        tmp1 = arrayList.get(t2);
        tmp2 = arrayList.get(t1);
        arrayList.add(t1 + 1, tmp1);
        arrayList.remove(t1);
        arrayList.add(t2 + 1, tmp2);
        arrayList.remove(t2);

    }

}

