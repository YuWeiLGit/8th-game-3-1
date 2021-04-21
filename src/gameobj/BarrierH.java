package gameobj;

import controllers.ImageController;
import controllers.Rotate;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class BarrierH extends  GameObject{
    private BarrierAnimatorH barrierAnimatorH;

    public BarrierH(int x, int y) {
        super(x, y, Global.UNIT_X * 2, Global.UNIT_Y);
        barrierAnimatorH = new BarrierAnimatorH();
    }

    //是否可以穿過
    public boolean isBarrier() {
        boolean ans = true;
        if (barrierAnimatorH.count >= 5) {
            ans = true;
        } else {
            ans = false;
        }
        return ans;
    }


    @Override
    public void paintComponent(Graphics g) {
        barrierAnimatorH.paintComponent(g,painter().left(),painter().top(),painter().right(),painter().bottom());
    }

    @Override
    public void update() {

    }

    //障礙物的動畫
    private static class BarrierAnimatorH {
        private Image img;
        private int count;
        private Delay delay;
        private static final int[] BARRIER_ANIMATOR = {9,8,7,6,5,4,3,2,1,0};

        //建構子
        BarrierAnimatorH() {
            img = ImageController.getInstance().tryGet("/barrierH.png");
            delay = new Delay(15);
            delay.loop();
            count = 9;
        }

        public int getCount() {
            return count;
        }

        public void paintComponent(Graphics g, int left, int top, int right, int bottom) {
            if (delay.count()) {
                count--;
                if (count <= 0) {
                    count = 9;
                }
            }
            int tx = Global.UNIT_X * 2;
            int ty = Global.UNIT_Y * 1;
            g.drawImage(img, left, top, right, bottom, 0,
                    Global.UNIT_Y * BARRIER_ANIMATOR[count], tx, ty + Global.UNIT_Y * BARRIER_ANIMATOR[count], null);
        }
    }
}

