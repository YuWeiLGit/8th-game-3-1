package utils;

public class Global {
    public class InternetCommand{
        public static final int CONNECT=0;
        public static final int MOVE=1;
        public static final int DISCONNECT=2;
    }
//    public class calculator{
//        public double dx(double x1,double x2){
//            return
//        }
//    }
    public enum Direction {
        UP(3),
        DOWN(0),
        LEFT(1),
        RIGHT(2),
        NO_DIR(4);
        private int value;

        Direction(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }

        public static Direction getDirection(int value){
            for(Direction d : Direction.values()){
                if(d.getValue() == value){
                    return d;
                }
            }
            return Direction.NO_DIR;
        }
    }

    public static final boolean IS_DEBUG =false;
    public static double getDegree(int x1,int y1,int x2,int y2){
        double a=getHypotenuse( x1, y1, x2, y2);
        double b=getHypotenuse(x1,y1,x2,y1);
        double c=getHypotenuse(x2,y1,x2,y2);
        double t1 = 2 * a * c;
        double t2 = (c * c + a * a - b * b);
        double t3 = t2 / t1;
        double t4 = Math.acos(t3);
        return  Math.toDegrees(t4);
    }
    public static double anotherDegree(double degree){
        return 90-degree;
    }

    public static double getHypotenuse(int x1,int y1,int x2,int y2){
        double tmp;
        int dx=x1-x2;
        int dy=y1-y2;
        tmp=Math.sqrt(dx*dx+dy*dy);
        return tmp;
    }
    public static double getHypotenuse(double dx,double dy){
        double tmp;
        tmp=Math.sqrt(dx*dx+dy*dy);
        return tmp;
    }
    public static double anglesFinder(double a, double b, double c) {

        double alpha;
        double beta;
        double gamma;
        alpha = (double) Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * c * b));
        beta = (double) Math.acos((Math.pow(a, 2) + Math.pow(c, 2) - Math.pow(b, 2)) / (2 * a * c));
        gamma = (double) Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b));
        System.out.println("angle between a & b is: " + (beta * (180 / Math.PI)));
        System.out.println("angle between a & c is: " + (alpha * (180 / Math.PI)));
        System.out.println("angle between b & c is: " + (gamma * (180 / Math.PI)));
        return (alpha * (180 / Math.PI));
    }


    public static void log(String str) {
        if (IS_DEBUG) {
            System.out.println(str);
        }
    }
    // 單位大小
    public static final int UNIT_X = 32;
    public static final int UNIT_Y = 32;
    // 視窗大小
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int MAP_WIDTH = 1280;
    public static final int MAP_HEIGHT = 1280;
    public static final int SCREEN_X = WINDOW_WIDTH - 8 - 8;
    public static final int SCREEN_Y = WINDOW_HEIGHT - 31 - 8;
    // 資料刷新時間
    public static final int UPDATE_TIMES_PER_SEC = 60;// 每秒更新60次遊戲邏輯
    public static final int NANOSECOND_PER_UPDATE = 1000000000 / UPDATE_TIMES_PER_SEC;// 每一次要花費的奈秒數
    // 畫面更新時間
    public static final int FRAME_LIMIT = 60;
    public static final int LIMIT_DELTA_TIME = 1000000000 / FRAME_LIMIT;

    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static boolean random(int rate) {
        return random(1, 100) <= rate;
    }
}
