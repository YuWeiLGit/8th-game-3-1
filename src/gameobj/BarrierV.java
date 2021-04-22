package gameobj;

import controllers.ImageController;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class BarrierV extends GameObject {
    private BarrierAnimatorV barrierAnimatorV;

    public BarrierV(int x, int y ){
        super( x, y,Global.UNIT_X,Global.UNIT_Y*3);
        barrierAnimatorV = new BarrierAnimatorV();
    }
    //是否可以穿過
    public boolean isBarrier() {
        boolean ans =true;
        if(barrierAnimatorV.count!=0){
            ans =true;
        }else{
            ans = false;
        }
        return ans;
        }


    @Override
    public void paintComponent(Graphics g) {
            barrierAnimatorV.paintComponent(g, painter().left(), painter().top(), painter().right(), painter().bottom());
        }

    @Override
    public void update() {

    }

    //障礙物的動畫
    private static class BarrierAnimatorV {
        private Image img;
        private int count;
        private Delay delay;
        private Delay delay2;
            private static final int[] BARRIER_ANIMATOR = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        //建構子
        private BarrierAnimatorV() {
            img = ImageController.getInstance().tryGet("/barrierV1v.png");
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
            if (count > 9) {
                count = 0;
                delay2.play();
                delay.pause();

            }
            if (delay2.count()) {
                delay2.pause();
                delay.loop();
            }


            int tx = Global.UNIT_X * 1;
            int ty = Global.UNIT_Y * 3;
            g.drawImage(img, left, top, right, bottom,   Global.UNIT_X * BARRIER_ANIMATOR[count],
                    0, tx + Global.UNIT_X * BARRIER_ANIMATOR[count] ,ty,null);
        }

    }
}
