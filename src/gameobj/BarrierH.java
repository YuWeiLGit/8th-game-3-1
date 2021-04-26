package gameobj;

import controllers.ImageController;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class BarrierH extends  GameObject{
    private BarrierAnimatorH barrierAnimatorH;

    public BarrierH(int x, int y) {
        super(x, y, Global.UNIT_X * 3, Global.UNIT_Y);
        barrierAnimatorH = new BarrierAnimatorH();
    }

    //是否可以穿過
    public boolean isBarrier() {
        boolean ans = true;
        if (barrierAnimatorH.count !=0) {
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
        private Delay delay2;
        private static final int[] BARRIER_ANIMATOR = {9,8,7,6,5,4,3,2,1,0};


        //建構子
        BarrierAnimatorH() {
            img = ImageController.getInstance().tryGet("/barrierH1v.png");
            delay = new Delay(5);
            delay2 = new Delay(90);
            delay.loop();
            delay2.isPause();
            count = 0;
        }

        public int getCount() {
            return count;
        }

        public void paintComponent(Graphics g, int left, int top, int right, int bottom) {
            if (delay.count()) {
                count++;
            }
            if (count >9) {
                count = 0;
                delay2.play();
                delay.pause();
            }
            if (delay2.count()) {
                delay2.pause();
                delay.loop();
            }

            int tx = Global.UNIT_X * 3;
            int ty = Global.UNIT_Y * 1;
            g.drawImage(img, left, top, right, bottom, 0,
                    Global.UNIT_Y * BARRIER_ANIMATOR[count], tx, ty + Global.UNIT_Y * BARRIER_ANIMATOR[count], null);
        }
    }
}

